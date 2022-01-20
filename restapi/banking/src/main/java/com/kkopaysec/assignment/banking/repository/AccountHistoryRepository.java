package com.kkopaysec.assignment.banking.repository;

import com.kkopaysec.assignment.banking.domain.Account;
import com.kkopaysec.assignment.banking.domain.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountHistoryRepository extends JpaRepository<AccountHistory, Long> {
    List<AccountHistory> findAllByAccount(Account account);

    @Query("select distinct ah " +
            " from AccountHistory ah " +
            " join fetch ah.account a " +
            " join fetch a.accountHolder h ")
    List<AccountHistory> findAllWithAccountAndMember();
}