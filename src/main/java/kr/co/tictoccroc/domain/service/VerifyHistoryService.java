package kr.co.tictoccroc.domain.service;

import kr.co.tictoccroc.domain.dto.verify.VerifyHistoryReq;
import kr.co.tictoccroc.domain.dto.verify.VerifyHistoryRes;
import kr.co.tictoccroc.domain.enumeration.VerifyResCode;
import kr.co.tictoccroc.domain.exception.VerifyException;
import kr.co.tictoccroc.domain.model.Book;
import kr.co.tictoccroc.domain.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VerifyHistoryService {

  private final BookRepo bookRepo;


  public VerifyHistoryRes search(VerifyHistoryReq verifyHistoryReq) {
    List<Book> books = getBooks(verifyHistoryReq);
    return new VerifyHistoryRes(books);
  }


  /**
   * 예약현황 조회
   */
  private List<Book> getBooks(VerifyHistoryReq verifyHistoryReq) {
    if (verifyHistoryReq.getLessonId() <= 0 && verifyHistoryReq.getStoreId() <= 0) {
      throw new VerifyException(VerifyResCode.CONDITION_FAIL);
    }

    if (verifyHistoryReq.getLessonId() != 0) {
      return bookRepo.findByLessonIncludeCancel(verifyHistoryReq.getLessonId());
    }

    if (verifyHistoryReq.getStoreId() != 0) {
      return bookRepo.findByStoreIncludeCancel(verifyHistoryReq.getStoreId());
    }

    return Collections.emptyList();
  }

}
