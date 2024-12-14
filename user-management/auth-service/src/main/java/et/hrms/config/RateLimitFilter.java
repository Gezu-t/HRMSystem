package et.hrms.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;



@Slf4j
@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private static final int MAX_REQUESTS_PER_MINUTE = 60;
    private final Map<String, AtomicInteger> clientRequestCounts = new ConcurrentHashMap<>();
    private final Map<String, Long> clientLastRequestTime = new ConcurrentHashMap<>();
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String clientId = extractClientId(request);
        if (clientId == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        // Synchronize client request tracking with a lock to prevent race conditions
        lock.lock();
        try {
            // Initialize request count if it's a new client or a new minute
            long currentTime = System.currentTimeMillis();
            long oneMinuteAgo = currentTime - 60000;

            // Reset the counter if it's been more than a minute since the last request
            if (clientLastRequestTime.getOrDefault(clientId, 0L) < oneMinuteAgo) {
                clientRequestCounts.put(clientId, new AtomicInteger(0));
                clientLastRequestTime.put(clientId, currentTime);
            }

            // Increment the request count
            AtomicInteger requestCount = clientRequestCounts.computeIfAbsent(clientId, k -> new AtomicInteger(0));
            int currentRequestCount = requestCount.incrementAndGet();

            // Set rate limit headers
            response.setHeader("X-RateLimit-Limit", String.valueOf(MAX_REQUESTS_PER_MINUTE));
            response.setHeader("X-RateLimit-Remaining", String.valueOf(MAX_REQUESTS_PER_MINUTE - currentRequestCount));

            // Check if the client exceeded the rate limit
            if (currentRequestCount > MAX_REQUESTS_PER_MINUTE) {
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"rate_limit_exceeded\",\"message\":\"Too many requests\"}");
                return;
            }
        } finally {
            lock.unlock();
        }

        filterChain.doFilter(request, response);
    }

    private String extractClientId(HttpServletRequest request) {
        // Try to get client ID from JWT token
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken jwtAuth = (JwtAuthenticationToken) authentication;
            String clientId = jwtAuth.getToken().getSubject();
            if (StringUtils.hasText(clientId)) {
                return clientId;
            }
        }

        // Try to get client ID from request parameter
        String clientId = request.getParameter("client_id");
        if (StringUtils.hasText(clientId)) {
            return clientId;
        }

        // Try to get client ID from Basic Auth header
        String authHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Basic ")) {
            try {
                String base64Credentials = authHeader.substring("Basic ".length()).trim();
                String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
                return credentials.split(":")[0];
            } catch (Exception e) {
                log.warn("Failed to decode Basic Auth header", e);
            }
        }

        // If all attempts fail, use IP address as fallback
        return request.getRemoteAddr();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // Skip rate limiting for certain paths
        String path = request.getRequestURI();
        return path.startsWith("/actuator") ||
                path.startsWith("/swagger-ui") ||
                path.startsWith("/v3/api-docs") ||
                path.equals("/error");
    }
}


