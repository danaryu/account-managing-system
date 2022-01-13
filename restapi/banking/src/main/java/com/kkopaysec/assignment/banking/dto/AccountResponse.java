package com.kkopaysec.assignment.banking.dto;

import com.kkopaysec.assignment.banking.domain.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@NoArgsConstructor
public class AccountResponse {

    private String accountNumber;
    private MemberResponse accountHolder;

    public AccountResponse(String accountNumber, MemberResponse accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
    }

    public static AccountResponse of(Account account) {
        return new AccountResponse(account.getAccountNumber(), MemberResponse.of(account.getAccountHolder()));
    }

    public static List<AccountResponse> toList(List<Account> accounts) {
        return accounts.stream()
                .map(AccountResponse::of)
                .collect(Collectors.toList());
    }

}
