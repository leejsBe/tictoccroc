package kr.co.tictoccroc.global.exception;

import kr.co.tictoccroc.global.dto.ApiErrorRes;
import kr.co.tictoccroc.global.enumeration.ErrorCode;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(1)
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    return new ResponseEntity<>(ApiErrorRes.builder()
      .status(status)
      .message(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage())
      .code(ErrorCode.VALID_ERROR.getCode()).build(), status);
  }

}
