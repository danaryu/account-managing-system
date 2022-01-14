package com.kkopaysec.assignment.banking.dto;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
public class DepositSumByYear {

    private String year;
    private BigDecimal depositSum;

    public DepositSumByYear(String year, BigDecimal depositSum) {
        this.year = year;
        this.depositSum = depositSum;
    }
}
