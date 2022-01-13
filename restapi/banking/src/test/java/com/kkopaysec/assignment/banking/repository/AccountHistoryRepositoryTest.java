package com.kkopaysec.assignment.banking.repository;

import com.kkopaysec.assignment.banking.domain.Account;
import com.kkopaysec.assignment.banking.domain.AccountHistory;
import com.kkopaysec.assignment.banking.domain.AccountStatus;
import com.kkopaysec.assignment.banking.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;


@Transactional
@SpringBootTest
@Rollback(value = false)
class AccountHistoryRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountHistoryRepository accountHistoryRepository;

    Member member;
    Account account;

    @BeforeEach
    public void setup() throws Exception {
        member = Member.builder()
                .name("Liam")
                .age(63)
                .membershipDate("2016-11-05")
                .build();
        memberRepository.save(member);

        account = Account.builder()
                .accountNumber("1000-22")
                .build();
        account.belongsTo(member);
        accountRepository.save(account);

    }

    @Test
    public void save() throws Exception {
        AccountHistory accountHistory = AccountHistory.builder()
                .accountStatus(AccountStatus.Y)
                .amount(BigDecimal.valueOf(336700))
                .businessDate("2019-08-26")
                .build();

        accountHistory.setAccount(account);
        accountHistoryRepository.save(accountHistory);

        assertThat(account.getAccountHistories().get(0).getAmount()).isEqualTo(accountHistory.getAmount());
        assertThat(account.getAccountHistories().get(0).getBusinessDate()).isEqualTo(accountHistory.getBusinessDate());

    }


}