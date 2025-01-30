package kr.co.moin.infra.exchange.service;

import kr.co.moin.infra.exchange.dto.ExchangeInfoToApiRes;
import kr.co.moin.infra.exchange.dto.ExchangeInfoToApiVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ExchangeInfoToApiServiceTest {


  @Autowired
  public ExchangeInfoToApiService exchangeInfoToApiService;


  @Test
  void success() {
    List<ExchangeInfoToApiRes> resList = exchangeInfoToApiService.search(new ExchangeInfoToApiVo());

    assertTrue(resList.size() > 0);
  }

}