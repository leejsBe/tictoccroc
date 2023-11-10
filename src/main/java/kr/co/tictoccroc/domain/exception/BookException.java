package kr.co.tictoccroc.domain.exception;

import kr.co.tictoccroc.global.enumeration.ResCode;
import kr.co.tictoccroc.global.exception.BasicException;

public class BookException extends BasicException {

  public BookException(ResCode resCode) {
    super(resCode);
  }

  public BookException(ResCode resCode, String mesg) {
    super(resCode, mesg);
  }
}
