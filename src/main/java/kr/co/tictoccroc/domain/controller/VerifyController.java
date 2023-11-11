package kr.co.tictoccroc.domain.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public ResponseEntity<?> bookerByStore(@PathVariable("storeId") long storeId) {
    return ResponseEntity.ok(null);
  }


  /**
   * 예약자 현황 (수업별)
   */
  @GetMapping("/lesson/{lessonId}/booker")
  public ResponseEntity<?> bookerByLesson(@PathVariable("lessonId") long lessonId) {
    return ResponseEntity.ok(null);
  }


  /**
   * 예약자 현황 (매장별)
   */
  @GetMapping("/store/{storeId}/book")
  public ResponseEntity<?> bookByStore(@PathVariable("storeId") long storeId) {
    return ResponseEntity.ok(null);
  }


  /**
   * 예약자 현황 (수업별)
   */
  @GetMapping("/lesson/{lessonId}/book")
  public ResponseEntity<?> bookByLesson(@PathVariable("lessonId") long lessonId) {
    return ResponseEntity.ok(null);
  }


}
