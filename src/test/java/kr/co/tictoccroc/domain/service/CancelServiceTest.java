package kr.co.tictoccroc.domain.service;

import kr.co.tictoccroc.domain.dto.book.BookReq;
import kr.co.tictoccroc.domain.dto.book.BookRes;
import kr.co.tictoccroc.domain.enumeration.CancelResCode;
import kr.co.tictoccroc.domain.exception.CancelException;
import kr.co.tictoccroc.domain.model.Parent;
import kr.co.tictoccroc.domain.model.StoreLesson;
import kr.co.tictoccroc.domain.repository.BookRepo;
import kr.co.tictoccroc.domain.repository.ParentRepo;
import kr.co.tictoccroc.domain.repository.StoreLessonRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CancelServiceTest {

  @Autowired
  public StoreLessonRepo storeLessonRepo;

  @Autowired
  public ParentRepo parentRepo;

  @Autowired
  public BookService bookService;

  @Autowired
  public CancelService cancelService;

  @Autowired
  public BookRepo bookRepo;


  @Test
  @Transactional
  @DisplayName("예약 없는 취소 테스트")
  void fail() {
    Throwable exception = assertThrows(CancelException.class, () -> {
      cancelService.cancel(10023);
    });

    assertEquals(CancelResCode.NOT_FOUND_CANCEL.getMsg(), exception.getMessage());
  }


  @Test
  @Transactional
  @DisplayName("예약 취소 성공 테스트")
  void success() {
    List<StoreLesson> storeLessons = storeLessonRepo.findByLessonDayFuture(LocalDate.now());
    StoreLesson storeLesson = storeLessons.get(0);

    List<Parent> parent = parentRepo.findAll();
    BookReq bookReq = BookReq.builder()
      .storeLessonId(storeLesson.getId())
      .parentId(parent.get(0).getId())
      .count(storeLesson.getMaxCountByDay() / 2)
      .build();

    BookRes bookRes = bookService.book(bookReq);


    boolean result = cancelService.cancel(Long.parseLong(bookRes.getBookNo()));

    assertTrue(result);
  }


  @Test
  @Transactional
  @DisplayName("예약 취소 중복 테스트")
  void fail_2() {
    List<StoreLesson> storeLessons = storeLessonRepo.findByLessonDayFuture(LocalDate.now());
    StoreLesson storeLesson = storeLessons.get(0);

    List<Parent> parent = parentRepo.findAll();
    BookReq bookReq = BookReq.builder()
      .storeLessonId(storeLesson.getId())
      .parentId(parent.get(0).getId())
      .count(storeLesson.getMaxCountByDay() / 2)
      .build();

    BookRes bookRes = bookService.book(bookReq);


    boolean result = cancelService.cancel(Long.parseLong(bookRes.getBookNo()));
    assertTrue(result);


    Throwable exception = assertThrows(CancelException.class, () -> {
      cancelService.cancel(Long.parseLong(bookRes.getBookNo()));
    });
    assertEquals(CancelResCode.ALREADY_CANCEL.getMsg(), exception.getMessage());
  }
}