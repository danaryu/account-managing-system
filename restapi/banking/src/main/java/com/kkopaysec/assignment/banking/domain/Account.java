package com.kkopaysec.assignment.banking.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_holder", foreignKey = @ForeignKey(name = "fk_account_to_member"))
    private Member accountHolder;

    @OneToMany(mappedBy = "account")
    private List<AccountHistory> accountHistories = new ArrayList<>();

    @Builder
    private Account(Long id, String accountNumber, Member accountHolder) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.accountHistories = new ArrayList<>();
    }

    public static Account createAccount(Member accountHolder, String accountNumber) {
        return Account.builder()
                .accountHolder(accountHolder)
                .accountNumber(accountNumber)
                .build();
    }

    public void belongsTo(Member member) {
        this.accountHolder = member;
        member.addAccounts(this);
    }

    public void addAccountHistory(AccountHistory history) {
        this.accountHistories.add(history);
    }

}
