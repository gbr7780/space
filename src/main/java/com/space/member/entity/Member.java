package com.space.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.space.member.constant.Area;
import com.space.member.constant.Role;
import com.space.member.dto.OauthAddInfoDto;
import com.space.utils.entity.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@DynamicUpdate
@Table(name="member")
@Getter @Setter
@NoArgsConstructor
@ToString
public class Member extends BaseEntity {
    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="member_name")
    private String name;

    // 회원은 이메일을 통해 유일하게 구분해야 하기 때문에, 동일한 값이 DB에 저장할 수 없도록 unique 속성 지정한다.
    @Column(unique = true, name="member_email")
    private String email;
    
    @Column(name="member_password")
    private String password;
    
    // 분야
    @Enumerated(EnumType.STRING)
    @Column(name="member_area")
    private Area area;
    
    // 스페이스 주소
    @Column(unique = true, name="member_spaceId")
    private String spaceId;

    // enum 타입은 기본적으로 순서가 저장되는데 순서가 바뀌면 문제가 생기므로 STRING 옵션 설정한다.
    @Enumerated(EnumType.STRING)
    private Role role;
    
    // OAuth로그인 서비스 제공자
    private String provider;
    
    @Column(unique = true)
    // OAuth로그인한 내 계정에 대한 Id
    private String providerId;

    // 프로필 사진
    private String image;

    // 스페이스 네임
    @Column(name="space_name")
    private String spaceName;
    
    // 공개 여부
    @Column(name="member_allPublicYn")
    private String allPublicYn;

    // 시군구 코드
    @Column(name="member_sggCd")
    private String sggCd;
    
//    @OneToMany(mappedBy = "member")
//    private List<Cash> cashs = new ArrayList<>();

    public void addInfoOAuth2(OauthAddInfoDto addInfoDto) {
    	this.area = addInfoDto.getArea();
    	this.spaceId = addInfoDto.getSpaceId();
        this.sggCd = addInfoDto.getSggCd();
    }
     
    @Builder
	public Member(String email, String password, Role role, String provider, String providerId,String name,String image,String allPublicYn, String spaceName) {
		this.email = email;
		this.password = password;
		this.role = role;
		this.provider = provider;
		this.providerId = providerId;
		this.name = name;
		this.image = image;
		this.allPublicYn = allPublicYn;
        this.spaceName = spaceName;
	}
    
}
