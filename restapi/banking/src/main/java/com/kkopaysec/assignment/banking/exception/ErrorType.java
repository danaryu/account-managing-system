package com.kkopaysec.assignment.banking.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorType {

    MEMBER_NOT_FOUND("member-0001", "사용자를 찾을 수 없습니다."),
    ACCOUNT_NOT_FOUND("account-0001", "계좌를 찾을 수 없습니다."),
    HISTORY_NOT_FOUND("history-0002", "계좌내역을 찾을 수 없습니다.");

    private final String errorCode;
    private final String message;

}
