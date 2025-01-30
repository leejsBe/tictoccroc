package kr.co.moin.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.moin.global.dto.GlobalRes;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberLoginRes extends GlobalRes {

  @Schema(description = "토큰")
  private String token;


  public MemberLoginRes(String token) {
    super();
    this.token = token;
  }
}
