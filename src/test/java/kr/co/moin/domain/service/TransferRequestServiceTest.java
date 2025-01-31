package kr.co.moin.domain.service;

import kr.co.moin.domain.dto.MemberSignUpReq;
import kr.co.moin.domain.dto.TransferQuoteReq;
import kr.co.moin.domain.dto.TransferQuoteRes;
import kr.co.moin.domain.dto.TransferReq;
import kr.co.moin.domain.enumeration.Currency;
import kr.co.moin.domain.enumeration.IdType;
import kr.co.moin.domain.model.Member;
import kr.co.moin.domain.model.Quote;
import kr.co.moin.support.WithMockCustomUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
class TransferRequestServiceTest {

  @Autowired
  public MemberSignUpService memberSignUpService;

  @Autowired
  public TransferQuoteService transferQuoteService;


  @Autowired
  public TransferRequestService transferRequestService;

  @Test
  @Transactional
  @WithMockCustomUser.WithMockUser(userId = "sample@gmail.com")
  void requestTest() {
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
    TransferQuoteRes transferQuoteRes = transferQuoteService.quote(req);


    transferRequestService.request(TransferReq.builder().quoteId(transferQuoteRes.getQuote().getQuoteId()).build());

  }


  @Test
  void validationUSDTest() {
    Member memberSignUpReq = Member.builder()
      .userId("sample@gmail.com")
      .password("1234")
      .name("테스")
      .idType(IdType.REG_NO)
      .idValue("90464-2112323")
      .build();

    Member memberBusinessSignUpReq = Member.builder()
      .userId("sample@gmail.com")
      .password("1234")
      .name("테스")
      .idType(IdType.BUSINESS_NO)
      .idValue("554-34234234")
      .build();

    Quote req = Quote.builder()
      .expireTime(LocalDateTime.now().plusMinutes(5))
      .usdAmount(999)
      .build();
    transferRequestService.validation(memberSignUpReq, req);

    Quote overThousandReq = Quote.builder()
      .expireTime(LocalDateTime.now().plusMinutes(5))
      .usdAmount(1000)
      .build();
    Assertions.assertThrows(RuntimeException.class, () -> {
      transferRequestService.validation(memberSignUpReq, overThousandReq);
    });

    Quote businessReq = Quote.builder()
      .expireTime(LocalDateTime.now().plusMinutes(5))
      .usdAmount(4999)
      .build();
    transferRequestService.validation(memberBusinessSignUpReq, businessReq);

  }

}