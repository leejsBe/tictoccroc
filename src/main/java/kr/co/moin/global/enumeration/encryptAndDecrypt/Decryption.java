package kr.co.moin.global.enumeration.encryptAndDecrypt;

import lombok.Getter;

@Getter
public enum Decryption {

  /**
   * db 컬럼 복호화
   */
  DB {
    @Override
    public String of(String str, String key) {
      return Algorithm.AES_ECB_PKCS5Padding.decode(str, key);
    }
  },


  ;


  public abstract String of(String str, String key);


}
