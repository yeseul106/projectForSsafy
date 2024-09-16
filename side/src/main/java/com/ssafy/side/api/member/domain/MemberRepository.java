package com.ssafy.side.api.member.domain;

import com.ssafy.side.common.exception.ErrorMessage;
import com.ssafy.side.common.exception.NotFoundException;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberById(Long id);

    boolean existsBySocialId(String socialId);
    Optional<Member> findByRefreshToken(String refreshToken);

    Optional<Member> findBySocialId(String socialId);

    default Member findMemberByIdOrThrow(Long memberId){
        return findMemberById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_MEMBER.getMessage()));
    }

    default Member findMemberBySocialIdOrThrow(String socialId) {
        return findBySocialId(socialId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_MEMBER.getMessage()));
    }

    default Member findByRefreshTokenOrThrow(String refreshToken) {
        return findByRefreshToken(refreshToken)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_MEMBER.getMessage()));
    }
}

