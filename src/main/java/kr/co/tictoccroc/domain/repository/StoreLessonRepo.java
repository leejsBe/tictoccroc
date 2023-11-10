package kr.co.tictoccroc.domain.repository;

import kr.co.tictoccroc.domain.model.StoreLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreLessonRepo extends JpaRepository<StoreLesson, Long> {


}
