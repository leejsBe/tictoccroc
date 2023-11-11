package kr.co.tictoccroc.domain.controller;

import kr.co.tictoccroc.domain.service.CancelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/tictoccroc")
@RequiredArgsConstructor
public class CancelController {

  private final CancelService cancelService;

  /**
   * 예약 취소
   */
  @PutMapping("/book/{bookNo}")
  public ResponseEntity<Boolean> cancel(@PathVariable("bookNo") long bookNo) {
    return ResponseEntity.ok(cancelService.cancel(bookNo));
  }

}
