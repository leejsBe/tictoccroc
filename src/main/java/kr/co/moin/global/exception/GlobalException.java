package kr.co.moin.global.exception;

import kr.co.moin.global.dto.ResCode;
import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {

  private final String code;

  public GlobalException(ResCode resCode) {
    super(resCode.msg());
    this.code = resCode.code();
  }

  public GlobalException(ResCode resCode, String msg) {
    super(resCode.msg() + "||" + msg);
    this.code = resCode.code();
  }

  public GlobalException(String code, String msg) {
    super(msg);
    this.code = code;
  }
}
