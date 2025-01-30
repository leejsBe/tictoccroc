package kr.co.moin.domain.service;

import kr.co.moin.domain.dto.MemberLoginReq;
import kr.co.moin.domain.dto.MemberLoginRes;
import kr.co.moin.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberLoginService {


  private final TokenProvider tokenProvider;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;


  public MemberLoginRes login(MemberLoginReq memberLoginReq) {
    UsernamePasswordAuthenticationToken authenticationToken =
      new UsernamePasswordAuthenticationToken(memberLoginReq.getUserid(), memberLoginReq.getPassword());

    // authenticate 메소드가 실행이 될 때 CustomUserDetailsService class의 loadUserByUsername 메소드가 실행
    Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    // 해당 객체를 SecurityContextHolder에 저장하고
    SecurityContextHolder.getContext().setAuthentication(authentication);
    // authentication 객체를 createToken 메소드를 통해서 JWT Token을 생성
    String jwt = tokenProvider.createToken(authentication);

    return new MemberLoginRes(jwt);
  }
}
