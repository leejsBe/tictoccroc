package kr.co.tictoccroc.domain.enumeration;

import kr.co.tictoccroc.global.enumeration.ResCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CancelResCode implements ResCode {

  NOT_FOUND_CANCEL("C001", "예약을 찾지 못하였습니다."),
  ALREADY_CANCEL("C002", "예약이 이미 취소되었습니다."),
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
