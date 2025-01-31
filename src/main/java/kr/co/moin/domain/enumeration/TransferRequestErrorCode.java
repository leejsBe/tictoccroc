package kr.co.moin.domain.enumeration;

import kr.co.moin.global.dto.ResCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransferRequestErrorCode implements ResCode {

  NOT_FOUND_QUOTE(6000, "견적서를 찾지 못했습니다"),
  EXPIRE_DATE(6001, "견적서의 만료시간이 지났습니다"),
  LIMIT_EXCESS(6002, "송금 한도를 초과했습니다")

  ;


  private final int code;
  private final String msg;


  @Override
  public int code() {
    return this.code;
  }

  @Override
  public String msg() {
    return this.msg;
  }
}
