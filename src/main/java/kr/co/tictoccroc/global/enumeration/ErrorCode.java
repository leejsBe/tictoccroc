package kr.co.tictoccroc.global.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  VALID_ERROR("E900", HttpStatus.BAD_REQUEST, ""),
  DB_ERROR("E901", HttpStatus.FORBIDDEN, "DB ERROR")
  ;


  private final String code;
  private final HttpStatus httpStatus;
  private final String msg;

}
