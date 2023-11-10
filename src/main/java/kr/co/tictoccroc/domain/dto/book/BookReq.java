package kr.co.tictoccroc.domain.dto.book;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookReq {

  @Positive(message = "수업이 입력되지 않았습니다.")
  private long storeLessonId;

  @Positive(message = "부모 정보가 입력되지 않았습니다.")
  private long parentId;

  private LocalDate lessonDay;

  @Positive(message = "예약인원이 입력되지 않았습니다.")
  private int count;

}
