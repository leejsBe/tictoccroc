package kr.co.moin.global.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {


  /*
   * ID
   */
  public static String getUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof UsernamePasswordAuthenticationToken))
      return null;

    return authentication.getName();
  }

}
