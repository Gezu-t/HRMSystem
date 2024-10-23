package et.hrms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.UUID;

@Configuration
public class ClientConfig {

//    private final PasswordEncoder passwordEncoder;
//
//    public ClientConfig(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Bean
//    public RegisteredClientRepository registeredClientRepository() {
//        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("my-client-id")
//                .clientSecret(passwordEncoder().encode("my-client-secret"))
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST) // Use POST for client credentials
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//                .scope("read")
//                .scope("write")
//                .tokenSettings(tokenSettings())
//                .clientSettings(ClientSettings.builder()
//                        .requireAuthorizationConsent(false) // Set this to true if you need consent
//                        .build())
//                .build();
//
//        return new InMemoryRegisteredClientRepository(registeredClient);
//    }
//
//    @Bean
//    public TokenSettings tokenSettings() {
//        return TokenSettings.builder()
//                .accessTokenTimeToLive(Duration.ofMinutes(30)) // Set access token lifetime
//                .refreshTokenTimeToLive(Duration.ofDays(30)) // Set refresh token lifetime
//                .reuseRefreshTokens(false) // Prevent reuse of refresh tokens
//                .build();
//    }
//
//    // Define PasswordEncoder bean
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
