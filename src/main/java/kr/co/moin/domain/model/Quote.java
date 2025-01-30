package kr.co.moin.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "quote", catalog = "moin")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quote {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @Column(name = "exchange_rate")
  private double exchangeRate;

  @Column(name = "expire_time")
  private LocalDateTime expireTime;

  @Column(name = "target_amount")
  private double targetAmount;


  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createAt;

  private LocalDateTime delAt;

}
