package kr.co.moin.global.enumeration;

import kr.co.moin.global.dto.ResCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode implements ResCode {

  VALID_ERROR(1510, "유효성 체크 오류"),
  DB_ERROR(1511, "DB 오류"),
  ENCRYPTION_DECRYPTION_ERROR(1512, "암복호화 오류"),
  NO_KEY_ERROR(1513, "암복호화 키 오류"),
  FAIL_API(1514, "API 요청 오류"),
  FAIL_LOGIN(1515, "로그인 실패"),

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
