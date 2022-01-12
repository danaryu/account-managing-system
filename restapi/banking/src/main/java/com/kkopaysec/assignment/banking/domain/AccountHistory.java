package com.kkopaysec.assignment.banking.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "account_history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_number", referencedColumnName = "account_number", foreignKey = @ForeignKey(name = "fk_history_to_account"))
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status")
    private AccountStatus accountStatus;

    private BigDecimal amount;

    @Column(name = "business_date")
    private String businessDate;

    @Builder
    public AccountHistory(Account account, AccountStatus accountStatus, BigDecimal amount, String businessDate) {
        this.account = account;
        this.accountStatus = accountStatus;
        this.amount = amount;
        this.businessDate = businessDate;
    }

    public static AccountHistory createHistory(Account account, AccountStatus accountStatus, BigDecimal amount, String businessDate) {
        return AccountHistory.builder()
                .account(account)
                .accountStatus(accountStatus)
                .amount(amount)
                .businessDate(businessDate)
                .build();
    }

    public void setAccount(Account account) {
        this.account = account;
        account.addAccountHistory(this);
    }
}
