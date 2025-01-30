package kr.co.moin.global.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class GlobalRes {

  @Schema(description = "결과 코드")
  private int resultCode;

  @Builder.Default
  @Schema(description = "응담 메세지(에러 발생 시)")
  private String resultMsg = "OK";


  public GlobalRes() {
    this.resultCode = 200;
    this.resultMsg = "OK";
  }


  public GlobalRes(ResCode resCode) {
    this.resultCode = resCode.code();
    this.resultMsg = resCode.msg();
  }

  public GlobalRes(int code, String msg) {
    this.resultCode = code;
    this.resultMsg = msg;
  }

}
