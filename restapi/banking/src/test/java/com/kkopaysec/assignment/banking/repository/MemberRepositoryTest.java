package com.kkopaysec.assignment.banking.repository;

import com.kkopaysec.assignment.banking.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void save() throws Exception {
        Member member = Member.builder()
                .name("Liam")
                .age(63)
                .membershipDate("2016-11-05")
                .build();

        memberRepository.save(member);
        memberRepository.findById(member.getId()).equals(member);
    }
}