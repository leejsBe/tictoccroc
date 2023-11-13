package kr.co.tictoccroc.domain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.tictoccroc.domain.service.CancelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Cancel Controller", description = "예약 취소 컨트롤러")
@Slf4j
@RestController
@RequestMapping("/tictoccroc")
@RequiredArgsConstructor
public class CancelController {

  private final CancelService cancelService;

  /**
   * 예약 취소
   */
  @Operation(summary = "book cancel", description = "예약 취소")
  @PutMapping("/book/{bookNo}")
  public ResponseEntity<Boolean> cancel(@PathVariable("bookNo") long bookNo) {
    return ResponseEntity.ok(cancelService.cancel(bookNo));
  }

}
