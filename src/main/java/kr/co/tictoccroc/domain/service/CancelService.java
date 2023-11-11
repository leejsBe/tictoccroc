package kr.co.tictoccroc.domain.service;

import kr.co.tictoccroc.domain.enumeration.CancelResCode;
import kr.co.tictoccroc.domain.exception.CancelException;
import kr.co.tictoccroc.domain.model.Book;
import kr.co.tictoccroc.domain.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CancelService {


  private final BookRepo bookRepo;


  public boolean cancel(long bookNo) {
    Book book = bookRepo.findById(bookNo).orElseThrow(() -> new CancelException(CancelResCode.NOT_FOUND_CANCEL));


    return false;
  }

}
