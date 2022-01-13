package com.kkopaysec.assignment.banking.dto;

import com.kkopaysec.assignment.banking.domain.AccountHistory;
import com.kkopaysec.assignment.banking.domain.AccountStatus;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AccountHistoryResponse {

    private AccountResponse account;
    private AccountStatus accountStatus;
    private BigDecimal amount;
    private String businessDate;

    public AccountHistoryResponse(AccountResponse account, AccountStatus accountStatus, BigDecimal amount, String businessDate) {
        this.account = account;
        this.accountStatus = accountStatus;
        this.amount = amount;
        this.businessDate = businessDate;
    }

    public static AccountHistoryResponse of(AccountHistory accountHistory) {
        return new AccountHistoryResponse(AccountResponse.of(accountHistory.getAccount()),
                accountHistory.getAccountStatus(),
                accountHistory.getAmount(),
                accountHistory.getBusinessDate());
    }

    public static List<AccountHistoryResponse> toList(List<AccountHistory> histories) {
        return histories.stream()
                .map(AccountHistoryResponse::of)
                .collect(Collectors.toList());
    }
}
