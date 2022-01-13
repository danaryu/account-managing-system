package com.kkopaysec.assignment.banking.controller;

import com.kkopaysec.assignment.banking.dto.AccountRequest;
import com.kkopaysec.assignment.banking.dto.AccountResponse;
import com.kkopaysec.assignment.banking.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountResponse>> findAllAccounts() {
        List<AccountResponse> accounts = accountService.findAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("{accountId}")
    public ResponseEntity<AccountResponse> findAccountById(@PathVariable Long accountId) {
        AccountResponse account = accountService.findAccountById(accountId);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/account-number/{accountNumber:.+}")
    public ResponseEntity<AccountResponse> findAccountByAccountNumber(@PathVariable String accountNumber) {
        AccountResponse account = accountService.findAccountByAccountNumber(accountNumber);
        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity<Void> addAccount(@RequestBody @Valid AccountRequest accountRequest) {
        accountService.addAccount(accountRequest);
        return ResponseEntity.ok().build();
    }
}
