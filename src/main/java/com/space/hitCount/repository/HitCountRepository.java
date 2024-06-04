package com.space.hitCount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.space.hitCount.entity.HitCount;

public interface HitCountRepository extends JpaRepository<HitCount,Long>{
	
	List<HitCount> findAllByOrderByIdDesc();


	Long countBySpaceId(String spaceId);

}
