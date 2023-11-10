package kr.co.tictoccroc.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "parent", catalog = "tictoccroc")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Parent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String email;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createAt;

  @UpdateTimestamp
  private LocalDateTime modAt;

  private LocalDateTime delAt;

}
