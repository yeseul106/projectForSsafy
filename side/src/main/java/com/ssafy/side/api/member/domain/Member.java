package com.ssafy.side.api.member.domain;

import com.ssafy.side.common.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String nickname;

  @Column(nullable = false, name = "social_id")
  private String socialId;

  @Column(name = "refresh_token")
  private String refreshToken;

  @Builder
  public Member(String nickname, String socialId, String refreshToken) {
    this.nickname = nickname;
    this.socialId = socialId;
    this.refreshToken = refreshToken;
  }

  public void updateRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

}
