package kr.co.tictoccroc.domain.repository;

import kr.co.tictoccroc.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {


}
