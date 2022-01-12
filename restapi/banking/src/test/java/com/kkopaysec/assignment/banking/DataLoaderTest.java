package com.kkopaysec.assignment.banking;

import com.kkopaysec.assignment.banking.domain.Account;
import com.kkopaysec.assignment.banking.domain.AccountHistory;
import com.kkopaysec.assignment.banking.domain.Member;
import com.kkopaysec.assignment.banking.repository.AccountHistoryRepository;
import com.kkopaysec.assignment.banking.repository.AccountRepository;
import com.kkopaysec.assignment.banking.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

@SpringBootTest
public class DataLoaderTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AccountHistoryRepository accountHistoryRepository;

    @Autowired
    AccountRepository accountRepository;

    @Test
    @DisplayName("csv파일의 멤버 list와 table의 멤버 list가 같다.")
    public void memberLoadTest() throws Exception {

        //given
        Resource resource = new ClassPathResource("data/member.csv");
        List<String> linesFromFile = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8);
        linesFromFile.remove(0);

        //when
        List<Member> allMembers = memberRepository.findAll();

        //then
        Assertions.assertThat(allMembers.size()).isEqualTo(linesFromFile.size());

    }

    @Test
    @DisplayName("csv파일의 계좌 list와 table의 계좌 list가 같다.")
    public void accountLoadTest() throws Exception {

        //given
        Resource resource = new ClassPathResource("data/account.csv");
        List<String> linesFromFile = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8);
        linesFromFile.remove(0);

        //when
        List<Account> allAccounts = accountRepository.findAll();

        //then
        Assertions.assertThat(allAccounts.size()).isEqualTo(linesFromFile.size());

    }

    @Test
    @DisplayName("csv파일의 계좌내역 수와 table의 계좌내역 수가 같다.")
    public void historyLoadTest() throws Exception {

        //given
        Resource resource = new ClassPathResource("data/history.csv");
        List<String> linesFromFile = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8);
        linesFromFile.remove(0);

        //when
        List<AccountHistory> allHistories = accountHistoryRepository.findAll();

        //then
        Assertions.assertThat(allHistories.size()).isEqualTo(linesFromFile.size());

    }



}
