package kr.co.tictoccroc.domain.exception;

import kr.co.tictoccroc.global.enumeration.ResCode;
import kr.co.tictoccroc.global.exception.BasicException;

public class VerifyException extends BasicException {

  public VerifyException(ResCode resCode) {
    super(resCode);
  }

  public VerifyException(ResCode resCode, String mesg) {
    super(resCode, mesg);
  }
}
