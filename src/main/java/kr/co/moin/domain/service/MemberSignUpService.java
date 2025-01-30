package kr.co.moin.domain.service;

import kr.co.moin.domain.dto.MemberSignUpReq;
import kr.co.moin.domain.model.Member;
import kr.co.moin.domain.repository.MemberRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberSignUpService {

  private final MemberRepo memberRepo;

  @Transactional
  public void signup(MemberSignUpReq memberSignUpReq) {
    memberRepo.save(Member.builder()
      .userId(memberSignUpReq.getUserid())
      .password(memberSignUpReq.getPassword())
      .name(memberSignUpReq.getName())
      .idType(memberSignUpReq.getIdType())
      .idValue(memberSignUpReq.getIdValue())
      .build());
  }
}
