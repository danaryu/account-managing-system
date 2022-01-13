package com.kkopaysec.assignment.banking.controller;

import com.kkopaysec.assignment.banking.dto.AccountHistoryRequest;
import com.kkopaysec.assignment.banking.dto.AccountHistoryResponse;
import com.kkopaysec.assignment.banking.service.AccountHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
@Api(tags = "Account History")
public class AccountHistoryController {

    private final AccountHistoryService accountHistoryService;

    @GetMapping
    @ApiOperation(value = "전체 계좌 거래 내역 조회하기", notes = "<strong>전체 계좌 거래 내역</strong>을 조회합니다.")
    public ResponseEntity<List<AccountHistoryResponse>> findAllAccountHistory() {
        List<AccountHistoryResponse> histories = accountHistoryService.findAllHistories();
        return ResponseEntity.ok(histories);
    }

    @GetMapping("{historyId}")
    @ApiOperation(value = "계좌 거래 내역 식별 번호로 계좌 내역 조회하기",
            notes = "<big>historyId</big>로 <strong>계좌 내역</strong>을 조회합니다.")
    public ResponseEntity<AccountHistoryResponse> findAccountHistoryById(@PathVariable Long historyId) {
        AccountHistoryResponse history = accountHistoryService.findHistoryById(historyId);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/account/{accountId}")
    @ApiOperation(value = "계좌 식별 번호로 계좌 내역 조회하기",
            notes = "<big>accountId</big>로 <strong>계좌 내역</strong>을 조회합니다. " +
                "<br><br> [주의!] 계좌 식별번호는 계좌번호가 아닙니다. <br>" +
                "계좌번호로 검색을 원하시는 경우, <strong>history/account/account-number</strong> endpoint를 이용해주세요.</br></br></br>")
    public ResponseEntity<List<AccountHistoryResponse>> findAccountHistoryByAccountNumber(@PathVariable Long accountId) {
        List<AccountHistoryResponse> histories = accountHistoryService.findHistoriesByAccountId(accountId);
        return ResponseEntity.ok(histories);
    }

    @GetMapping("/account/account-number/{accountNumber:.+}")
    @ApiOperation(value = "계좌 번호로 계좌 내역 조회하기", notes = "<big>accountNumber</big>로 <strong>계좌 내역</strong>을 조회합니다.")
    public ResponseEntity<List<AccountHistoryResponse>> findAccountHistoryByAccountNumber(@PathVariable String accountNumber) {
        List<AccountHistoryResponse> histories = accountHistoryService.findHistoriesByAccountNumber(accountNumber);
        return ResponseEntity.ok(histories);
    }

    @PostMapping
    @ApiOperation(value = "계좌 거래 내역 추가하기", notes = "계좌 거래 내역을 추가합니다.")
    public ResponseEntity<Void> addAccountHistory(@RequestBody @Valid AccountHistoryRequest accountHistoryRequest) {
        accountHistoryService.addAccountHistory(accountHistoryRequest);
        return ResponseEntity.ok().build();
    }

}
