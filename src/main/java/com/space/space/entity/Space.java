package com.space.space.entity;

import com.space.member.entity.Member;
import com.space.mypage.category.entity.Category;
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
	@Column(name = "content")
	private String content;

	// 카테고리 공개,비공개
	@Column(name = "categoryYn")
	private String categoryYn;

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
		content.setCategoryYn(spaceWriteDto.getCategoryYn());
		content.setTitle(spaceWriteDto.getTitle());
		return content;
	}
}
