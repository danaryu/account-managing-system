package com.kkopaysec.assignment.banking.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountStatus {

    Y("DEPOSIT", "Y"),
    N("WITHDRAWAL", "N");

    private String status;
    private String value;

}
