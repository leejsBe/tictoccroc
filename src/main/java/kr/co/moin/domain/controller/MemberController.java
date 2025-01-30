package kr.co.moin.domain.controller;

import io.swagger.v3.oas.annotations.Operation;
import kr.co.moin.domain.dto.MemberLoginReq;
import kr.co.moin.domain.dto.MemberSignUpReq;
import kr.co.moin.domain.service.MemberLoginService;
import kr.co.moin.domain.service.MemberSignUpService;
import kr.co.moin.global.dto.GlobalRes;
import kr.co.moin.global.enumeration.ErrorCode;
import kr.co.moin.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {

  private final MemberSignUpService memberSignUpService;
  private final MemberLoginService memberLoginService;


  @Operation(summary = "회원가입")
  @PostMapping("/signup")
  public ResponseEntity<GlobalRes> signup(@RequestBody MemberSignUpReq memberSignUpReq) {
    try {
      memberSignUpService.signup(memberSignUpReq);
      return ResponseEntity.ok(new GlobalRes());
    } catch (GlobalException e) {
      return ResponseEntity.badRequest().body(new GlobalRes(e.getCode(), e.getMessage()));
    } catch (Exception e) {
      log.error("{}", e.getMessage(), e);
      return ResponseEntity.internalServerError().body(new GlobalRes(ErrorCode.FAIL_API));
    }
  }

  @Operation(summary = "로그인")
  @PostMapping("/login")
  public ResponseEntity<GlobalRes> login(@RequestBody MemberLoginReq memberLoginReq) {
    try {
      return ResponseEntity.ok(memberLoginService.login(memberLoginReq));
    } catch (BadCredentialsException e) {
      return ResponseEntity.badRequest().body(new GlobalRes(ErrorCode.FAIL_LOGIN));
    } catch (Exception e) {
      log.error("{}", e.getMessage(), e);
      return ResponseEntity.internalServerError().body(new GlobalRes(ErrorCode.FAIL_API));
    }
  }

}
