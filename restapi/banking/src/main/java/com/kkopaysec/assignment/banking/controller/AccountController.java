package com.kkopaysec.assignment.banking.controller;

import com.kkopaysec.assignment.banking.dto.AccountRequest;
import com.kkopaysec.assignment.banking.dto.AccountResponse;
import com.kkopaysec.assignment.banking.service.AccountService;
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
@RequestMapping("/account")
@RequiredArgsConstructor
@Api(tags = "Account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    @ApiOperation(value = "전체 계좌 목록 조회하기", notes = "<strong>전체 계좌 목록</strong>을 조회합니다.")
    public ResponseEntity<List<AccountResponse>> findAllAccounts() {
        List<AccountResponse> accounts = accountService.findAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("{accountId}")
    @ApiOperation(value = "계좌 식별 번호로 계좌 정보 조회하기",
                  notes = "<big>accountId</big>로 <strong>계좌정보</strong>를 조회합니다. " +
                          "<br><br> [주의!] 계좌 식별번호는 계좌번호가 아닙니다. <br>" +
                          "계좌번호로 검색을 원하시는 경우, <strong>/account-number</strong> endpoint를 이용해주세요.</br></br></br>")
    public ResponseEntity<AccountResponse> findAccountById(@PathVariable Long accountId) {
        AccountResponse account = accountService.findAccountById(accountId);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/account-number/{accountNumber:.+}")
    @ApiOperation(value = "계좌번호로 계좌 정보 조회하기", notes = "<big>accountNumber</big>으로 <strong>계좌정보</strong>를 조회합니다.")
    public ResponseEntity<AccountResponse> findAccountByAccountNumber(@PathVariable String accountNumber) {
        AccountResponse account = accountService.findAccountByAccountNumber(accountNumber);
        return ResponseEntity.ok(account);
    }

    @PostMapping
    @ApiOperation(value = "계좌 추가하기", notes = "현재 존재하는 사용자를 대상으로 계좌를 추가합니다.")
    public ResponseEntity<Void> addAccount(@RequestBody @Valid AccountRequest accountRequest) {
        accountService.addAccount(accountRequest);
        return ResponseEntity.ok().build();
    }
}
