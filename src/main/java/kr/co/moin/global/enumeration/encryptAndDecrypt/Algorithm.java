package kr.co.moin.global.enumeration.encryptAndDecrypt;


import kr.co.moin.global.enumeration.ErrorCode;
import kr.co.moin.global.exception.GlobalException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.Base64;

@Slf4j
@Getter
@RequiredArgsConstructor
public enum Algorithm {


  /**
   * AES/ECB/PKCS5Padding 암복호화 로직
   */
  AES_ECB_PKCS5Padding("AES/ECB/PKCS5Padding") {
    @Override
    public String encode(String str, String key) {
      if (StringUtils.isBlank(str)) {
        return "";
      }

      try {
        Cipher cipher = Cipher.getInstance(this.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
        return new String(Base64.getEncoder().encode(cipher.doFinal(Normalizer.normalize(str, Normalizer.Form.NFKC).getBytes())));
      } catch (Exception e) {
        log.info("str: {}, key: {}, error: {}", str, key, e.getMessage());
        throw new GlobalException(ErrorCode.ENCRYPTION_DECRYPTION_ERROR);
      }
    }

    @Override
    public String decode(String str, String key) {
      if (StringUtils.isBlank(str)) {
        return "";
      }

      try {
        Cipher cipher = Cipher.getInstance(this.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
        return new String(cipher.doFinal(Base64.getDecoder().decode(str)));
      } catch (Exception e) {
        log.info("str: {}, key: {}, error: {}", str, key, e.getMessage());
        throw new GlobalException(ErrorCode.ENCRYPTION_DECRYPTION_ERROR);
      }
    }
  },

  /**
   * AES/CBC/PKCS5Padding 암복호화 로직
   */
  AES_CBC_PKCS5Padding("AES/CBC/PKCS5Padding") {
    @Override
    public String encode(String str, String key) {
      if (StringUtils.isBlank(str)) {
        return "";
      }

      try {
        Cipher cipher = getCipher(key.getBytes(StandardCharsets.UTF_8), Cipher.ENCRYPT_MODE);
        return Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
      } catch (Exception e) {
        log.info("str: {}, key: {}, error: {}", str, key, e.getMessage());
        throw new GlobalException(ErrorCode.ENCRYPTION_DECRYPTION_ERROR);
      }
    }

    @Override
    public String decode(String str, String key) {
      if (StringUtils.isBlank(str)) {
        return "";
      }

      try {
        Cipher cipher = getCipher(key.getBytes(StandardCharsets.UTF_8), Cipher.DECRYPT_MODE);
        return new String(cipher.doFinal(Base64.getDecoder().decode(str)), StandardCharsets.UTF_8);
      } catch (Exception e) {
        log.info("str: {}, key: {}, error: {}", str, key, e.getMessage());
        throw new GlobalException(ErrorCode.ENCRYPTION_DECRYPTION_ERROR);
      }
    }

    private Cipher getCipher(byte[] keyByte, int mode) {
      try {
        Cipher cipher = Cipher.getInstance(this.getAlgorithm());
        byte[] IV = new byte[16];
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyByte, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);

        cipher.init(mode, secretKeySpec, ivParameterSpec);

        return cipher;
      } catch (Exception e) {
        log.info("keyByte: {}, mode: {}, error: {}", keyByte, mode, e.getMessage());
        throw new GlobalException(ErrorCode.ENCRYPTION_DECRYPTION_ERROR);
      }
    }
  },

  ;

  private final String algorithm;

  public abstract String encode(String str, String key);

  public abstract String decode(String str, String key);
}
