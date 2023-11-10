package kr.co.tictoccroc.domain.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/tictoccroc")
@RequiredArgsConstructor
public class VerifyController {


  /**
   * 예약자 현황 (매장별)
   */
  @GetMapping("/store/{storeId}/booker")
  public ResponseEntity<?> bookerByStore() {
    return ResponseEntity.ok(null);
  }


  /**
   * 예약자 현황 (수업별)
   */
  @GetMapping("/lesson/{lessonId}/booker")
  public ResponseEntity<?> bookerByLesson() {
    return ResponseEntity.ok(null);
  }


  /**
   * 예약자 현황 (매장별)
   */
  @GetMapping("/store/{storeId}/book")
  public ResponseEntity<?> bookByStore() {
    return ResponseEntity.ok(null);
  }


  /**
   * 예약자 현황 (수업별)
   */
  @GetMapping("/lesson/{lessonId}/book")
  public ResponseEntity<?> bookByLesson() {
    return ResponseEntity.ok(null);
  }


}
