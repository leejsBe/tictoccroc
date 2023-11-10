package kr.co.tictoccroc.domain.service;

import kr.co.tictoccroc.domain.dto.book.BookReq;
import kr.co.tictoccroc.domain.exception.BookException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BookServiceTest {

  @Autowired
  public BookService bookService;


  @Test
  @DisplayName("당일 예약 테스트")
  public void fail() {
    BookReq bookReq = BookReq.builder()
      .storeLessonId(1)
      .parentId(1)
      .lessonDay(LocalDate.now())
      .count(1)
      .build();

    assertThrows(BookException.class, () -> {
      bookService.book(bookReq);
    });
  }

}