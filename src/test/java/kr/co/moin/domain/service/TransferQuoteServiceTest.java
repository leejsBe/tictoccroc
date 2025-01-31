package kr.co.moin.domain.service;

import kr.co.moin.domain.dto.MemberSignUpReq;
import kr.co.moin.domain.dto.TransferQuoteReq;
import kr.co.moin.domain.enumeration.Currency;
import kr.co.moin.domain.enumeration.IdType;
import kr.co.moin.support.WithMockCustomUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class TransferQuoteServiceTest {

  @Autowired
  public MemberSignUpService memberSignUpService;

  @Autowired
  public TransferQuoteService transferQuoteService;


  @Test
  @Transactional
  @WithMockCustomUser.WithMockUser(userId = "sample@gmail.com")
  void transferQuoteTest() {
    MemberSignUpReq memberSignUpReq = MemberSignUpReq.builder()
      .userid("sample@gmail.com")
      .password("1234")
      .name("테스")
      .idType(IdType.REG_NO)
      .idValue("90464-2112323")
      .build();
    memberSignUpService.signup(memberSignUpReq);



    TransferQuoteReq req = TransferQuoteReq.builder()
      .amount(10000)
      .targetCurrency(Currency.JPY)
      .build();

    transferQuoteService.quote(req);

  }
}