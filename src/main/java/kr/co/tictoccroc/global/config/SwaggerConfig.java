package kr.co.tictoccroc.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI bookingAPIConfig() {

    Contact contact = new Contact();

    Info info = new Info()
      .title("ㅉㄲㅇㅇ")
      .version("1.0")
      .contact(contact)
      .description("")
      .license(new License().name("Terms of service").url(""));

    return new OpenAPI()
      .info(info);
  }


  @Bean
  public GroupedOpenApi allApi() {
    return GroupedOpenApi.builder()
      .group("All")
      .pathsToExclude("/tictoccroc/**", "/tictoccroc/**")
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
