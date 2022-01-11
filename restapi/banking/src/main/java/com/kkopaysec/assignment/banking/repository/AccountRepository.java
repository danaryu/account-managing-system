package com.kkopaysec.assignment.banking.repository;

import com.kkopaysec.assignment.banking.domain.Account;
import com.kkopaysec.assignment.banking.domain.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
}
