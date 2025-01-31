package kr.co.moin.domain.service;

import kr.co.moin.domain.dto.TransferReq;
import kr.co.moin.domain.enumeration.TransferRequestErrorCode;
import kr.co.moin.domain.exception.TransferRequestException;
import kr.co.moin.domain.model.Member;
import kr.co.moin.domain.model.Quote;
import kr.co.moin.domain.model.Request;
import kr.co.moin.domain.repository.MemberRepo;
import kr.co.moin.domain.repository.QuoteRepo;
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
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferRequestService {

  private final QuoteRepo quoteRepo;
  private final MemberRepo memberRepo;
  private final RequestRepo requestRepo;


  @Transactional
  public void request(TransferReq transferReq) {
    Member member = memberRepo.findByUserId(AuthUtil.getUserId()).orElseThrow(() -> new GlobalException(ErrorCode.FAIL_LOGIN));
    Quote quote = quoteRepo.findById(transferReq.getQuoteId()).orElseThrow(() -> new TransferRequestException(TransferRequestErrorCode.NOT_FOUND_QUOTE));

    validation(member, quote);

    requestRepo.save(Request.builder()
      .member(member)
      .sourceAmount(quote.getSourceAmount())
      .fee(quote.getFee())
      .usdExchangeRate(quote.getUsdExchangeRate())
      .usdAmount(quote.getUsdAmount())
      .targetCurrency(quote.getTargetCurrency().name())
      .exchangeRate(quote.getExchangeRate())
      .targetAmount(quote.getTargetAmount())
      .requestedDate(LocalDateTime.now())
      .build());
  }


  public void validation(Member member, Quote quote) {
    if (quote.getExpireTime().isBefore(LocalDateTime.now())) {
      throw new TransferRequestException(TransferRequestErrorCode.EXPIRE_DATE);
    }

    if (Objects.isNull(member.getIdType())) {
      throw new GlobalException(ErrorCode.FAIL_API);
    }

    double limitAmount = switch (member.getIdType()) {
      case REG_NO -> 1000;
      case BUSINESS_NO -> 5000;
      default -> throw new GlobalException(ErrorCode.FAIL_API);
    };

    LocalDateTime sdate = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));
    LocalDateTime edate = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));
    List<Request> requestList = requestRepo.findAllByMemberAndDate(member.getId(), sdate, edate);

    double totalPrice = requestList.stream().map(Request::getUsdAmount).reduce(0D, Double::sum);

    if (totalPrice + quote.getUsdAmount() >= limitAmount) {
      throw new TransferRequestException(TransferRequestErrorCode.LIMIT_EXCESS);
    }
  }

}
