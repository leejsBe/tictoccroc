package kr.co.moin.infra.exchange.service;

import kr.co.moin.infra.common.dto.ApiReq;
import kr.co.moin.infra.common.service.ApiService;
import kr.co.moin.infra.enumeration.InfraErrorCode;
import kr.co.moin.infra.exception.InfraException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeToApiService<T> implements ApiService {

  @Value("${exchange.domain}")
  protected String domain;


  /**
   * base url
   */
  @Override
  public String baseUrl() {
    return this.domain;
  }

  /**
   * 요청 헤더 세팅
   */
  @Override
  public void makeHeaders(HttpHeaders headers) {
    MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
    header.add("accept", MediaType.APPLICATION_JSON_VALUE);

    headers.addAll(header);
  }


  /**
   * GET setting
   */
  @Override
  public Mono<String> get(WebClient webClient, ApiReq apiReq) {
    return mutateWebClient(webClient, apiReq)
      .get()
      .uri(apiReq.getUrl())
      .retrieve()
      .onStatus(httpStatus -> !httpStatus.is2xxSuccessful(), clientResponse -> clientResponse.bodyToMono(String.class).map(Exception::new))
      .bodyToMono(String.class).defaultIfEmpty("")
      .timeout(apiReq.timeout())
      .onErrorMap(e -> {
        log.warn(e.getMessage());
        throw new InfraException(InfraErrorCode.API_REQUEST_ERROR, e.getMessage());
      });
  }


  /**
   * POST setting
   */
  @Override
  public Mono<String> post(WebClient webClient, ApiReq apiReq) {
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
        throw new InfraException(InfraErrorCode.API_REQUEST_ERROR, e.getMessage());
      });
  }


  /**
   * 결과값 포맷 변환
   */
  public T resFormatConvert(String resData) {
    log.info(API_RESPONSE_BODY_PREFIX + " {}", resData);
    return null;
  }

}
