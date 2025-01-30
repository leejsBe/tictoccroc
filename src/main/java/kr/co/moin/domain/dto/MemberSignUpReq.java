package kr.co.moin.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.moin.domain.enumeration.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignUpReq {

  @Schema(description = "아이디")
  private String userid;

  @Schema(description = "비밀번호")
  private String password;

  @Schema(description = "이름")
  private String name;

  @Schema(description = "식별ID 타입")
  private IdType idType;

  @Schema(description = "REG_NO: 주민등록번호, BUSINESS_NO: 사업자 등록번호")
  private String idValue;

}
