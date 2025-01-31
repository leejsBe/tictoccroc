package kr.co.moin.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.moin.domain.enumeration.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferQuoteReq {

  @Schema(description = "보내는 금액")
  private long amount;

  @Schema(description = "통화 정보")
  private Currency targetCurrency;

}
