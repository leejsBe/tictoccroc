package kr.co.tictoccroc.global.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  VALID_ERROR("999", HttpStatus.BAD_REQUEST, ""),
    ;


  private final String code;
  private final HttpStatus httpStatus;
  private final String msg;

}
