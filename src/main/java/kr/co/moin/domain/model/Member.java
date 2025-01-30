package kr.co.moin.domain.model;

import jakarta.persistence.*;
import kr.co.moin.domain.enumeration.IdType;
import kr.co.moin.global.converter.StringEncryptConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity
@Table(name = "member")
@DynamicInsert
@DynamicUpdate
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "user_id", unique = true)
  @Convert(converter = StringEncryptConverter.class)
  private String userId;

  @Column(name = "name")
  @Convert(converter = StringEncryptConverter.class)
  private String name;

  @Column(name = "password")
  @Convert(converter = StringEncryptConverter.class)
  private String password;

  @Column(name = "id_type")
  @Enumerated(EnumType.STRING)
  private IdType idType;

  @Column(name = "id_value")
  @Convert(converter = StringEncryptConverter.class)
  private String idValue;


}
