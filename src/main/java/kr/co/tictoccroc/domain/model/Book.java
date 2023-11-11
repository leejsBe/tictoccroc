package kr.co.tictoccroc.domain.model;

import jakarta.persistence.*;
import kr.co.tictoccroc.global.enumeration.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "book", catalog = "tictoccroc")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private StoreLesson storeLesson;

  @ManyToOne(fetch = FetchType.LAZY)
  private Parent parent;


  @Column(name = "count")
  private int count;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private BookStatus bookStatus;


  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createAt;

  @UpdateTimestamp
  private LocalDateTime modAt;

  private LocalDateTime delAt;


  public void changeStatus(BookStatus bookStatus) {
    this.bookStatus = bookStatus;
  }
}
