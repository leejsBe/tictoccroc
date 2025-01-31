package kr.co.moin.infra.exchange.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeInfoToApiRes {


  private String code;
  private String currencyCode;
  private double basePrice;
  private double currencyUnit;


}
