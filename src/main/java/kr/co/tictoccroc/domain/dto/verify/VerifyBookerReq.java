package kr.co.tictoccroc.domain.dto.verify;

import lombok.Getter;

@Getter
public class VerifyBookerReq {

  private long storeId;
  private long lessonId;
}
