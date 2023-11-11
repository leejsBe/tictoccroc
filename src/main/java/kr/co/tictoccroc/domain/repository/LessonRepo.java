package kr.co.tictoccroc.domain.repository;

import kr.co.tictoccroc.domain.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepo extends JpaRepository<Lesson, Long> {


}
