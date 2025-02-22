package com.gtltek.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtUtil {

    private final Key secretKey;
    private final long expirationTime;
    private final long refreshTokenExpirationTime;

    public JwtUtil(
            @Value("${spring.security.jwt.secret-key}") String secret,
            @Value("${spring.security.jwt.expiration}") long expirationTime,
            @Value("${spring.security.jwt.refresh-token.expiration}") long refreshTokenExpirationTime
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationTime = expirationTime;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
    }

    /**
     * Generates an access token for the given username.
     *
     * @param username the username for which the token is generated
     * @return a signed JWT as a String
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Generates a refresh token for the given username.
     *
     * @param username the username for which the refresh token is generated
     * @return a signed JWT as a String
     */
    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpirationTime))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validates the provided JWT.
     *
     * @param token the JWT to validate
     * @return true if valid; false otherwise
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            // Optionally log the exception for debugging:
            // logger.warn("JWT validation failed: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Extracts the username (subject) from the provided JWT.
     *
     * @param token the JWT from which to extract the username
     * @return the username as a String
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts a claim from the JWT using the provided claims resolver function.
     *
     * @param token          the JWT
     * @param claimsResolver a function to resolve a claim from the JWT claims
     * @param <T>            the type of the claim
     * @return the extracted claim
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    /**
     * Gets the configured access token expiration time.
     *
     * @return the expiration time in milliseconds
     */
    public long getExpirationTime() {
        return expirationTime;
    }
}
