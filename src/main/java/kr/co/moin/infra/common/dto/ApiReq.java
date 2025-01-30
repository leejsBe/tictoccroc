package kr.co.moin.infra.common.dto;

import java.time.Duration;

public interface ApiReq {

  String API_RESPONSE_PREFIX = ":: API RESPONSE ::";
  String API_ERROR_PREFIX = ":: API ERROR ::";

  String getUrl();

  /**
   * 요청 url 설정
   */
  default String makeUrl(String domain) {
    return domain + getUrl();
  }


  /**
   * 요청 바디 설정
   */
  default Object makeBody() {
    return null;
  }

  /**
   * 타임 아웃 설정
   */
  default Duration timeout() {
    return Duration.ofMillis(50000);
  }

}
