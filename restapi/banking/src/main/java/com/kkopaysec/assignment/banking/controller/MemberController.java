package com.kkopaysec.assignment.banking.controller;

import com.kkopaysec.assignment.banking.dto.MemberRequest;
import com.kkopaysec.assignment.banking.dto.MemberResponse;
import com.kkopaysec.assignment.banking.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Api(tags = "Member")
public class MemberController {
    
    private final MemberService memberService;
    
    @GetMapping
    @ApiOperation(value = "전체 사용자 목록 조회하기", notes = "<strong>전체 사용자 목록</strong>을 조회합니다.")
    public ResponseEntity<List<MemberResponse>> findAllMembers() {
        List<MemberResponse> members = memberService.findAllMembers();
        return ResponseEntity.ok(members);
    }

    @GetMapping("{memberId}")
    @ApiOperation(value = "ID로 사용자 정보 조회하기", notes = "<strong>memberId로 사용자 정보</strong>를 조회합니다.")
    public ResponseEntity<MemberResponse> findMemberById(@PathVariable Long memberId) {
        MemberResponse member = memberService.findMemberById(memberId);
        return ResponseEntity.ok(member);
    }

    // TODO Validation Error처리
    @PostMapping
    @ApiOperation(value = "사용자 추가하기", notes = "<strong>사용자</strong>를 추가합니다.")
    public ResponseEntity<Void> addMember(@RequestBody @Valid MemberRequest memberRequest) {
        memberService.addMember(memberRequest);
        return ResponseEntity.ok().build();
    }

}
