package com.ssafy.side.api.member.controller;

import com.ssafy.side.api.member.dto.GetMemberInfoResponseDto;
import com.ssafy.side.api.member.service.MemberService;
import com.ssafy.side.common.util.MemberUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Tag(name = "Member - 멤버 관련 API", description = "Member API Document")
public class MemberController {

    private final MemberService memberService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "유저 닉네임을 조회하는 API 입니다.")
    public ResponseEntity<GetMemberInfoResponseDto> getMemberInfo(Principal principal) {
        Long memberId = MemberUtil.getUserId(principal);
        return ResponseEntity.ok(memberService.getMemberInfo(memberId));
    }

}
