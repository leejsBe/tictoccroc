package kr.co.tictoccroc.domain.enumeration;

import kr.co.tictoccroc.global.enumeration.ResCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BookResCode implements ResCode {

  BOOK_TODAY_FAIL("B001", "당일 예약은 불가능 합니다."),
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
