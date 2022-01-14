package com.kkopaysec.assignment.banking.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class DepositByPeriodRequest {

    @Pattern(regexp = "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")
    @ApiParam(value = "시작일", example = "yyyy-MM-dd")
    private String startDate;

    @Pattern(regexp = "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")
    @ApiParam(value = "종료일", example = "yyyy-MM-dd")
    private String endDate;

    public DepositByPeriodRequest(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

}