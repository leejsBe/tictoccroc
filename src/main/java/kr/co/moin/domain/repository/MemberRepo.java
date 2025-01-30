package kr.co.moin.domain.repository;

import kr.co.moin.domain.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {


  Optional<Member> findByUserId(String userId);

  Optional<Member> findByUserIdAndPassword(String userId, String password);

}
