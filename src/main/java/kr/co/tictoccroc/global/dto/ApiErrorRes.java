package kr.co.tictoccroc.global.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiErrorRes {

  private final LocalDateTime timestamp = LocalDateTime.now();
  private final HttpStatusCode status;
  private final String message;
  private final String code;
}
