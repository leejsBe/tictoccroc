package kr.co.tictoccroc.domain.service;

import kr.co.tictoccroc.domain.enumeration.CancelResCode;
import kr.co.tictoccroc.domain.exception.CancelException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class CancelServiceTest {

  @Autowired
  public CancelService cancelService;

  @Test
  @Transactional
  @DisplayName("예약 없는 취소 테스트")
  void fail() {
    Throwable exception = assertThrows(CancelException.class, () -> {
      cancelService.cancel(10023);
    });

    assertEquals(CancelResCode.NOT_FOUND_CANCEL.getMsg(), exception.getMessage());
  }
}