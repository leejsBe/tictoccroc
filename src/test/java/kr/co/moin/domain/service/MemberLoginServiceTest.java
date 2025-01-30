package kr.co.moin.domain.service;

import kr.co.moin.domain.dto.MemberLoginReq;
import kr.co.moin.domain.dto.MemberLoginRes;
import kr.co.moin.domain.dto.MemberSignUpReq;
import kr.co.moin.domain.enumeration.IdType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberLoginServiceTest {

  @Autowired
  public MemberSignUpService memberSignUpService;

  @Autowired
  public MemberLoginService memberLoginService;


  @Test
  void loginTest() {
    MemberSignUpReq req = MemberSignUpReq.builder()
      .userid("sample@gmail.com")
      .password("1234")
      .name("테스")
      .idType(IdType.REG_NO)
      .idValue("001123-311111")
      .build();

    memberSignUpService.signup(req);


    MemberLoginReq memberLoginReq = MemberLoginReq.builder().userid("sample@gmail.com").password("1234").build();
    MemberLoginRes login = memberLoginService.login(memberLoginReq);

    System.out.println(login.getToken());

  }


  @Test
  void loginFailTest() {
    MemberSignUpReq req = MemberSignUpReq.builder()
      .userid("sample@gmail.com")
      .password("1234")
      .name("테스")
      .idType(IdType.REG_NO)
      .idValue("001123-311111")
      .build();

    memberSignUpService.signup(req);


    MemberLoginReq memberLoginReq = MemberLoginReq.builder().userid("sample@gmail.com").password("44212").build();

    Assertions.assertThrows(RuntimeException.class, () -> {
      MemberLoginRes login = memberLoginService.login(memberLoginReq);
    });

  }

}