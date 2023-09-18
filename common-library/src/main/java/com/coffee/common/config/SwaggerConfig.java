
/*
 * Copyright (c) 2023 FPT Software Co. Ltd
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * FPT Software Co. Ltd ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered
 * into with FPT Software Co. Ltd or its subsidiaries.
 */
package com.coffee.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    @Value("${springdoc.swagger-ui.server:}")
    String swaggerServer;

    @Value("${springdoc.token-url:}")
    String tokenUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl(this.swaggerServer);
        return new OpenAPI().servers(List.of(server))
                .components(new Components()
                        .addSecuritySchemes("oAuth2Password", createOAuthScheme()))
                .addSecurityItem(new SecurityRequirement().addList("oAuth2Password"));
    }

    @Bean
    public SecurityScheme createOAuthScheme() {
        OAuthFlows flows = createOAuthFlows();
        return new SecurityScheme()
                .type(SecurityScheme.Type.OAUTH2)
                .flows(flows);
    }

    private OAuthFlows createOAuthFlows() {
        OAuthFlow authorizationCodeFlow = createAuthorizationCodeFlow();
        return new OAuthFlows().password(authorizationCodeFlow);
    }

    private OAuthFlow createAuthorizationCodeFlow() {
        return new OAuthFlow().tokenUrl(tokenUrl);
    }
}
