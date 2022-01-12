package com.kkopaysec.assignment.banking;

import com.kkopaysec.assignment.banking.domain.Account;
import com.kkopaysec.assignment.banking.domain.AccountHistory;
import com.kkopaysec.assignment.banking.domain.AccountStatus;
import com.kkopaysec.assignment.banking.domain.Member;
import com.kkopaysec.assignment.banking.exception.MemberNotFoundException;
import com.kkopaysec.assignment.banking.exception.NotFoundException;
import com.kkopaysec.assignment.banking.repository.AccountHistoryRepository;
import com.kkopaysec.assignment.banking.repository.AccountRepository;
import com.kkopaysec.assignment.banking.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;
    private final AccountHistoryRepository accountHistoryRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        setupMember();
        setupAccount();
        setupAccountHistory();
    }

    private void setupMember() throws IOException {
        List<Member> members = readLineFromFile("data/member.csv")
                .map(line -> {
                    return Member.createMember(line[1], Integer.parseInt(line[2]), line[3]);
                }).collect(Collectors.toList());

        memberRepository.saveAll(members);
    }

    private void setupAccount() throws IOException {
        List<Account> accounts = readLineFromFile("data/account.csv")
                .map(line -> {
                    Member foundMember = memberRepository.findById(Long.parseLong(line[0]))
                            .orElseThrow(() -> new MemberNotFoundException("member를 찾을 수 없습니다."));
                    return Account.createAccount(foundMember, line[1]);
                }).collect(Collectors.toList());

        accountRepository.saveAll(accounts);
    }

    private void setupAccountHistory() throws IOException {
        List<AccountHistory> accountHistories = readLineFromFile("data/history.csv")
                .map(line -> {
                    Account foundAccount = accountRepository.findByAccountNumber(line[0])
                            .orElseThrow(() -> new NotFoundException("account를 찾을 수 없습니다."));
                    return AccountHistory.createHistory(foundAccount, AccountStatus.valueOf(line[1]), new BigDecimal(line[2]), line[3]);
                }).collect(Collectors.toList());

        accountHistoryRepository.saveAll(accountHistories);
    }

    private Stream<String[]> readLineFromFile(String path) throws IOException {
        Resource resource = new ClassPathResource(path);
        List<String> linesFromFile = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8);
        linesFromFile.remove(0); // 첫번째 행 제거
        return processLineByLine(linesFromFile);
    }

    private Stream<String[]> processLineByLine(List<String> linesFromFile) {
        return linesFromFile.stream().map(line -> line.split(","));
    }

}
