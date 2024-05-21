package com.space.member.repository;

import com.space.member.dto.MemberExternalDto;
import org.springframework.data.jpa.repository.JpaRepository;

import com.space.member.entity.Member;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MemberRepository extends JpaRepository<Member,Long> {
    //회원 가입시 중복되지 않기 위해 이메일 검색하는 쿼리 메소드 추가
    Member findByEmail(String email);
    Member findByProviderId(String ProviderId);

    /**
     *
     * @param spaceId
     * @return count
     */
    Long countBySpaceId(String spaceId);

    // optional 타입으로 하고 findById(Long id)하는게 맞는데 빠르게 작업하기 위해 일단 이렇게 추후 변경
    Member findMemberById(Long id);

    // 외부 스페이스 목록 조회
    List<Member> findMemberByAllPublicYn(String openYn);

    // 외부 스페이스 목록 조회
    @Query("select new com.space.member.dto.MemberExternalDto(m.spaceName, m.area, COUNT(h)) FROM Member m JOIN HitCount h ON m.spaceId = h.spaceId WHERE m.allPublicYn = 'Y' GROUP BY m.spaceName, m.area")
    List<MemberExternalDto> findExternalSpaceList();

    // 조회수 증가
//    @Modifying
//    @Query("update Member m set m.view = m.view + 1 where m.id = :id")
//    int updateView(Long id);
}
