package kr.co.moin.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@Configuration
public class WebClientConfig {


  @Bean
  public WebClient myWebClient() {
    HttpClient httpClient = HttpClient.create(
      ConnectionProvider.builder("myConnection")
        .maxConnections(200)
        .pendingAcquireTimeout(Duration.ofSeconds(60))
        .build());

    return WebClient.builder()
      .clientConnector(new ReactorClientHttpConnector(httpClient))
      .exchangeStrategies(ExchangeStrategies.builder()
        .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(-1)) // to unlimited memory size
        .build())
      .build();
  }


}
