package com.kkopaysec.assignment.banking.service;

import com.kkopaysec.assignment.banking.domain.Account;
import com.kkopaysec.assignment.banking.domain.AccountHistory;
import com.kkopaysec.assignment.banking.dto.AccountHistoryRequest;
import com.kkopaysec.assignment.banking.dto.AccountHistoryResponse;
import com.kkopaysec.assignment.banking.exception.NotFoundException;
import com.kkopaysec.assignment.banking.repository.AccountHistoryRepository;
import com.kkopaysec.assignment.banking.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountHistoryService {

    private final AccountHistoryRepository accountHistoryRepository;
    private final AccountRepository accountRepository;

    public List<AccountHistoryResponse> findAllHistories() {
        List<AccountHistory> histories = accountHistoryRepository.findAll();
        return AccountHistoryResponse.toList(histories);
    }

    public AccountHistoryResponse findHistoryById(Long historyId) {
        AccountHistory history = accountHistoryRepository.findById(historyId)
                .orElseThrow(() -> new NotFoundException("account를 찾을 수 없습니다"));
        return AccountHistoryResponse.of(history);
    }

    public List<AccountHistoryResponse> findHistoriesByAccountId(Long accountId) {
        List<AccountHistory> histories = new ArrayList<>();
        accountRepository.findById(accountId)
                .ifPresentOrElse(account -> {histories.addAll(accountHistoryRepository.findAllByAccount(account));},
                        () -> new NotFoundException("계좌를 찾을 수 없습니다."));
        return AccountHistoryResponse.toList(histories);
    }

    public List<AccountHistoryResponse> findHistoriesByAccountNumber(String accountNumber) {
        List<AccountHistory> histories = new ArrayList<>();
        accountRepository.findByAccountNumber(accountNumber)
                .ifPresentOrElse(account -> {histories.addAll(accountHistoryRepository.findAllByAccount(account));},
                        () -> new NotFoundException("계좌를 찾을 수 없습니다."));
        return AccountHistoryResponse.toList(histories);
    }

    public void addAccountHistory(AccountHistoryRequest accountHistoryRequest) {
        Account foundAccount = accountRepository.findByAccountNumber(accountHistoryRequest.getAccountNumber())
                .orElseThrow(() -> new NotFoundException("계좌를 찾을 수 없습니다."));
        String dateOfToday = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));

        AccountHistory history = AccountHistory.createHistory(foundAccount,
                accountHistoryRequest.getAccountStatus(),
                accountHistoryRequest.getAmount(),
                dateOfToday);
        accountHistoryRepository.save(history);
    }
}
