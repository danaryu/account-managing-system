package com.kkopaysec.assignment.banking.service;

import com.kkopaysec.assignment.banking.domain.Member;
import com.kkopaysec.assignment.banking.dto.MemberRequest;
import com.kkopaysec.assignment.banking.dto.MemberResponse;
import com.kkopaysec.assignment.banking.exception.NotFoundException;
import com.kkopaysec.assignment.banking.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<MemberResponse> findAllMembers() {
        return MemberResponse.toList(memberRepository.findAll());
    }

    public MemberResponse findMemberById(Long memberId) {
        return MemberResponse.of(memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("member를 찾을 수 없습니다.")));
    }

    public void addMember(MemberRequest memberRequest) {
        Member member = Member.createMember(memberRequest.getName(), memberRequest.getAge(), memberRequest.getMembershipDate());
        memberRepository.save(member);
    }
}
