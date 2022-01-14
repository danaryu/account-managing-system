package com.kkopaysec.assignment.banking.dto;

import com.kkopaysec.assignment.banking.domain.Member;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
public class DepositByPeriod {

    private Long accountHolderId;
    private String accountHolderName;
    private BigDecimal deposit;

    public DepositByPeriod(Long accountHolderId, String accountHolderName, BigDecimal deposit) {
        this.accountHolderId = accountHolderId;
        this.accountHolderName = accountHolderName;
        this.deposit = deposit;
    }
}
