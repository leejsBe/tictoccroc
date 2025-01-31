package kr.co.moin.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.moin.global.dto.GlobalRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class TransferQuoteRes extends GlobalRes {

  private Quote quote;

  public TransferQuoteRes(kr.co.moin.domain.model.Quote quote) {
    this.quote = Quote.builder()
      .quoteId(quote.getId())
      .exchangeRate(quote.getExchangeRate())
      .expireTime(quote.getExpireTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
      .targetAmount(quote.getTargetAmount())
      .build();
  }

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Quote {
    @Schema(description = "견적서 ID")
    private long quoteId;

    @Schema(description = "환율 정보")
    private double exchangeRate;

    @Schema(description = "만료 시간")
    private String expireTime;

    @Schema(description = "")
    private double targetAmount;

  }

}
