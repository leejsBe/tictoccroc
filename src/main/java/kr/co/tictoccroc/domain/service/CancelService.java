package kr.co.tictoccroc.domain.service;

import kr.co.tictoccroc.domain.enumeration.CancelResCode;
import kr.co.tictoccroc.domain.exception.CancelException;
import kr.co.tictoccroc.domain.model.Book;
import kr.co.tictoccroc.domain.repository.BookRepo;
import kr.co.tictoccroc.global.enumeration.BookStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CancelService {


  private final BookRepo bookRepo;


  @Transactional
  public boolean cancel(long bookNo) {
    Book book = getBook(bookNo);

    /// 예약 상태 취소 변경
    book.changeStatus(BookStatus.CANCEL);

    log.info("취소 예약 번호 : " + book.getId());
    return true;
  }


  /**
   * 예약 조회
   */
  private Book getBook(long bookNo) {
    Book book = bookRepo.findById(bookNo).orElseThrow(() -> new CancelException(CancelResCode.NOT_FOUND_CANCEL));

    if (Objects.equals(book.getBookStatus(), BookStatus.CANCEL)) {
      throw new CancelException(CancelResCode.ALREADY_CANCEL);
    }

    return book;
  }

}
