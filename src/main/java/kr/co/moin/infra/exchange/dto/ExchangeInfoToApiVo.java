package kr.co.moin.infra.exchange.dto;

import kr.co.moin.infra.common.dto.ApiReq;
import kr.co.moin.infra.exchange.enumeration.ExchangeUrl;
import lombok.Getter;

@Getter
public class ExchangeInfoToApiVo implements ApiReq {


  @Override
  public String getUrl() {
    return ExchangeUrl.SEARCH_REPRESENT_PRODUCT_LIST.getUrl();
  }
}
