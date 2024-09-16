package com.ssafy.side.api.member.service;

import com.ssafy.side.api.member.domain.Member;
import com.ssafy.side.api.member.domain.MemberRepository;
import com.ssafy.side.api.member.dto.GetMemberInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public GetMemberInfoResponseDto getMemberInfo(Long memberId) {
        Member findMember = memberRepository.findMemberByIdOrThrow(memberId);
        return new GetMemberInfoResponseDto(findMember.getNickname());
    }
}
