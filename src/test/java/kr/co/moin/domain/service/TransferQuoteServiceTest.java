package kr.co.moin.domain.service;

import kr.co.moin.domain.dto.MemberSignUpReq;
import kr.co.moin.domain.dto.TransferQuoteReq;
import kr.co.moin.domain.enumeration.Currency;
import kr.co.moin.domain.enumeration.IdType;
import kr.co.moin.infra.exchange.dto.ExchangeInfoToApiRes;
import kr.co.moin.support.WithMockCustomUser;
import org.junit.jupiter.api.Assertions;
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


  @Test
  void calcExchangeRateTest() {
    ExchangeInfoToApiRes jpy = ExchangeInfoToApiRes.builder()
      .code("FRX.KRWJPY")
      .currencyCode("JPY")
      .basePrice(907.98)
      .currencyUnit(100)
      .build();

    Assertions.assertEquals(9.0798, transferQuoteService.calcExchangeRate(jpy));


    ExchangeInfoToApiRes usd = ExchangeInfoToApiRes.builder()
      .code("FRX.KRWUSD")
      .currencyCode("USD")
      .basePrice(1302.00)
      .currencyUnit(1)
      .build();

    Assertions.assertEquals(1302.00, transferQuoteService.calcExchangeRate(usd));
  }


  @Test
  void calcFeeTest() {

    Currency currency = Currency.USD;
    long sourceAmount = 400000;
    /*
     * 수수료 = 보내는금액(amount) * 수수료율 + 고정수수료
     * 400000 * 0.002 + 1000
     * */

    Assertions.assertEquals(1800, transferQuoteService.calcFee(currency, sourceAmount));
  }

  @Test
  void calcTargetAmountTest() {
    Currency currency = Currency.USD;
    long originAmount = 400000 - 1800;
    double exchangeRate = 1444.01;

    /*
     * 받는 금액 = (보내는 금액 - 수수료) / 환율D
     * (400000 - 1800) / 1444.01
     * */

    Assertions.assertEquals(275.76, transferQuoteService.calcTargetAmount(currency, originAmount, exchangeRate));

  }

}