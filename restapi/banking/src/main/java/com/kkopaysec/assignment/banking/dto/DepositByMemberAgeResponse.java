package com.kkopaysec.assignment.banking.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class DepositByMemberAgeResponse {

    private int ageGroup;
    private BigDecimal averageDeposit;

    public DepositByMemberAgeResponse(int ageGroup, BigDecimal averageDeposit) {
        this.ageGroup = ageGroup;
        this.averageDeposit = averageDeposit;
    }

    public static List<DepositByMemberAgeResponse> mapToList(Map<Integer, Integer> ageMap, Map<Integer, BigDecimal> depositMap) {
        List<DepositByMemberAgeResponse> averageDeposit = new ArrayList<>();
        for (Integer age : ageMap.keySet()) {
            averageDeposit.add(new DepositByMemberAgeResponse(age*10, depositMap.get(age).divide(BigDecimal.valueOf(ageMap.get(age)))));
        }
        return averageDeposit;
    }
}
