package kr.co.tictoccroc.domain.enumeration;

import kr.co.tictoccroc.global.enumeration.ResCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BookResCode implements ResCode {

  BOOK_TODAY_FAIL("B001", "당일 예약은 불가능 합니다."),
  NOT_FOUND_STORE_LESSON("B002", "해당 매장의 수업을 찾지 못했습니다."),
  BOOK_PAST_DAY_FAIL("B003", "과거 예약은 불가능 합니다."),
  BOOK_RESERVE_TIME_FAIL("B004", "예약 불가능 시간입니다."),
  BOOK_DUPLICATION_FAIL("B005", "동일 매장, 동일 수업에 중복 예약 불가능 합니다."),
  BOOK_MAX_COUNT_FAIL("B006", "수업 최대 인원수를 초과하여 예약이 불가능 합니다.")

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
