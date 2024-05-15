package com.space.sgg.entity;

import com.space.utils.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "sgg")
public class Sgg extends BaseEntity {

    @Id
    @Column(name = "sgg_cd")
    private String sggCd;       // 시군구 코드

    @Column(name = "sgg_nm")
    private String sggNm;       //  시군구 이름

    @Column(name = "uppr_cd")
    private String upprCd;      // 상위 시군구

    @Column(name = "use_yn")
    private String useYn;       // 사용여부

}
