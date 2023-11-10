package kr.co.tictoccroc.domain.service;

import kr.co.tictoccroc.domain.dto.book.BookReq;
import kr.co.tictoccroc.domain.dto.book.BookRes;
import kr.co.tictoccroc.domain.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepo bookRepo;

  public BookRes book(BookReq bookReq) {


    return null;
  }


  private void validation() {
    /// 당일 예약 X
    /// 과거 예약 X
    /// 현재일로 부터 14일 이전 일정체크
    /// 동일인이 동일매장, 동일수업 중복 예약 체크

  }

}
