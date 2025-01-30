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
@Table(name = "history", catalog = "moin")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class History {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;


  @Column(name = "source_amount")
  private double sourceAmount;

  @Column(name = "fee")
  private double fee;

  @Column(name = "usd_exchange_rate")
  private double useExchangeRate;

  @Column(name = "usd_amount")
  private double usdAmount;

  @Column(name = "target_currency")
  private String targetCurrency;

  @Column(name = "exchange_rate")
  private double exchangeRate;

  @Column(name = "target_amount")
  private double targetAmount;

  @Column(name = "requested_date")
  private LocalDateTime requestedDate;


  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createAt;

  private LocalDateTime delAt;

}
