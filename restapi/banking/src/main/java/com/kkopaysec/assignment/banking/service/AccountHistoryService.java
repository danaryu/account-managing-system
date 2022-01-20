package com.kkopaysec.assignment.banking.service;

import com.kkopaysec.assignment.banking.domain.Account;
import com.kkopaysec.assignment.banking.domain.AccountHistory;
import com.kkopaysec.assignment.banking.domain.Member;
import com.kkopaysec.assignment.banking.dto.*;
import com.kkopaysec.assignment.banking.exception.ErrorType;
import com.kkopaysec.assignment.banking.exception.NotFoundException;
import com.kkopaysec.assignment.banking.repository.AccountHistoryRepository;
import com.kkopaysec.assignment.banking.repository.AccountRepository;
import com.kkopaysec.assignment.banking.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountHistoryService {

    private final AccountHistoryRepository accountHistoryRepository;
    private final AccountRepository accountRepository;
    private final MemberRepository memberRepository;

    public List<AccountHistoryResponse> findAllHistories() {
        //List<AccountHistory> histories = accountHistoryRepository.findAll();
        List<AccountHistory> histories = accountHistoryRepository.findAllWithAccountAndMember();
        return AccountHistoryResponse.toList(histories);
    }

    public AccountHistoryResponse findHistoryById(Long historyId) {
        AccountHistory history = accountHistoryRepository.findById(historyId)
                .orElseThrow(() -> new NotFoundException(ErrorType.ACCOUNT_NOT_FOUND));
        return AccountHistoryResponse.of(history);
    }

    public List<AccountHistoryResponse> findHistoriesByAccountId(Long accountId) {
        List<AccountHistory> histories = new ArrayList<>();
        accountRepository.findById(accountId)
                .ifPresentOrElse(account -> { histories.addAll(accountHistoryRepository.findAllByAccount(account));
                        }, () -> new NotFoundException(ErrorType.ACCOUNT_NOT_FOUND));
        return AccountHistoryResponse.toList(histories);
    }

    public List<AccountHistoryResponse> findHistoriesByAccountNumber(String accountNumber) {
        List<AccountHistory> histories = new ArrayList<>();
        accountRepository.findByAccountNumber(accountNumber)
                .ifPresentOrElse(account -> {histories.addAll(accountHistoryRepository.findAllByAccount(account));}
                        , () -> new NotFoundException(ErrorType.ACCOUNT_NOT_FOUND));
        return AccountHistoryResponse.toList(histories);
    }

    public void addAccountHistory(AccountHistoryRequest accountHistoryRequest) {
        Account foundAccount = accountRepository.findByAccountNumber(accountHistoryRequest.getAccountNumber())
                .orElseThrow(() -> new NotFoundException(ErrorType.ACCOUNT_NOT_FOUND));
        String dateOfToday = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));

        AccountHistory history = AccountHistory.createHistory(foundAccount,
                accountHistoryRequest.getAccountStatus(),
                accountHistoryRequest.getAmount(),
                dateOfToday);
        accountHistoryRepository.save(history);
    }

    public List<DepositByMemberAccount> findAccountDepositByMemberId(Long memberId) {
        Member foundMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorType.MEMBER_NOT_FOUND));
        return accountHistoryRepository.findAllDepositsByMemberAccount(foundMember);
    }


    public List<DepositByMemberAgeResponse> findAccountDepositByMemberAge() {
        List<AgeAndDepositByMember> ageAndDepositByMember = accountHistoryRepository.findAllAgeAndDepositByMember();

        Map<Integer, BigDecimal> depositMap = new HashMap<>();
        Map<Integer, Integer> ageMap = new HashMap<>();

        ageAndDepositByMember.forEach(ageAndDeposit -> {
            int ageGroup = ageAndDeposit.getAge() / 10;
            ageMap.put(ageGroup, ageMap.getOrDefault(ageGroup, 0) + 1);
            depositMap.put(ageGroup, depositMap.getOrDefault(ageGroup, BigDecimal.ZERO).add(ageAndDeposit.getDepositSum()));
        });
        return DepositByMemberAgeResponse.mapToList(ageMap, depositMap);
    }

    public DepositSumByYear findAccountDepositSumByYear(String year) {
        return accountHistoryRepository.findDepositSumByYear(year);
    }

    public List<DepositByPeriod> findAccountDepositByPeriod(DepositByPeriodRequest depositByPeriodRequest) {
        return accountHistoryRepository.findAllDepositByPeriod(depositByPeriodRequest.getStartDate(),
                depositByPeriodRequest.getEndDate());
    }
}