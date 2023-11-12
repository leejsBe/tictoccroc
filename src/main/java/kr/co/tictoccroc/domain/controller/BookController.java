package kr.co.tictoccroc.domain.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import kr.co.tictoccroc.domain.dto.book.BookReq;
import kr.co.tictoccroc.domain.dto.book.BookRes;
import kr.co.tictoccroc.domain.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/tictoccroc")
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  /**
   * 예약
   */
  @Operation(summary = "book", description = "예약 API")
  @PostMapping("/book")
  public ResponseEntity<BookRes> book(@RequestBody @Valid BookReq bookReq) {
    return ResponseEntity.ok(bookService.book(bookReq));
  }

}
