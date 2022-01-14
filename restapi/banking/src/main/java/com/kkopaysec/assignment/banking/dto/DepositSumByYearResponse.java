package com.kkopaysec.assignment.banking.dto;


import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class DepositSumByYearResponse {

    private BigDecimal depositSum;
    private String year;

    public DepositSumByYearResponse(BigDecimal depositSum, String year) {
        this.depositSum = depositSum;
        this.year = year;
    }

    public static DepositByMemberAccountResponse of(DepositSumByYear depositSumByYear) {
        return new DepositByMemberAccountResponse(depositSumByYear.getYear(),
                depositSumByYear.getDepositSum());
    }
}
