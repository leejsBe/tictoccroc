package kr.co.moin.global.converter;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import kr.co.moin.global.enumeration.ErrorCode;
import kr.co.moin.global.enumeration.encryptAndDecrypt.Decryption;
import kr.co.moin.global.enumeration.encryptAndDecrypt.Encryption;
import kr.co.moin.global.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Converter
public class StringEncryptConverter implements AttributeConverter<String, String> {

  @Value("${converter.db.key}")
  private String key;


  @Override
  public String convertToDatabaseColumn(String s) {
    if (StringUtils.isBlank(this.key)) {
      throw new GlobalException(ErrorCode.NO_KEY_ERROR);
    }
    if (StringUtils.isBlank(s)) {
      return null;
    }

    return Encryption.DB.of(s, this.key);
  }


  @Override
  public String convertToEntityAttribute(String s) {
    if (StringUtils.isBlank(this.key)) {
      throw new GlobalException(ErrorCode.NO_KEY_ERROR);
    }
    if (StringUtils.isBlank(s)) {
      return null;
    }

    return Decryption.DB.of(s, this.key);
  }
}
