package kr.co.moin.global.exception;

import kr.co.moin.global.dto.ResCode;
import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {

  private final int code;
  private final String msg;

  public GlobalException(ResCode resCode) {
    super(resCode.msg());
    this.code = resCode.code();
    this.msg = resCode.msg();
  }

  public GlobalException(ResCode resCode, String msg) {
    super(resCode.msg() + "||" + msg);
    this.code = resCode.code();
    this.msg = resCode.msg();
  }

  public GlobalException(Throwable cause, String msg) {
    super(cause);
    this.code = 1000;
    this.msg = msg;
  }

}
