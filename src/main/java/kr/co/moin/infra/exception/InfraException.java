package kr.co.moin.infra.exception;

import kr.co.moin.global.exception.GlobalException;
import kr.co.moin.infra.enumeration.InfraErrorCode;
import lombok.Getter;

@Getter
public class InfraException extends GlobalException {


  public InfraException(InfraErrorCode infraErrorCode) {
    super(infraErrorCode);
  }

  public InfraException(InfraErrorCode infraErrorCode, String msg) {
    super(infraErrorCode, msg);
  }

}
