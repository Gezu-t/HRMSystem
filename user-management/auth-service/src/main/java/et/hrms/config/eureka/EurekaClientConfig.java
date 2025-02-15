package et.hrms.config.eureka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;

@Configuration
public class EurekaClientConfig {

    @Value("${eureka.client.username:eureka}")
    private String username;

    @Value("${eureka.client.password:password}")
    private String password;

    @Bean
    public RestTemplate eurekaRestTemplate() {
        // Create a client HTTP request interceptor to add authentication
        ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
            // Create the Basic auth header
            String credentials = username + ":" + password;
            String encodedCredentials = java.util.Base64.getEncoder()
                    .encodeToString(credentials.getBytes());

            // Add the Authorization header to each request
            request.getHeaders().add(
                    HttpHeaders.AUTHORIZATION,
                    "Basic " + encodedCredentials
            );

            // Execute the request with added authentication
            return execution.execute(request, body);
        };

        // Build RestTemplate with the authentication interceptor
        return new RestTemplateBuilder()
                .additionalInterceptors(interceptor)
                .build();
    }
}