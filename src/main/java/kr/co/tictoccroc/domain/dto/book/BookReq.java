package kr.co.tictoccroc.domain.dto.book;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BookReq {

  private long storeLessonId;
  private long parentId;
  private LocalDate lessonDay;
  private int count;

}
