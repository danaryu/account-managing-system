package com.kkopaysec.assignment.banking.service;

import com.kkopaysec.assignment.banking.domain.Account;
import com.kkopaysec.assignment.banking.domain.Member;
import com.kkopaysec.assignment.banking.dto.AccountRequest;
import com.kkopaysec.assignment.banking.dto.AccountResponse;
import com.kkopaysec.assignment.banking.exception.ErrorType;
import com.kkopaysec.assignment.banking.exception.NotFoundException;
import com.kkopaysec.assignment.banking.repository.AccountRepository;
import com.kkopaysec.assignment.banking.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final MemberRepository memberRepository;

    public List<AccountResponse> findAllAccounts() {
        //return AccountResponse.toList(accountRepository.findAll());
        return AccountResponse.toList(accountRepository.findAllWithMember());
    }

    public AccountResponse findAccountById(Long accountId) {
        return AccountResponse.of(accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException(ErrorType.ACCOUNT_NOT_FOUND)));
    }

    public AccountResponse findAccountByAccountNumber(String accountNumber) {
        return AccountResponse.of(accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new NotFoundException(ErrorType.ACCOUNT_NOT_FOUND)));
    }

    public void addAccount(AccountRequest accountRequest) {
        Member foundMember = memberRepository.findById(accountRequest.getAccountHolderId())
                .orElseThrow(() -> new NotFoundException(ErrorType.MEMBER_NOT_FOUND));
        Account account = Account.createAccount(foundMember, createRandomAccountNumber());
        accountRepository.save(account);
    }

    private String createRandomAccountNumber() {
        StringBuffer sb = new StringBuffer(RandomStringUtils.randomNumeric(6));
        String randomAccountNumber = sb.insert(4, "-").toString();
        return validateAccountNumber(randomAccountNumber);
    }

    private String validateAccountNumber(String randomAccountNumber) {
        if(accountRepository.findByAccountNumber(randomAccountNumber).isPresent()) {
            return createRandomAccountNumber();
        }
        return randomAccountNumber;
    }
}
