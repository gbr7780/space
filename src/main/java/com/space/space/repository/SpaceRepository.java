package com.space.space.repository;

import com.space.space.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpaceRepository extends JpaRepository<Space, Long> {

    // 스페이스 리스트 조회
    List<Space> findByMemberId(Long memberId);
}
