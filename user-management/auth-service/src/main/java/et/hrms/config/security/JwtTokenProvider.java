package et.hrms.config.security;


import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.*;
import et.hrms.service.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
public class JwtTokenProvider {
//
//    private final CustomUserDetailsService userDetailsService;
//
//    // Secret key for HS256 algorithm
//    @Value("${app.jwtSecret}")
//    private String jwtSecret;
//
//    // Token validity in milliseconds (e.g., 3600000 ms = 1 hour)
//    @Value("${app.jwtExpirationInMs:3600000}")
//    private long jwtExpirationInMs;
//
//    public JwtTokenProvider(CustomUserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    // Generate JWT token
//    public String generateToken(Authentication authentication) throws JOSEException {
//        String username = authentication.getName();
//
//        // Set token claims
//        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
//                .subject(username)
//                .issueTime(new Date())
//                .expirationTime(new Date(System.currentTimeMillis() + jwtExpirationInMs))
//                .build();
//
//        // Create the JWS header with HS256 algorithm
//        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
//
//        // Create the signed JWT
//        SignedJWT signedJWT = new SignedJWT(header, claimsSet);
//
//        // Create HMAC signer
//        JWSSigner signer = new MACSigner(jwtSecret.getBytes());
//
//        // Sign the token
//        signedJWT.sign(signer);
//
//        // Serialize the token to a compact form
//        return signedJWT.serialize();
//    }
//
//    // Validate the token
//    public boolean validateToken(String token) {
//        try {
//            SignedJWT signedJWT = SignedJWT.parse(token);
//
//            // Create HMAC verifier
//            JWSVerifier verifier = new MACVerifier(jwtSecret.getBytes());
//
//            // Verify the signature
//            if (!signedJWT.verify(verifier)) {
//                return false;
//            }
//
//            // Check token expiration
//            Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
//            return expirationTime != null && expirationTime.after(new Date());
//        } catch (Exception ex) {
//            // Log the exception (e.g., parsing error, JOSEException)
//            // ex.printStackTrace(); // Uncomment for debugging
//            return false;
//        }
//    }
//
//    // Get authentication from token
//    public Authentication getAuthentication(String token) throws Exception {
//        SignedJWT signedJWT = SignedJWT.parse(token);
//        String username = signedJWT.getJWTClaimsSet().getSubject();
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        return new UsernamePasswordAuthenticationToken(
//                userDetails, null, userDetails.getAuthorities());
//    }
//
//    // Resolve token from request
//    public String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (org.springframework.util.StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
}
