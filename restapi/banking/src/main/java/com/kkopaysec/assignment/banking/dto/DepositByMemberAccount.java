package com.kkopaysec.assignment.banking.dto;

import com.kkopaysec.assignment.banking.domain.AccountStatus;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
public class DepositByMemberAccount {

    private BigDecimal deposit;
    private String accountNumber;

    public DepositByMemberAccount(BigDecimal deposit, String accountNumber) {
        this.deposit = deposit;
        this.accountNumber = accountNumber;
    }

}