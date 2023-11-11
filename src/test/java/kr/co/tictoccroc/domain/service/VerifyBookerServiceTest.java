package kr.co.tictoccroc.domain.service;

import kr.co.tictoccroc.domain.dto.verify.VerifyBookerReq;
import kr.co.tictoccroc.domain.enumeration.VerifyResCode;
import kr.co.tictoccroc.domain.exception.VerifyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class VerifyBookerServiceTest {

  @Autowired
  public VerifyBookerService verifyBookerService;

  @Test
  @Transactional
  @DisplayName("파라미터 무입력 검색 테스트")
  void fail() {
    VerifyBookerReq req = VerifyBookerReq.builder().build();

    Throwable exception = assertThrows(VerifyException.class, () -> {
      verifyBookerService.verify(req);
    });

    assertEquals(VerifyResCode.CONDITION_FAIL.getMsg(), exception.getMessage());
  }

  @Test
  @Transactional
  @DisplayName("파라미터 오류 테스트")
  void fail_2() {
    VerifyBookerReq req = VerifyBookerReq.builder().storeId(-2).build();

    Throwable exception = assertThrows(VerifyException.class, () -> {
      verifyBookerService.verify(req);
    });

    assertEquals(VerifyResCode.CONDITION_FAIL.getMsg(), exception.getMessage());
  }



}