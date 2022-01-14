package com.kkopaysec.assignment.banking.dto;

import com.kkopaysec.assignment.banking.domain.Member;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DepositByPeriodResponse {

    private Long accountHolderId;
    private String accountHolderName;
    private BigDecimal deposit;

    public DepositByPeriodResponse(Long accountHolderId, String accountHolderName, BigDecimal deposit) {
        this.accountHolderId = accountHolderId;
        this.accountHolderName = accountHolderName;
        this.deposit = deposit;
    }

    public static DepositByPeriodResponse of(DepositByPeriod deposits) {
        return new DepositByPeriodResponse(deposits.getAccountHolderId(),
                deposits.getAccountHolderName(),
                deposits.getDeposit());
    }

    public static List<DepositByPeriodResponse> toList(List<DepositByPeriod> depositByPeriods) {
        return depositByPeriods.stream()
                .map(DepositByPeriodResponse::of)
                .collect(Collectors.toList());
    }

}
