package kr.co.tictoccroc.domain.repository;

import kr.co.tictoccroc.domain.model.Book;
import kr.co.tictoccroc.global.enumeration.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {


  @Query("SELECT b FROM Book b WHERE b.delAt IS NULL AND b.storeLesson.id = :storeLessonId AND b.bookStatus <> 'CANCEL'")
  List<Book> findByStoreLessonAndParent(@Param("storeLessonId") long storeLessonId);

}
