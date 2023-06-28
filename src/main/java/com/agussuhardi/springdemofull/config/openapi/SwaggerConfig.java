package com.agussuhardi.springdemofull.config.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author agus.suhardii@gmail.com
 * @created 16/06/23/06/2023 :19.52
 * @project spring-demo-full
 * <p>
 * access swagger ui : <a href="http://localhost:8080/swagger-ui/index.html">Swagger UI</a>
 * export open api : <a http://localhost:8080/api-docs/any">export to postman</a>
 * generate swagger open api for collect rest api, you can export this api to postman
 */
@Component
@Configuration
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String appName;

    /**
     * Swagger config page and input Bearer token
     *
     * @return Swagger config Bean
     */
    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        final String apiTitle = String.format("%s API", StringUtils.capitalize(appName));
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")))
                .info(new Info().title(apiTitle).version("Dev"));
    }

    /**
     * any rest api with prefix /api
     *
     * @return prefix /api
     */
    @Bean
    public GroupedOpenApi anyApi() {
        String[] paths = {"/api/**"};
        return GroupedOpenApi.builder().group("any").pathsToMatch(paths).build();
    }


    /**
     * grouping api authentication
     *
     * @return grouping api Bean
     */
    @Bean
    public GroupedOpenApi authApi() {
        String[] paths = {"/api/**/auth/**"};
        return GroupedOpenApi.builder().group("auth").pathsToMatch(paths).build();
    }


    @Bean
    public GroupedOpenApi adminApi() {
        String[] paths = {"/admin/api/**"};
        return GroupedOpenApi.builder().group("ADMIN").pathsToMatch(paths).build();
    }

    @Bean
    public GroupedOpenApi customerApi() {
        String[] paths = {"/customer/api/**"};
        return GroupedOpenApi.builder().group("CUSTOMER").pathsToMatch(paths).build();
    }

    @Bean
    public GroupedOpenApi publicApi() {
        String[] paths = {"/api/**"};
        return GroupedOpenApi.builder().group("PUBLIC").pathsToMatch(paths).build();
    }

}
