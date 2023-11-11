package kr.co.tictoccroc.domain.enumeration;

import kr.co.tictoccroc.global.enumeration.ResCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VerifyResCode implements ResCode {

  CONDITION_FAIL("V001", "예약 현황 요청이 파라미터가 잘못 되었습니다."),

  ;

  private final String code;
  private final String msg;

  @Override
  public String msg() {
    return this.msg;
  }

  @Override
  public String code() {
    return this.code;
  }
}
