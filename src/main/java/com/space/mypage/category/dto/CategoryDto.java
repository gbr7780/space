package com.space.mypage.category.dto;

import com.space.mypage.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * description    :
 * packageName    : com.space.mypage.category.dto
 * fileName        : CategoryUpdateDto
 * author         : kimminsol
 * date           : 2024/05/14
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/14        kimminsol       최초 생성
 */

@Getter
@Setter
@AllArgsConstructor
public class CategoryDto {
	
	private Long categoryId;			// 카테고리 id
	
	private String categoryType;		// 카테고리 타입

	private Long memberId;				// 사용자 id

	// entity to dto
	public CategoryDto(Category category) {
		this.categoryId = category.getId();
		this.categoryType = category.getType();
	}
}
