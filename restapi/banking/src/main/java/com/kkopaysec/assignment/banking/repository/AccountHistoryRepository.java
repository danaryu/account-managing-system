package com.kkopaysec.assignment.banking.repository;

import com.kkopaysec.assignment.banking.domain.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHistoryRepository extends JpaRepository<AccountHistory, Long> {
}
