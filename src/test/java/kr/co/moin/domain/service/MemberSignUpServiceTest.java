package kr.co.moin.domain.service;

import kr.co.moin.domain.dto.MemberSignUpReq;
import kr.co.moin.domain.enumeration.IdType;
import kr.co.moin.domain.model.Member;
import kr.co.moin.domain.repository.MemberRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberSignUpServiceTest {

  @Autowired
  public MemberSignUpService memberSignUpService;

  @Autowired
  public MemberRepo memberRepo;

  @Test
  void signupTest() {
    MemberSignUpReq req = MemberSignUpReq.builder()
      .userid("sample@gmail.com")
      .password("1234")
      .name("테스")
      .idType(IdType.REG_NO)
      .idValue("001123-311111")
      .build();

    memberSignUpService.signup(req);


    Member member = memberRepo.findByUserId("sample@gmail.com").orElseThrow();

    System.out.println(member.getUserId());
    System.out.println(member.getName());
    System.out.println(member.getPassword());
    System.out.println(member.getIdType());
    System.out.println(member.getIdValue());
  }


  @Test
  void signupDoubleTest() {
    MemberSignUpReq req = MemberSignUpReq.builder()
      .userid("sample@gmail.com")
      .password("1234")
      .name("테스")
      .idType(IdType.REG_NO)
      .idValue("001123-311111")
      .build();

    memberSignUpService.signup(req);

    Assertions.assertThrows(RuntimeException.class, () -> {
      memberSignUpService.signup(req);
    });
  }
}