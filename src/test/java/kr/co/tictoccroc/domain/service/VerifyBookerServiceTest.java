package kr.co.tictoccroc.domain.service;

import kr.co.tictoccroc.domain.dto.verify.VerifyBookerReq;
import kr.co.tictoccroc.domain.dto.verify.VerifyBookerRes;
import kr.co.tictoccroc.domain.enumeration.VerifyResCode;
import kr.co.tictoccroc.domain.exception.VerifyException;
import kr.co.tictoccroc.domain.model.Lesson;
import kr.co.tictoccroc.domain.model.Store;
import kr.co.tictoccroc.domain.repository.LessonRepo;
import kr.co.tictoccroc.domain.repository.StoreRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VerifyBookerServiceTest {

  @Autowired
  public VerifyBookerService verifyBookerService;

  @Autowired
  public StoreRepo storeRepo;

  @Autowired
  public LessonRepo lessonRepo;


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


  @Test
  @Transactional
  @DisplayName("매장별 예약자현황 테스트")
  void success() {
    List<Store> stores = storeRepo.findByDelAtIsNull();
    Store store = stores.get(0);

    VerifyBookerReq req = VerifyBookerReq.builder().storeId(store.getId()).build();

    VerifyBookerRes result = verifyBookerService.verify(req);

    assertNotNull(result);

    result.getStoreLessons().forEach(System.out::println);
  }


  @Test
  @Transactional
  @DisplayName("수업별 예약자현황 테스트")
  void success_2() {
    List<Lesson> lessons = lessonRepo.findAll();
    Lesson lesson = lessons.get(0);

    VerifyBookerReq req = VerifyBookerReq.builder().lessonId(lesson.getId()).build();

    VerifyBookerRes result = verifyBookerService.verify(req);

    assertNotNull(result);

    result.getStoreLessons().forEach(System.out::println);
  }
}