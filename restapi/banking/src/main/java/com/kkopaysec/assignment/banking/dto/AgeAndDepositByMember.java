package com.kkopaysec.assignment.banking.dto;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class AgeAndDepositByMember {

    private Long memberId;
    private int age;
    private BigDecimal depositSum;

    public AgeAndDepositByMember(Long memberId, int age, BigDecimal depositSum) {
        this.memberId = memberId;
        this.age = age;
        this.depositSum = depositSum;
    }

}
