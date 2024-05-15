package com.space.mypage.category.repository;

import com.space.mypage.category.dto.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;

import com.space.mypage.category.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	Category findCategoryById(Long id);

	List<Category> findByMemberId(Long memberId);
}
