package kr.co.moin.infra.exchange.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.moin.infra.enumeration.InfraErrorCode;
import kr.co.moin.infra.exception.InfraException;
import kr.co.moin.infra.exchange.dto.ExchangeInfoToApiRes;
import kr.co.moin.infra.exchange.dto.ExchangeInfoToApiVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeInfoToApiService extends ExchangeToApiService<List<ExchangeInfoToApiRes>> {

  private final WebClient myWebClient;

  public List<ExchangeInfoToApiRes> search(ExchangeInfoToApiVo exchangeInfoToApiVo) {
    return resFormatConvert(super.get(myWebClient, exchangeInfoToApiVo).block());
  }


  @Override
  public List<ExchangeInfoToApiRes> resFormatConvert(String resData) {
    try {
      super.resFormatConvert(resData);
      List<ExchangeInfoToApiRes> result = new ObjectMapper().readValue(resData, new TypeReference<>() {
      });

      if (CollectionUtils.isEmpty(result)) {
        log.info(":: 환율정보 요청 결과 에러 ::");
        throw new InfraException(InfraErrorCode.API_RESPONSE_CONVERT_ERROR);
      }

      return result;
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new InfraException(InfraErrorCode.API_RESPONSE_CONVERT_ERROR);
    }
  }
}
