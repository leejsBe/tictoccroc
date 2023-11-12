package kr.co.tictoccroc.domain.controller;

import kr.co.tictoccroc.domain.dto.verify.VerifyBookerReq;
import kr.co.tictoccroc.domain.dto.verify.VerifyBookerRes;
import kr.co.tictoccroc.domain.dto.verify.VerifyHistoryReq;
import kr.co.tictoccroc.domain.dto.verify.VerifyHistoryRes;
import kr.co.tictoccroc.domain.service.VerifyBookerService;
import kr.co.tictoccroc.domain.service.VerifyHistoryService;
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

  private final VerifyBookerService verifyBookerService;
  private final VerifyHistoryService verifyHistoryService;


  /**
   * 예약자 현황 (매장별)
   */
  @GetMapping("/store/{storeId}/booker")
  public ResponseEntity<VerifyBookerRes> bookerByStore(@PathVariable("storeId") long storeId) {
    VerifyBookerReq req = VerifyBookerReq.builder().storeId(storeId).build();
    return ResponseEntity.ok(verifyBookerService.verify(req));
  }


  /**
   * 예약자 현황 (수업별)
   */
  @GetMapping("/lesson/{lessonId}/booker")
  public ResponseEntity<VerifyBookerRes> bookerByLesson(@PathVariable("lessonId") long lessonId) {
    VerifyBookerReq req = VerifyBookerReq.builder().lessonId(lessonId).build();
    return ResponseEntity.ok(verifyBookerService.verify(req));
  }


  /**
   * 예약자 현황 (매장별)
   */
  @GetMapping("/store/{storeId}/book")
  public ResponseEntity<VerifyHistoryRes> bookByStore(@PathVariable("storeId") long storeId) {
    VerifyHistoryReq req = VerifyHistoryReq.builder().storeId(storeId).build();
    return ResponseEntity.ok(verifyHistoryService.search(req));
  }


  /**
   * 예약자 현황 (수업별)
   */
  @GetMapping("/lesson/{lessonId}/book")
  public ResponseEntity<VerifyHistoryRes> bookByLesson(@PathVariable("lessonId") long lessonId) {
    VerifyHistoryReq req = VerifyHistoryReq.builder().lessonId(lessonId).build();
    return ResponseEntity.ok(verifyHistoryService.search(req));
  }


}
