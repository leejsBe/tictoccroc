package kr.co.moin.domain.service;

import kr.co.moin.domain.dto.TransferListRes;
import kr.co.moin.domain.model.Member;
import kr.co.moin.domain.model.Request;
import kr.co.moin.domain.repository.MemberRepo;
import kr.co.moin.domain.repository.RequestRepo;
import kr.co.moin.global.enumeration.ErrorCode;
import kr.co.moin.global.exception.GlobalException;
import kr.co.moin.global.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferListService {

  private final MemberRepo memberRepo;
  private final RequestRepo requestRepo;


  @Transactional
  public TransferListRes request() {
    Member member = memberRepo.findByUserId(AuthUtil.getUserId()).orElseThrow(() -> new GlobalException(ErrorCode.FAIL_LOGIN));


    List<Request> requestList = requestRepo.findAllByMember(member.getId());


    LocalDateTime toDay = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));
    List<Request> toDayRequest = requestList.stream().filter(request -> request.getRequestedDate().isAfter(toDay)).toList();

    return TransferListRes.builder()
      .userId(member.getUserId())
      .name(member.getName())
      .todayTransferCount(toDayRequest.size())
      .todayTransferUsdAmount(toDayRequest.stream().map(Request::getUsdAmount).reduce(0D, Double::sum))
      .history(requestList.stream().map(history -> {
        return TransferListRes.History.builder()
          .sourceAmount((long) history.getSourceAmount())
          .fee(history.getFee())
          .usdExchangeRate(history.getUsdExchangeRate())
          .usdAmount(history.getUsdAmount())
          .targetCurrency(history.getTargetCurrency())
          .exchangeRate(history.getExchangeRate())
          .targetAmount(history.getTargetAmount())
          .requestedDate(history.getRequestedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
          .build();
      }).toList())
      .build();

  }


}
