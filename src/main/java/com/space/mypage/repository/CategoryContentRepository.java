package com.space.mypage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.space.mypage.entity.CategoryContent;

public interface CategoryContentRepository extends JpaRepository<CategoryContent, Long> {
	
}
