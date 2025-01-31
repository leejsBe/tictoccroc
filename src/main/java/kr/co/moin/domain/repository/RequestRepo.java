package kr.co.moin.domain.repository;

import kr.co.moin.domain.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RequestRepo extends JpaRepository<Request, Long> {


  @Query("SELECT r FROM Request r WHERE r.member.id = :memberId AND r.requestedDate BETWEEN :sdate AND :edate")
  List<Request> findAllByMemberAndDate(@Param("memberId") long memberId, @Param("sdate") LocalDateTime sdate, @Param("edate") LocalDateTime edate);

  @Query("SELECT r FROM Request r WHERE r.member.id = :memberId")
  List<Request> findAllByMember(@Param("memberId") long memberId);
}
