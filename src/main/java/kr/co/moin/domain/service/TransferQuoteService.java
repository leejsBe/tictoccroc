package kr.co.moin.domain.service;

import kr.co.moin.domain.dto.TransferQuoteReq;
import kr.co.moin.domain.dto.TransferQuoteRes;
import kr.co.moin.domain.enumeration.Currency;
import kr.co.moin.domain.model.Member;
import kr.co.moin.domain.model.Quote;
import kr.co.moin.domain.repository.MemberRepo;
import kr.co.moin.domain.repository.QuoteRepo;
import kr.co.moin.global.enumeration.ErrorCode;
import kr.co.moin.global.exception.GlobalException;
import kr.co.moin.global.util.AuthUtil;
import kr.co.moin.infra.exchange.dto.ExchangeInfoToApiRes;
import kr.co.moin.infra.exchange.dto.ExchangeInfoToApiVo;
import kr.co.moin.infra.exchange.service.ExchangeInfoToApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferQuoteService {

  private final ExchangeInfoToApiService exchangeInfoToApiService;
  private final QuoteRepo quoteRepo;
  private final MemberRepo memberRepo;

  @Transactional
  public TransferQuoteRes quote(TransferQuoteReq transferQuoteReq) {
    Member member = memberRepo.findByUserId(AuthUtil.getUserId()).orElseThrow(() -> new GlobalException(ErrorCode.FAIL_LOGIN));
    Map<String, ExchangeInfoToApiRes> exchangeInfos = exchangeInfoToApiService.search(new ExchangeInfoToApiVo()).stream()
      .collect(Collectors.toMap(ExchangeInfoToApiRes::getCurrencyCode, Function.identity(), (t1, t2) -> t1));

    Currency targetCurrency = transferQuoteReq.getTargetCurrency();
    double exchangeRate = calcExchangeRate(exchangeInfos.get(targetCurrency.name()));
    double fee = calcFee(targetCurrency, transferQuoteReq.getAmount());

    double originAmount = transferQuoteReq.getAmount() - fee;
    double targetAmount = calcTargetAmount(targetCurrency, originAmount, exchangeRate);

    Quote savedQuote = quoteRepo.save(Quote.builder()
      .member(member)
      .exchangeRate(exchangeRate)
      .expireTime(LocalDateTime.now().plusMinutes(10))
      .targetAmount(targetAmount)
      .targetCurrency(targetCurrency)
      .sourceAmount(transferQuoteReq.getAmount())
      .fee(fee)
      .usdExchangeRate(exchangeInfos.get(Currency.USD.name()).getBasePrice())
      .usdAmount(Objects.equals(targetCurrency, Currency.USD) ? targetAmount : calcTargetAmount(Currency.USD, originAmount, exchangeInfos.get(Currency.USD.name()).getBasePrice()))
      .build());

    return new TransferQuoteRes(savedQuote);
  }


  private double calcExchangeRate(ExchangeInfoToApiRes exchangeInfoToApiRes) {
    return new BigDecimal(String.valueOf(exchangeInfoToApiRes.getBasePrice())).divide(new BigDecimal(String.valueOf(exchangeInfoToApiRes.getCurrencyUnit()))).doubleValue();
  }


  private double calcFee(Currency targetCurrency, long sourceAmount) {
    double commissionRate = targetCurrency.commissionRate(sourceAmount);
    double fixedFee = targetCurrency.fixedFee(sourceAmount);
    return (sourceAmount * commissionRate) + fixedFee;
  }


  /**
   * @param originAmount sourceAmount - fee
   */
  private double calcTargetAmount(Currency targetCurrency, double originAmount, double exchangeRate) {
    double targetAmount = originAmount / exchangeRate;

    int roundingDigits = getRoundingDigits(targetCurrency);

    return Double.parseDouble(String.format("%." + roundingDigits + "f", targetAmount));
  }

  private int getRoundingDigits(Currency currency) {
    return java.util.Currency.getInstance(currency.name()).getDefaultFractionDigits();
  }
}

