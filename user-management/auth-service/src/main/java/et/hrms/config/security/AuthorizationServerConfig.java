package et.hrms.config.security;

//@Slf4j
//@Configuration
public class AuthorizationServerConfig {


//    @Autowired
//    private CommonSecurityConfig commonSecurityConfig;
//
//    @Value("${spring.security.oauth2.client.registration.custom-client.client-id}")
//    private String clientId;
//
//    @Value("${spring.security.oauth2.client.registration.custom-client.client-secret}")
//    private String clientSecret;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
//
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/login").permitAll()
//                        .requestMatchers("/oauth2/token").permitAll()
//                        .requestMatchers("/oauth2/jwks").permitAll()
//                        .requestMatchers("/error").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers("/oauth2/token")
//                )
//                .addFilterBefore(new SecurityHeadersFilter(), UsernamePasswordAuthenticationFilter.class)
//                .addFilterAfter(new RateLimitFilter(), SecurityHeadersFilter.class)
//                .exceptionHandling(exceptions ->
//                        exceptions.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")));
////                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(commonSecurityConfig.jwtDecoder(commonSecurityConfig.jwkSource()))));
//
//        return http.build();
//    }
//
//
//
//    @Bean
//    public RegisteredClientRepository registeredClientRepository() {
//        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId(clientId)
//                .clientSecret(commonSecurityConfig.passwordEncoder().encode(clientSecret))
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .redirectUri("http://localhost:8084/swagger-ui/oauth2-redirect.html")
//                .scope("read")
//                .scope("write")
//                .tokenSettings(commonSecurityConfig.tokenSettings())
//                .clientSettings(ClientSettings.builder()
//                        .requireAuthorizationConsent(false)
//                        .requireProofKey(false) // Changed to false
//                        .build())
//                .build();
//
//        log.info("Registered client: {}", registeredClient.getClientId());
//        return new InMemoryRegisteredClientRepository(registeredClient);
//    }
//
//
//    @Bean
//    public AuthorizationServerSettings authorizationServerSettings() {
//        return AuthorizationServerSettings.builder()
//                .issuer("http://localhost:8084")
//                .authorizationEndpoint("/oauth2/authorize")
//                .tokenEndpoint("/oauth2/token")
//                .tokenIntrospectionEndpoint("/oauth2/introspect")
//                .tokenRevocationEndpoint("/oauth2/revoke")
//                .jwkSetEndpoint("/oauth2/jwks")
//                .oidcUserInfoEndpoint("/userinfo")
//                .build();
//    }
}