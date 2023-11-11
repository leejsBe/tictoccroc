package kr.co.tictoccroc.domain.service;

import kr.co.tictoccroc.domain.dto.book.BookReq;
import kr.co.tictoccroc.domain.enumeration.BookResCode;
import kr.co.tictoccroc.domain.exception.BookException;
import kr.co.tictoccroc.domain.model.Parent;
import kr.co.tictoccroc.domain.model.StoreLesson;
import kr.co.tictoccroc.domain.repository.ParentRepo;
import kr.co.tictoccroc.domain.repository.StoreLessonRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BookServiceTest {

  @Autowired
  public StoreLessonRepo storeLessonRepo;

  @Autowired
  public ParentRepo parentRepo;

  @Autowired
  public BookService bookService;


  @Test
  @Transactional
  @DisplayName("당일 예약 테스트")
  void fail() {
    List<StoreLesson> storeLessons = storeLessonRepo.findByLessonDayToday(LocalDate.now());
    StoreLesson storeLesson = storeLessons.get(0);

    List<Parent> parent = parentRepo.findAll();

    BookReq bookReq = BookReq.builder()
      .storeLessonId(storeLesson.getId())
      .parentId(parent.get(0).getId())
      .count(1)
      .build();

    Throwable exception = assertThrows(BookException.class, () -> {
      bookService.book(bookReq);
    });

    assertEquals(BookResCode.BOOK_TODAY_FAIL.getMsg(), exception.getMessage());
  }


  @Test
  @Transactional
  @DisplayName("과거 예약 테스트")
  void fail_2() {
    List<StoreLesson> storeLessons = storeLessonRepo.findByLessonDayPast(LocalDate.now());
    StoreLesson storeLesson = storeLessons.get(0);

    List<Parent> parent = parentRepo.findAll();

    BookReq bookReq = BookReq.builder()
      .storeLessonId(storeLesson.getId())
      .parentId(parent.get(0).getId())
      .count(1)
      .build();

    Throwable exception = assertThrows(BookException.class, () -> {
      bookService.book(bookReq);
    });

    assertEquals(BookResCode.BOOK_PAST_DAY_FAIL.getMsg(), exception.getMessage());
  }


  @Test
  @Transactional
  @DisplayName("14일 이후 예약 테스트")
  void fail_3() {
    List<StoreLesson> storeLessons = storeLessonRepo.findByLessonDayFuture(LocalDate.now().plusDays(14));
    StoreLesson storeLesson = storeLessons.get(0);

    List<Parent> parent = parentRepo.findAll();

    BookReq bookReq = BookReq.builder()
      .storeLessonId(storeLesson.getId())
      .parentId(parent.get(0).getId())
      .count(1)
      .build();

    Throwable exception = assertThrows(BookException.class, () -> {
      bookService.book(bookReq);
    });

    assertEquals(BookResCode.BOOK_RESERVE_TIME_FAIL.getMsg(), exception.getMessage());
  }

}