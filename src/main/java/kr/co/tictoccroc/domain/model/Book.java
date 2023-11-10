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
@Table(name = "book", catalog = "tictoccroc")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private StoreLesson storeLesson;

  @ManyToOne(fetch = FetchType.LAZY)
  private Parent parent;

  @Column(name = "lesson_day")
  private LocalDate lessonDay;

  @Column(name = "count")
  private int count;


  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createAt;

  @UpdateTimestamp
  private LocalDateTime modAt;

  private LocalDateTime delAt;

}
