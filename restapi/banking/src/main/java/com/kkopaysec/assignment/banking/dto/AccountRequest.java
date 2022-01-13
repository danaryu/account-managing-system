package com.kkopaysec.assignment.banking.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class AccountRequest {

/*
    @NotBlank
    @Pattern(regexp = "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")
    private String accountNumber;
*/

    @NotNull(message = "계좌 사용자 ID는 반드시 입력해야합니다.")
    private Long accountHolderId;

    public AccountRequest(Long accountHolderId) {
        //this.accountNumber = accountNumber;
        this.accountHolderId = accountHolderId;
    }
}
