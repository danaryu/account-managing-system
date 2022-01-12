package com.kkopaysec.assignment.banking.service;

import com.kkopaysec.assignment.banking.domain.Member;
import com.kkopaysec.assignment.banking.dto.MemberResponse;
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

}
