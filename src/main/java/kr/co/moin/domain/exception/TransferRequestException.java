package kr.co.moin.domain.exception;

import kr.co.moin.domain.enumeration.TransferRequestErrorCode;
import kr.co.moin.global.exception.GlobalException;
import lombok.Getter;

@Getter
public class TransferRequestException extends GlobalException {


  public TransferRequestException(TransferRequestErrorCode transferRequestErrorCode) {
    super(transferRequestErrorCode);
  }

}
