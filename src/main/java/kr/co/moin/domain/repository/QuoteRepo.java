package kr.co.moin.domain.repository;

import kr.co.moin.domain.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepo extends JpaRepository<Quote, Long> {


}
