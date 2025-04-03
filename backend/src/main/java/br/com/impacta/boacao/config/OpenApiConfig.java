package br.com.impacta.boacao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Boação API")
            .version("1.0")
            .description(
                "API para gerenciar voluntários, ONGs, atividades e suas interações,.")
            .license(new io.swagger.v3.oas.models.info.License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT")));
  }
}
