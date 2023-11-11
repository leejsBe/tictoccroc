package kr.co.tictoccroc.domain.repository;

import kr.co.tictoccroc.domain.model.StoreLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StoreLessonRepo extends JpaRepository<StoreLesson, Long> {


  @Query("SELECT sl FROM StoreLesson sl WHERE sl.delAt IS NULL AND sl.lessonDay < :lessonDay")
  List<StoreLesson> findByLessonDayPast(@Param("lessonDay") LocalDate lessonDay);


  @Query("SELECT sl FROM StoreLesson sl WHERE sl.delAt IS NULL AND sl.lessonDay = :lessonDay")
  List<StoreLesson> findByLessonDayToday(@Param("lessonDay") LocalDate lessonDay);


  @Query("SELECT sl FROM StoreLesson sl WHERE sl.delAt IS NULL AND sl.lessonDay > :lessonDay ORDER BY sl.lessonDay ASC")
  List<StoreLesson> findByLessonDayFuture(@Param("lessonDay") LocalDate lessonDay);

}
