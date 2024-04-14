package com.space.mypage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.space.mypage.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	Category findCategoryById(Long id);

}
