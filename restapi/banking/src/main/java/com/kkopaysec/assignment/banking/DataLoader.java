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
        List<Member> members = getListFromFile("data/member.csv").stream()
                .map(line -> {
                    String[] split = line.split(",");
                    System.out.println("split[1] = " + split[1]);
                    return Member.builder()
                            .name(split[1])
                            .age(Integer.parseInt(split[2]))
                            .membershipDate(split[3])
                            .build();
                }).collect(Collectors.toList());

        memberRepository.saveAll(members);
    }

    private void setupAccount() throws IOException {
        List<Account> accounts = getListFromFile("data/account.csv").stream()
                .map(line -> {
                    String[] split = line.split(",");
                    Member foundMember = memberRepository.findById(Long.parseLong(split[0]))
                            .orElseThrow(() -> new MemberNotFoundException("member를 찾을 수 없습니다"));

                    return Account.builder()
                            .member(foundMember)
                            .accountNumber(split[1])
                            .build();
                }).collect(Collectors.toList());

        accountRepository.saveAll(accounts);
    }

    private void setupAccountHistory() throws IOException {
        List<AccountHistory> accountHistories = getListFromFile("data/history.csv").stream()
                .map(line -> {
                    String[] split = line.split(",");
                    Account foundAccount = accountRepository.findByAccountNumber(split[0])
                            .orElseThrow(() -> new NotFoundException("member를 찾을 수 없습니다"));

                    return AccountHistory.builder()
                            .account(foundAccount)
                            .accountStatus(AccountStatus.valueOf(split[1]))
                            .amount(new BigDecimal(split[2]))
                            .businessDate(split[3])
                            .build();
                }).collect(Collectors.toList());

        accountHistoryRepository.saveAll(accountHistories);
    }

    private List<String> getListFromFile(String path) throws IOException {
        Resource resource = new ClassPathResource(path);
        List<String> listFromFile = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8);
        listFromFile.remove(0);

        return listFromFile;
    }

}
