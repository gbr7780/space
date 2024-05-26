package com.space.space.repository;

import com.space.space.dto.SpaceResponseDto;
import com.space.space.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpaceRepository extends JpaRepository<Space, Long> {

    // 스페이스 리스트 조회
    List<Space> findByMemberId(Long memberId);


    // 스페이스 메인 출력
    @Query("select s.content as content, s.title as title, c.type as type from Space s right JOIN s.category c where c.member.id = :memberId order by s.id")
    List<SpaceResponseDto> findSpcaeAll(@Param("memberId") Long memberId);
}
