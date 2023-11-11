package kr.co.tictoccroc.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "store_lesson", catalog = "tictoccroc")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreLesson {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Store store;

  @ManyToOne(fetch = FetchType.LAZY)
  private Lesson lesson;

  @Column(name = "lesson_day")
  private LocalDate lessonDay;
  
  @Column(name = "max_count_by_day")
  private int maxCountByDay;


  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createAt;

  @UpdateTimestamp
  private LocalDateTime modAt;

  private LocalDateTime delAt;

}
