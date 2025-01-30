package kr.co.moin.infra.enumeration;

import kr.co.moin.global.dto.ResCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InfraErrorCode implements ResCode {

  API_REQUEST_ERROR(9000, "api 요청 에러"),
  API_RESPONSE_CONVERT_ERROR(9001, "api 요청 결과 변환 에러"),
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
