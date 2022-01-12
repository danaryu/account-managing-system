package com.kkopaysec.assignment.banking.controller;

import com.kkopaysec.assignment.banking.controller.dto.MemberRequest;
import com.kkopaysec.assignment.banking.controller.dto.MemberResponse;
import com.kkopaysec.assignment.banking.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberService memberService;
    
    @GetMapping
    public ResponseEntity<List<MemberResponse>> findAllMembers() {
        List<MemberResponse> members = memberService.getMembers();
        return ResponseEntity.ok(members);
    }

    @GetMapping("{memberId}")
    public ResponseEntity<MemberResponse> findMemberById(@PathVariable Long memberId) {
        MemberResponse member = memberService.getMember(memberId);
        return ResponseEntity.ok(member);
    }

    // TODO Validation Error처리
    @PostMapping
    public ResponseEntity<Void> addMember(@RequestBody @Valid MemberRequest memberRequest) {
        memberService.addMember(memberRequest);
        return ResponseEntity.ok().build();
    }

}
