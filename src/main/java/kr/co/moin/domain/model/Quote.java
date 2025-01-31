package kr.co.moin.domain.model;

import jakarta.persistence.*;
import kr.co.moin.domain.enumeration.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "quote")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quote {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Member member;

  @Column(name = "exchange_rate")
  private double exchangeRate;

  @Column(name = "expire_time")
  private LocalDateTime expireTime;

  @Column(name = "target_amount")
  private double targetAmount;


  @Column(name = "source_amount")
  private long sourceAmount;

  @Column(name = "fee")
  private double fee;

  @Column(name = "usd_exchange_rate")
  private double usdExchangeRate;

  @Column(name = "usdAmount")
  private double usdAmount;

  @Column(name = "target_curency")
  @Enumerated(EnumType.STRING)
  private Currency targetCurrency;


  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createAt;

  private LocalDateTime delAt;

}
