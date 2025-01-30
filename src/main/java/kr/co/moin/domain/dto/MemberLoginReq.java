package kr.co.moin.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginReq {

  @Schema(description = "아이디")
  private String userid;

  @Schema(description = "비밀번호")
  private String password;

}
