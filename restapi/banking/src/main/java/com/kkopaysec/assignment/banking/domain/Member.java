package com.kkopaysec.assignment.banking.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String membershipDate;

    @OneToMany(mappedBy = "accountHolder")
    private List<Account> accounts = new ArrayList<>();

    @Builder
    private Member(Long id, String name, int age, String membershipDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.membershipDate = membershipDate;
        this.accounts = new ArrayList<>();
    }

    public static Member createMember(String name, int age, String membershipDate) {
        return Member.builder()
                .name(name)
                .age(age)
                .membershipDate(membershipDate)
                .build();
    }

    public void addAccounts(Account account) {
        this.accounts.add(account);
    }

}
