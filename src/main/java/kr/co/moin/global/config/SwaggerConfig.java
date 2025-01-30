package kr.co.moin.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI APIConfig() {

    Contact contact = new Contact();

    Info info = new Info()
      .title("MOIN")
      .version("1.0")
      .contact(contact)
      .description("")
      .license(new License().name("Terms of service").url(""));

    SecurityScheme securityScheme = new SecurityScheme()
      .type(SecurityScheme.Type.HTTP)
      .scheme("bearer")
      .bearerFormat("JWT")
      .in(SecurityScheme.In.HEADER)
      .name("Authorization");

    Components authComponents = new Components().addSecuritySchemes("bearerAuth", securityScheme);
    SecurityRequirement schemaRequirement = new SecurityRequirement().addList("bearerAuth");

    return new OpenAPI()
      .info(info)
      .components(authComponents)
      .addSecurityItem(schemaRequirement);
  }


  @Bean
  public GroupedOpenApi userApi() {
    String[] paths = {"/v3/user/**"};
    String[] packagesToScan = {"kr.co.moin"};

    return GroupedOpenApi.builder()
      .group("All")
      .pathsToExclude(paths)
      .packagesToScan(packagesToScan)
      .build();
  }


  @Bean
  public GroupedOpenApi sampleApi() {
    return GroupedOpenApi.builder()
      .group("Test sample")
      .pathsToMatch("/test/**", "/test/**")
      .build();
  }
}
