package et.hrms.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${server.port:8084}")
    private String serverPort;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .servers(servers())
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", securityScheme()))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .tags(apiTags());
    }

    private Info apiInfo() {
        return new Info()
                .title("HRMS Authentication Service API")
                .version("1.0.0")
                .description("Authentication and Authorization Service for HRMS")
                .contact(new Contact()
                        .name("HRMS Team")
                        .email("support@hrms.et")
                        .url("https://hrms.et"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0"));
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme()
                .name("bearerAuth")
                .description("JWT Authentication. Enter 'Bearer ' + your token")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
    }

    private List<Server> servers() {
        return Arrays.asList(
                new Server()
                        .url("http://localhost:" + serverPort)
                        .description("Local Development")
        );
    }

    private List<Tag> apiTags() {
        return Arrays.asList(
                new Tag().name("Authentication").description("Authentication endpoints"),
                new Tag().name("User Management").description("User management operations"),
                new Tag().name("Role Management").description("Role management operations")
        );
    }
}