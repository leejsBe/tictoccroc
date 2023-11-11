package kr.co.tictoccroc.domain.service;

import kr.co.tictoccroc.domain.dto.verify.VerifyBookerReq;
import kr.co.tictoccroc.domain.dto.verify.VerifyBookerRes;
import kr.co.tictoccroc.domain.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyBookerService {


  private final BookRepo bookRepo;


  public VerifyBookerRes verify(VerifyBookerReq verifyBookerReq) {


    return null;
  }

}
