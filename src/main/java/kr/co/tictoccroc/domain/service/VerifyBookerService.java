package kr.co.tictoccroc.domain.service;

import kr.co.tictoccroc.domain.dto.verify.VerifyBookerReq;
import kr.co.tictoccroc.domain.dto.verify.VerifyBookerRes;
import kr.co.tictoccroc.domain.enumeration.VerifyResCode;
import kr.co.tictoccroc.domain.exception.VerifyException;
import kr.co.tictoccroc.domain.model.Book;
import kr.co.tictoccroc.domain.model.Parent;
import kr.co.tictoccroc.domain.repository.BookRepo;
import kr.co.tictoccroc.domain.repository.ParentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VerifyBookerService {


  private final BookRepo bookRepo;
  private final ParentRepo parentRepo;


  public VerifyBookerRes verify(VerifyBookerReq verifyBookerReq) {
    List<Book> books = getBooks(verifyBookerReq);

    Set<Long> parentIds = books.stream().map(book -> book.getParent().getId()).collect(Collectors.toSet());
    Map<Long, Parent> parentMap = getParents(parentIds);

    return new VerifyBookerRes(parentMap);
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


  /**
   * 부모 정보 조회
   */
  private Map<Long, Parent> getParents(Set<Long> parentIds) {
    return parentRepo.findById(parentIds.stream().toList()).stream().collect(Collectors.toMap(Parent::getId, Function.identity(), (t1, t2) -> t1));
  }
}
