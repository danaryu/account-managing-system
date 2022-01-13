package com.kkopaysec.assignment.banking.dto;

import com.kkopaysec.assignment.banking.domain.AccountStatus;
import io.swagger.annotations.ApiParam;
import lombok.Getter;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
public class AccountHistoryRequest {

    @ApiParam(value = "계좌 번호", example = "0000-00")
    @Pattern(regexp = "\\d{4}-\\d{2}")
    private String accountNumber;

    @ApiParam(value = "입출금 여부")
    private AccountStatus accountStatus;

    @ApiParam(value = "입금액")
    private BigDecimal amount;

    public AccountHistoryRequest(String accountNumber, AccountStatus accountStatus, BigDecimal amount) {
        this.accountNumber = accountNumber;
        this.accountStatus = accountStatus;
        this.amount = amount;
    }

}