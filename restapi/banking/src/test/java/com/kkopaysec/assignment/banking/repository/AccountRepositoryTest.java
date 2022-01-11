package com.kkopaysec.assignment.banking.repository;

import com.kkopaysec.assignment.banking.domain.Account;
import com.kkopaysec.assignment.banking.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Rollback(value = false)
@Transactional
class AccountRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AccountRepository accountRepository;

    Member member;

    @BeforeEach
    public void setupMember() throws Exception {
        member = Member.builder()
                .name("Liam")
                .age(63)
                .membershipDate("2016-11-05")
                .build();
        memberRepository.save(member);

    }

    @Test
    public void save() throws Exception {
        Account account = Account.builder()
                .accountNumber("1000-22")
                .build();
        account.belongsTo(member);
        accountRepository.save(account);

        Assertions.assertThat(member.getAccounts().get(0).getAccountNumber()).isEqualTo(account.getAccountNumber());

    }

}
