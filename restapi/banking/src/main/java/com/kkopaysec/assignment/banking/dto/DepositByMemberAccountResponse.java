package com.kkopaysec.assignment.banking.dto;

import com.kkopaysec.assignment.banking.domain.AccountHistory;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class DepositByMemberAccountResponse {

    private String accountNumber;
    private BigDecimal deposit;

    public DepositByMemberAccountResponse(String accountNumber, BigDecimal deposit) {
        this.accountNumber = accountNumber;
        this.deposit = deposit;
    }

    public DepositByMemberAccountResponse(Long accountHolderId, Long accountHolderName, BigDecimal deposit) {
    }

    public static DepositByMemberAccountResponse of(DepositByMemberAccount accountHistory) {
        return new DepositByMemberAccountResponse(accountHistory.getAccountNumber(),
                accountHistory.getDeposit());
    }

    public static List<DepositByMemberAccountResponse> toList(List<DepositByMemberAccount> histories) {
        return histories.stream()
                .map(DepositByMemberAccountResponse::of)
                .collect(Collectors.toList());
    }
}
