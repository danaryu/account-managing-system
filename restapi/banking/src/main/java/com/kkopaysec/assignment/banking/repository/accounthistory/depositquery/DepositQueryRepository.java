package com.kkopaysec.assignment.banking.repository.accounthistory.depositquery;

import com.kkopaysec.assignment.banking.domain.AccountHistory;
import com.kkopaysec.assignment.banking.domain.AccountStatus;
import com.kkopaysec.assignment.banking.domain.Member;
import com.kkopaysec.assignment.banking.dto.AgeAndDepositByMember;
import com.kkopaysec.assignment.banking.dto.DepositByMemberAccount;
import com.kkopaysec.assignment.banking.dto.DepositByPeriod;
import com.kkopaysec.assignment.banking.dto.DepositSumByYear;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositQueryRepository {

    @Query("select distinct new com.kkopaysec.assignment.banking.dto.DepositByMemberAccount(" +
            "SUM(case when ah.accountStatus = com.kkopaysec.assignment.banking.domain.AccountStatus.Y then ah.amount else (-1*(ah.amount)) END)" +
            ", ah.account.accountNumber)" +
            "from AccountHistory as ah " +
            "inner join ah.account " +
            "where ah.account.accountHolder = :accountHolder " +
            "group by ah.account.accountNumber")
    List<DepositByMemberAccount> findAllDepositsByMemberAccount(@Param("accountHolder") Member accountHolder);

    @Query("select distinct new com.kkopaysec.assignment.banking.dto.AgeAndDepositByMember(m.id, m.age, " +
            "SUM(case when ah.accountStatus = com.kkopaysec.assignment.banking.domain.AccountStatus.Y then ah.amount else (-1*(ah.amount)) END)) " +
            "from AccountHistory ah, Account a, Member m  " +
            "where ah.account = a.accountNumber " +
            "and a.accountHolder = m.id " +
            "group by m.id, m.age")
    List<AgeAndDepositByMember> findAllAgeAndDepositByMember();

    @Query("select distinct new com.kkopaysec.assignment.banking.dto.DepositSumByYear(substring(ah.businessDate, 1, 4), " +
            "SUM(case when ah.accountStatus = com.kkopaysec.assignment.banking.domain.AccountStatus.Y then ah.amount else (-1*(ah.amount)) END)) " +
            "from AccountHistory ah " +
            "where substring(ah.businessDate, 1, 4) = :year " +
            "group by substring(ah.businessDate, 1, 4)")
    DepositSumByYear findDepositSumByYear(@Param("year") String year);

    @Query("select distinct new com.kkopaysec.assignment.banking.dto.DepositByPeriod(m.id, m.name, " +
            "SUM(case when ah.accountStatus = com.kkopaysec.assignment.banking.domain.AccountStatus.Y then ah.amount else (-1*(ah.amount)) END)) " +
            "from AccountHistory ah, Account a, Member m " +
            "where ah.account = a.accountNumber " +
            "and a.accountHolder = m.id " +
            "and to_date(ah.businessDate, 'yyyy-MM-dd') between parsedatetime(:startDate, 'yyyy-MM-dd') and parsedatetime(:endDate, 'yyyy-MM-dd') " +
            "group by m.id, m.name " +
            "order by 3 desc")
    List<DepositByPeriod> findAllDepositByPeriod(@Param("startDate") String startDate, @Param("endDate") String endDate);

}
