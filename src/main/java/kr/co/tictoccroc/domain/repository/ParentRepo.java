package kr.co.tictoccroc.domain.repository;

import kr.co.tictoccroc.domain.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentRepo extends JpaRepository<Parent, Long> {


  @Query("SELECT p FROM Parent p WHERE p.id IN (:ids)")
  List<Parent> findById(@Param("ids") List<Long> ids);

}
