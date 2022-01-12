package com.kkopaysec.assignment.banking.dto;

import com.kkopaysec.assignment.banking.domain.Member;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MemberResponse {

    private Long id;
    private String name;
    private int age;
    private String membershipDate;

    public MemberResponse(Long id, String name, int age, String membershipDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.membershipDate = membershipDate;
    }

    public static MemberResponse of(Member member) {
        return new MemberResponse(member.getId(), member.getName(), member.getAge(), member.getMembershipDate());
    }

    public static List<MemberResponse> toList(List<Member> members) {
        return members.stream()
                .map(member -> of(member))
                .collect(Collectors.toList());
    }
}

