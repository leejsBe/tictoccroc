package kr.co.tictoccroc.domain.dto.verify;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerifyHistoryReq {

  private long storeId;
  private long lessonId;


  public void changeStoreId(long storeId) {
    this.storeId = storeId;
  }

  public void changeLessonId(long lessonId) {
    this.lessonId = lessonId;
  }
}
