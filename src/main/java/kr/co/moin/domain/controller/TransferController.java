package kr.co.moin.domain.controller;

import io.swagger.v3.oas.annotations.Operation;
import kr.co.moin.domain.dto.TransferQuoteReq;
import kr.co.moin.domain.dto.TransferReq;
import kr.co.moin.domain.service.TransferListService;
import kr.co.moin.domain.service.TransferQuoteService;
import kr.co.moin.domain.service.TransferRequestService;
import kr.co.moin.global.dto.GlobalRes;
import kr.co.moin.global.enumeration.ErrorCode;
import kr.co.moin.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {

  private final TransferQuoteService transferQuoteService;
  private final TransferRequestService transferRequestService;
  private final TransferListService transferListService;


  @Operation(summary = "송금 견적서 조회")
  @PostMapping("/quote")
  public ResponseEntity<GlobalRes> quote(@RequestBody TransferQuoteReq transferQuoteReq) {
    try {
      return ResponseEntity.ok(transferQuoteService.quote(transferQuoteReq));
    } catch (GlobalException e) {
      return ResponseEntity.badRequest().body(new GlobalRes(e.getCode(), e.getMessage()));
    } catch (Exception e) {
      log.error("{}", e.getMessage(), e);
      return ResponseEntity.internalServerError().body(new GlobalRes(ErrorCode.FAIL_API));
    }
  }


  @Operation(summary = "송금 접수 요청")
  @PostMapping("/request")
  public ResponseEntity<GlobalRes> request(@RequestBody TransferReq transferReq) {
    try {
      transferRequestService.request(transferReq);
      return ResponseEntity.ok(new GlobalRes());
    } catch (GlobalException e) {
      return ResponseEntity.badRequest().body(new GlobalRes(e.getCode(), e.getMessage()));
    } catch (Exception e) {
      log.error("{}", e.getMessage(), e);
      return ResponseEntity.internalServerError().body(new GlobalRes(ErrorCode.FAIL_API));
    }
  }


  @Operation(summary = "거래 이력")
  @GetMapping("/list")
  public ResponseEntity<GlobalRes> list() {
    try {
      return ResponseEntity.ok(transferListService.request());
    } catch (GlobalException e) {
      return ResponseEntity.badRequest().body(new GlobalRes(e.getCode(), e.getMessage()));
    } catch (Exception e) {
      log.error("{}", e.getMessage(), e);
      return ResponseEntity.internalServerError().body(new GlobalRes(ErrorCode.FAIL_API));
    }
  }

}
