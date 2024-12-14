package et.hrms.config.security;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.UUID;

//@Slf4j
//@Configuration
//@RequiredArgsConstructor
public class CommonSecurityConfig {
//
//    @Value("${oauth2.token.access-token-validity:1800}")
//    private int accessTokenValidity;
//
//    @Value("${oauth2.token.refresh-token-validity:86400}")
//    private int refreshTokenValidity;
//
//    @Value("${oauth2.token.reuse-refresh-tokens:false}")
//    private boolean reuseRefreshTokens;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(13);
//    }
//
//    @Bean
//    public TokenSettings tokenSettings() {
//        return TokenSettings.builder()
//                .accessTokenTimeToLive(Duration.ofSeconds(accessTokenValidity))
//                .refreshTokenTimeToLive(Duration.ofSeconds(refreshTokenValidity))
//                .reuseRefreshTokens(reuseRefreshTokens)
//                .idTokenSignatureAlgorithm(SignatureAlgorithm.RS256)
//                .build();
//    }
//
//    @Bean
//    public JWKSource<SecurityContext> jwkSource() {
//        KeyPair keyPair = generateRsaKey();
//        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//
//        RSAKey rsaKey = new RSAKey.Builder(publicKey)
//                .privateKey(privateKey)
//                .keyID(UUID.randomUUID().toString())
//                .keyUse(KeyUse.SIGNATURE)
//                .algorithm(JWSAlgorithm.RS256)
//                .build();
//
//        JWKSet jwkSet = new JWKSet(rsaKey);
//        return new ImmutableJWKSet<>(jwkSet);
//    }
//
//    private KeyPair generateRsaKey() {
//        try {
//            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//            keyPairGenerator.initialize(2048, new SecureRandom());
//            KeyPair keyPair = keyPairGenerator.generateKeyPair();
//            log.debug("Generated new RSA key pair with ID: {}", UUID.randomUUID());
//            return keyPair;
//        } catch (Exception ex) {
//            log.error("Failed to generate RSA key pair: {}", ex.getMessage());
//            throw new SecurityException("Could not generate RSA key pair", ex);
//        }
//    }
}