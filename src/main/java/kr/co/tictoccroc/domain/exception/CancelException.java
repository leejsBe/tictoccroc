package kr.co.tictoccroc.domain.exception;

import kr.co.tictoccroc.global.enumeration.ResCode;
import kr.co.tictoccroc.global.exception.BasicException;

public class CancelException extends BasicException {

  public CancelException(ResCode resCode) {
    super(resCode);
  }

  public CancelException(ResCode resCode, String mesg) {
    super(resCode, mesg);
  }
}
