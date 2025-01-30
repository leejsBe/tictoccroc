package kr.co.moin.global.service;

import kr.co.moin.domain.model.Member;
import kr.co.moin.domain.repository.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("userDetailService")
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

  private final MemberRepo memberRepo;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return memberRepo.findByUserId(username).map(user -> createMember(username, user))
      .orElseThrow(() -> new UsernameNotFoundException(username + " 로그인 실패"));
  }


  private UserDetails createMember(String username, Member member) {
    List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
    return new User(member.getUserId(), member.getPassword(), authorities);
  }


}
