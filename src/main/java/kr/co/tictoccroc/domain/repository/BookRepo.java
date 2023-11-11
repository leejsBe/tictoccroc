package kr.co.tictoccroc.domain.repository;

import kr.co.tictoccroc.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {


  @Query("SELECT b FROM Book b WHERE b.delAt IS NULL AND b.storeLesson.id = :storeLessonId AND b.bookStatus <> 'CANCEL'")
  List<Book> findByStoreLessonAndParent(@Param("storeLessonId") long storeLessonId);


  @Query("SELECT b FROM Book b JOIN FETCH b.storeLesson sl WHERE b.delAt IS NULL AND sl.delAt IS NULL AND b.bookStatus <> 'CANCEL' AND sl.store.id = :storeId")
  List<Book> findByStore(@Param("storeId") long storeId);


  @Query("SELECT b FROM Book b JOIN FETCH b.storeLesson sl WHERE b.delAt IS NULL AND sl.delAt IS NULL AND b.bookStatus <> 'CANCEL' AND sl.lesson.id = :lessonId")
  List<Book> findByLesson(@Param("lessonId") long lessonId);


  @Query("SELECT b FROM Book b JOIN FETCH b.storeLesson sl JOIN FETCH sl.lesson JOIN FETCH sl.store WHERE b.delAt IS NULL AND sl.delAt IS NULL AND sl.store.id = :storeId")
  List<Book> findByStoreIncludeCancel(@Param("storeId") long storeId);


  @Query("SELECT b FROM Book b JOIN FETCH b.storeLesson sl JOIN FETCH sl.lesson JOIN FETCH sl.store WHERE b.delAt IS NULL AND sl.delAt IS NULL AND sl.lesson.id = :lessonId")
  List<Book> findByLessonIncludeCancel(@Param("lessonId") long lessonId);
}
