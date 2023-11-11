package kr.co.tictoccroc.domain.service;

import kr.co.tictoccroc.domain.dto.verify.VerifyBookerReq;
import kr.co.tictoccroc.domain.dto.verify.VerifyBookerRes;
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
public class VerifyBookerService {


  private final BookRepo bookRepo;


  public VerifyBookerRes verify(VerifyBookerReq verifyBookerReq) {
    List<Book> books = getBooks(verifyBookerReq);

    return null;
  }


  /**
   * 예약현황 조회
   */
  private List<Book> getBooks(VerifyBookerReq verifyBookerReq) {
    if (verifyBookerReq.getLessonId() <= 0 && verifyBookerReq.getStoreId() <= 0) {
      throw new VerifyException(VerifyResCode.CONDITION_FAIL);
    }

    if (verifyBookerReq.getLessonId() != 0) {
      return bookRepo.findByLesson(verifyBookerReq.getLessonId());
    }

    if (verifyBookerReq.getStoreId() != 0) {
      return bookRepo.findByStore(verifyBookerReq.getStoreId());
    }

    return Collections.emptyList();
  }

}
