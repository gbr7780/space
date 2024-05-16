package com.space.member.dto;

import com.space.member.constant.Area;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class OauthAddInfoDto {
    // 분야
    private Area area;
    // 스페이스 주소
    private String spaceId;
    // 지역코드
    private String sggCd;
    
}
