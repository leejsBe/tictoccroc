package kr.co.tictoccroc.domain.service;

import kr.co.tictoccroc.domain.dto.book.BookReq;
import kr.co.tictoccroc.domain.dto.book.BookRes;
import kr.co.tictoccroc.domain.enumeration.BookResCode;
import kr.co.tictoccroc.domain.exception.BookException;
import kr.co.tictoccroc.domain.model.Book;
import kr.co.tictoccroc.domain.model.StoreLesson;
import kr.co.tictoccroc.domain.repository.BookRepo;
import kr.co.tictoccroc.domain.repository.StoreLessonRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

  private final StoreLessonRepo storeLessonRepo;
  private final BookRepo bookRepo;


  public BookRes book(BookReq bookReq) {
    StoreLesson storeLesson = getStoreLesson(bookReq);
    List<Book> books = getBook(bookReq, storeLesson);

    save(storeLesson, bookReq);
    return null;
  }


  /**
   * 매장 수업 정보 조회
   */
  private StoreLesson getStoreLesson(BookReq bookReq) {
    StoreLesson storeLesson = storeLessonRepo.findById(bookReq.getStoreLessonId()).orElseThrow(() -> new BookException(BookResCode.NOT_FOUND_STORE_LESSON));
    validation(storeLesson);

    return storeLesson;
  }

  private void validation(StoreLesson storeLesson) {
    /// 당일 예약 X
    if (LocalDate.now().isEqual(storeLesson.getLessonDay())) {
      throw new BookException(BookResCode.BOOK_TODAY_FAIL);
    }

    /// 과거 예약 X
    if (LocalDate.now().isAfter(storeLesson.getLessonDay())) {
      throw new BookException(BookResCode.BOOK_PAST_DAY_FAIL);
    }

    /// 현재일로 부터 14일 이전 일정체크
    if (LocalDate.now().plusDays(14).isAfter(storeLesson.getLessonDay())) {
      throw new BookException(BookResCode.BOOK_RESERVE_TIME_FAIL);
    }

  }


  /**
   * 해당 수업의 예약리스트 조회
   */
  private List<Book> getBook(BookReq bookReq, StoreLesson storeLesson) {
    List<Book> books = Optional.ofNullable(bookRepo.findByStoreLessonAndParent(bookReq.getStoreLessonId())).orElse(new ArrayList<>());
    validation(books, bookReq, storeLesson);

    return books;
  }

  private void validation(List<Book> books, BookReq bookReq, StoreLesson storeLesson) {
    /// 동일인이 동일매장, 동일수업 중복 예약 체크
    Optional<Book> booked = books.stream().filter(book -> book.getStoreLesson().getId() == book.getStoreLesson().getId() && book.getParent().getId() == bookReq.getParentId()).findFirst();
    if (booked.isPresent()) {
      throw new BookException(BookResCode.BOOK_RESERVE_TIME_FAIL);
    }

    /// 최대 인원수 체크
    int bookedTotalCount = books.stream().map(Book::getCount).reduce(0, Integer::sum);
    if (storeLesson.getMaxCountByDay() < bookedTotalCount + bookReq.getCount()) {
      throw new BookException(BookResCode.BOOK_MAX_COUNT_FAIL);
    }
  }


  /**
   * 데이터 저장
   */
  private Book save(StoreLesson storeLesson, BookReq bookReq) {
    return null;
  }
}
