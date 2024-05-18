package com.space.sgg.dto;

import com.space.sgg.entity.Sgg;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)      // Mybatis에서 SELECT 결과를 객체에 매핑하기 위해서는 기본생성자가 필요함.
public class SggResponseDto {
    private String sggCd;       // 시군구 코드

    private String sggNm;       //  시군구 이름

    private String upprCd;      // 상위 시군구 코드


    public SggResponseDto(Sgg sgg) {
        this.sggCd = sgg.getSggCd();
        this.sggNm = sgg.getSggNm();
        this.upprCd = sgg.getUpprCd();
    }



}
