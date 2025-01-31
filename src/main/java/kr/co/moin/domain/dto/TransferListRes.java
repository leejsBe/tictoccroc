package kr.co.moin.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.moin.global.dto.GlobalRes;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TransferListRes extends GlobalRes {

  @Schema(description = "회원 ID")
  private String userId;

  @Schema(description = "이름")
  private String name;

  @Schema(description = "오늘 송금 횟수")
  private long todayTransferCount;

  @Schema(description = "오늘 송금 금액")
  private double todayTransferUsdAmount;

  @Schema(description = "내역")
  private List<History> history;


  @Getter
  @Builder
  @ToString
  @NoArgsConstructor
  @AllArgsConstructor
  public static class History {
    @Schema(description = "원화 송금 요청액")
    private long sourceAmount;

    @Schema(description = "송금 수수료")
    private double fee;

    @Schema(description = "usd 환율정보")
    private double usdExchangeRate;

    @Schema(description = "usd 송금액")
    private double usdAmount;

    @Schema(description = "받는 환율 정보")
    private String targetCurrency;

    @Schema(description = "환율 정보")
    private double exchangeRate;

    @Schema(description = "받는 금액")
    private double targetAmount;

    @Schema(description = "송금 요청 시간")
    private String requestedDate;
  }

}
