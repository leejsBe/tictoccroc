package kr.co.moin.domain.service;

import kr.co.moin.domain.dto.TransferListRes;
import kr.co.moin.domain.enumeration.Currency;
import kr.co.moin.domain.enumeration.IdType;
import kr.co.moin.domain.model.Member;
import kr.co.moin.domain.model.Request;
import kr.co.moin.domain.repository.MemberRepo;
import kr.co.moin.domain.repository.RequestRepo;
import kr.co.moin.support.WithMockCustomUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class TransferListServiceTest {

  @Autowired
  public MemberRepo memberRepo;

  @Autowired
  public RequestRepo requestRepo;

  @Autowired
  public TransferListService transferListService;


  @Test
  @WithMockCustomUser.WithMockUser(userId = "stest")
  void historyTest() {
    Member member = memberRepo.save(Member.builder()
      .userId("stest")
      .name("tes")
      .password("1234")
      .idType(IdType.REG_NO)
      .idValue("5456468446")
      .build());

    requestRepo.save(Request.builder()
      .exchangeRate(9.3625)
      .fee(5000)
      .requestedDate(LocalDateTime.now())
      .sourceAmount(400000)
      .targetAmount(42190)
      .targetCurrency(Currency.JPY.name())
      .usdAmount(273.45)
      .usdExchangeRate(1444.5)
      .member(member)
      .build());

    requestRepo.save(Request.builder()
      .exchangeRate(1301.01)
      .fee(3000)
      .requestedDate(LocalDateTime.now())
      .sourceAmount(400000)
      .targetAmount(305.14)
      .targetCurrency(Currency.USD.name())
      .usdAmount(305.14)
      .usdExchangeRate(1301.01)
      .member(member)
      .build());

    requestRepo.save(Request.builder()
      .exchangeRate(1317.00)
      .fee(3000)
      .requestedDate(LocalDateTime.now())
      .sourceAmount(605000)
      .targetAmount(457.10)
      .targetCurrency(Currency.USD.name())
      .usdAmount(457.10)
      .usdExchangeRate(1317.00)
      .member(member)
      .build());

    TransferListRes res = transferListService.request();

    System.out.println(res.toString());

  }


}