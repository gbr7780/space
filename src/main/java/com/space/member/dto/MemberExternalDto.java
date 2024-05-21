package com.space.member.dto;

import com.space.member.constant.Area;
import com.space.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * description    :
 * packageName    : com.space.member.dto
 * fileName        : MemberExternalDto
 * author         : kimminsol
 * date           : 2024/05/18
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/18        kimminsol       최초 생성
 */

@Getter
@Setter
@AllArgsConstructor
public class MemberExternalDto {
    private String memberSpaceId;       // 스페이스 주소

    private String spaceName;           // 스페이스 메인 이름

    private Area area;                // 카테고리

    private Long hitCount;

    // entity to dto
    public MemberExternalDto(Member member){
        this.memberSpaceId = member.getSpaceId();
        this.spaceName = member.getSpaceName();
    }

    public MemberExternalDto(String spaceName, Area area, Long hitCount) {
        this.spaceName = spaceName;
        this.area = area;
        this.hitCount = hitCount;
    }
}
