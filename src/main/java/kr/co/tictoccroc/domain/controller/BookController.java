package kr.co.tictoccroc.domain.controller;

import kr.co.tictoccroc.domain.dto.book.BookRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/tictoccroc")
@RequiredArgsConstructor
public class BookController {


  /**
   * 예약
   */
  @PostMapping("/book")
  public ResponseEntity<BookRes> book() {
    return ResponseEntity.ok(BookRes.builder().build());
  }

}
