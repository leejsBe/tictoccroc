package kr.co.moin.infra.exchange.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExchangeUrl {

  SEARCH_REPRESENT_PRODUCT_LIST("/v1/forex/recent?codes=,FRX.KRWJPY,FRX.KRWUSD"),    /// 환율 정보 조회
  ;


  private final String url;
}
