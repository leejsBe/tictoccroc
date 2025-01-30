package kr.co.moin.infra.common.service;


import kr.co.moin.infra.common.dto.ApiReq;
import kr.co.moin.infra.enumeration.InfraErrorCode;
import kr.co.moin.infra.exception.InfraException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ApiService {

  org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ApiService.class);

  String API_REQUEST_PREFIX = ":: API REQUEST ::";
  String API_REQUEST_HEADER_PREFIX = ":: API REQUEST HEADER ::";
  String API_REQUEST_BODY_PREFIX = ":: API REQUEST BODY ::";
  String API_RESPONSE_PREFIX = ":: API RESPONSE ::";
  String API_RESPONSE_BODY_PREFIX = ":: API RESPONSE BODY ::";

  /**
   * base url 세팅
   */
  default String baseUrl() {
    return "";
  }

  /**
   * 요청 헤더 세팅
   */
  default void makeHeaders(HttpHeaders headers) {
  }


  /**
   * 요청 body 세팅
   * body 가 존재할때 history 에 입력하려면 필수
   */
  default Object makeBody(ApiReq apiReq) {
    return null;
  }


  /**
   * webClient setting
   */
  default WebClient mutateWebClient(WebClient webClient, ApiReq apiReq) {
    return webClient.mutate()
      .baseUrl(baseUrl())
      .defaultHeaders(this::makeHeaders)
      .filters(exchangeFilterFunctions -> this.filter(exchangeFilterFunctions, apiReq))
      .build();
  }


  /**
   * 요청 필터 세팅
   */
  default void filter(List<ExchangeFilterFunction> exchangeFilterFunctions, ApiReq apiReq) {
    exchangeFilterFunctions.add(
      ExchangeFilterFunction.ofRequestProcessor(
        request -> {
          log.info(API_REQUEST_PREFIX + " {} {}", request.method(), request.url());
          request.headers().forEach((name, values) -> values.forEach(value -> log.info(API_REQUEST_HEADER_PREFIX + " {}={}", name, value)));
          log.info(API_REQUEST_BODY_PREFIX + " {}", apiReq.toString());
          return Mono.just(request);
        }
      )
    );
    exchangeFilterFunctions.add(ExchangeFilterFunction.ofResponseProcessor(
      response -> {
        log.debug(API_RESPONSE_PREFIX + " {} ({})", response.statusCode().value(), response.statusCode().value());
        return Mono.just(response);
      }
    ));
  }


  /**
   * GET setting
   */
  default Mono<String> get(WebClient webClient, ApiReq apiReq) {
    return mutateWebClient(webClient, apiReq)
      .get()
      .uri(apiReq.getUrl())
      .retrieve()
      .onStatus(httpStatus -> !httpStatus.is2xxSuccessful(), clientResponse -> clientResponse.bodyToMono(String.class).map(Exception::new))
      .bodyToMono(String.class).defaultIfEmpty("")
      .timeout(apiReq.timeout())
      .onErrorMap(e -> {
        log.warn(e.getMessage());
        throw new InfraException(errorCode(), e.getMessage());
      });
  }

  /**
   * POST setting
   */
  default Mono<String> post(WebClient webClient, ApiReq apiReq) {
    return mutateWebClient(webClient, apiReq)
      .post()
      .uri(apiReq.getUrl())
      .bodyValue(makeBody(apiReq))
      .retrieve()
      .onStatus(httpStatus -> !httpStatus.is2xxSuccessful(), clientResponse -> clientResponse.bodyToMono(String.class).map(Exception::new))
      .bodyToMono(String.class).defaultIfEmpty("")
      .timeout(apiReq.timeout())
      .onErrorMap(e -> {
        log.warn(e.getMessage());
        throw new InfraException(errorCode(), e.getMessage());
      });
  }


  default InfraErrorCode errorCode() {
    return InfraErrorCode.API_REQUEST_ERROR;
  }

}
