package kr.co.tictoccroc.domain.controller;

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


  /**
   * 예약 취소
   */
  @PutMapping("/book/{bookNo}")
  public ResponseEntity<?> cancel(@PathVariable("bookNo") String bookNo) {
    return ResponseEntity.ok(null);
  }

}
