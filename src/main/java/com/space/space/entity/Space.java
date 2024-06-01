package com.space.space.entity;

import com.space.member.entity.Member;
import com.space.mypage.category.entity.Category;
import com.space.mypage.space.dto.SpaceAllUpdateDto;
import com.space.mypage.space.dto.SpaceListDto;
import com.space.space.dto.SpaceWriteDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "space")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Space {
	@Id
	@Column(name = "space_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 스페이스 타이틀
	@Column(name = "title")
	private String title;

	// 스페이스 내용
	@Column(columnDefinition = "TEXT", name = "content")
	private String content;

	// 스페이스 공개 여부
	@Column(name = "open_yn")
	private String openYn;

	// 다대일관계 카테고리
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	// 다대일관계 회원
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	// 객체 생성
	public static Space createContent(SpaceWriteDto spaceWriteDto) {
		Space content = new Space();
		content.setContent(spaceWriteDto.getContent());
		content.setOpenYn(spaceWriteDto.getOpenYn());
		content.setTitle(spaceWriteDto.getTitle());
		return content;
	}
	// dto to entity
	// 일괄 수정용
	public Space(SpaceAllUpdateDto dto) {
		this.id = dto.getSpaceId();
		this.openYn = dto.getOpenYn();
	}
}
