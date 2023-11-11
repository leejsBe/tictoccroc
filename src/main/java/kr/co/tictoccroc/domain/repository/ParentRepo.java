package kr.co.tictoccroc.domain.repository;

import kr.co.tictoccroc.domain.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepo extends JpaRepository<Parent, Long> {


}