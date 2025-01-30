package kr.co.moin.domain.model;

import jakarta.persistence.*;
import kr.co.moin.domain.enumeration.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "user", catalog = "moin")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;


  @Column(name = "user_id", unique = true)
  private String userId;

  @Column(name = "name")
  private String name;

  @Column(name = "password")
  private String password;

  @Column(name = "id_type")
  @Enumerated(EnumType.STRING)
  private IdType idType;

  @Column(name = "id_value")
  private String idValue;


  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createAt;

  @UpdateTimestamp
  private LocalDateTime modAt;

  private LocalDateTime delAt;

}
