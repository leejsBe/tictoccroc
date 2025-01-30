package kr.co.moin.global.exception;

import kr.co.moin.global.dto.GlobalErrorRes;
import kr.co.moin.global.dto.GlobalRes;
import kr.co.moin.global.enumeration.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  protected ResponseEntity<Object> buildResponseEntity(GlobalRes resultDto) {
    return ResponseEntity.ok(resultDto);
  }


  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    return new ResponseEntity<>(GlobalErrorRes.builder()
      .status(status)
      .message(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage())
      .code(ErrorCode.VALID_ERROR.getCode()).build(), status);
  }


  @ExceptionHandler(SQLException.class)
  protected ResponseEntity<Object> handleSQLException(SQLException ex) {
    log.info("-- SQLException 발생..!!", ex);
    return new ResponseEntity<>(GlobalErrorRes.builder()
      .status(HttpStatus.FORBIDDEN)
      .message(ErrorCode.DB_ERROR.getMsg())
      .code(ErrorCode.DB_ERROR.getCode()).build(), HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(NullPointerException.class)
  protected ResponseEntity<Object> handleNullPointException(NullPointerException ex) {
    log.info("-- NullPointException 발생..!!", ex);
    return new ResponseEntity<>(GlobalErrorRes.builder()
      .status(HttpStatus.FORBIDDEN)
      .message(ex.getMessage())
      .code(ErrorCode.VALID_ERROR.getCode()).build(), HttpStatus.FORBIDDEN);
  }


  @ExceptionHandler(GlobalException.class)
  protected ResponseEntity<Object> handleGlobalException(GlobalException ex) {
    log.info("-- GlobalException 발생..!!", ex);
    return new ResponseEntity<>(GlobalErrorRes.builder()
      .status(HttpStatus.FORBIDDEN)
      .message(ex.getMessage())
      .code(ErrorCode.VALID_ERROR.getCode()).build(), HttpStatus.FORBIDDEN);
  }


//  @ExceptionHandler(CustomAuthException.class)
//  protected ResponseEntity<Object> handleAuthenticationException(CustomAuthException ex) {
//    HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
//    ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
//
//    if (ex.getCause() instanceof AccessDeniedException) {
//      httpStatus = HttpStatus.FORBIDDEN;
//      errorCode = ErrorCode.FORBIDDEN;
//    }
//
//    return new ResponseEntity<>(GlobalErrorRes.builder()
//      .status(httpStatus)
//      .message(ex.getExtractMessage())
//      .code(errorCode.getCode())
//      .build(), httpStatus);
//  }

}
