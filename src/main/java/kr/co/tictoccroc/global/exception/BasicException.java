package kr.co.tictoccroc.global.exception;

import kr.co.tictoccroc.global.enumeration.ResCode;
import lombok.Getter;

@Getter
public class BasicException extends RuntimeException {

  private final String code;

  public BasicException(ResCode resCode) {
    super(resCode.msg());
    this.code = resCode.code();
  }

  public BasicException(ResCode resCode, String msg) {
    super(msg);
    this.code = resCode.code();
  }
}
