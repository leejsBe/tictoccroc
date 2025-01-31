package kr.co.moin.global.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@ToString
public class GlobalRes {

  @Builder.Default
  @Schema(description = "결과 코드")
  private int resultCode = 200;

  @Builder.Default
  @Schema(description = "응담 메세지(에러 발생 시)")
  private String resultMsg = "OK";


  public GlobalRes() {
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
