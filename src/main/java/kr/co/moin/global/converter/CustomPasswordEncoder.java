package kr.co.moin.global.converter;

import kr.co.moin.global.enumeration.encryptAndDecrypt.Encryption;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

  @Value("${converter.db.key}")
  private String key;

  @Override
  public String encode(CharSequence rawPassword) {
    return Encryption.DB.of(rawPassword.toString(), key);
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    return StringUtils.equals(rawPassword, encodedPassword);
  }
}

