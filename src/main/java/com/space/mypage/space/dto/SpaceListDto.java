package com.space.mypage.space.dto;

import com.space.space.entity.Space;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * description    :
 * packageName    : com.space.mypage.space.dto
 * fileName        : SpaceListDto
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
public class SpaceListDto {
    private Long spaceId;       // 스페이스 id

    private String title;       // 스페이스 제목

    private String openYn;      // 스페이스 공개여부

    private Long memberId;      // 사용자 id

    // entity to dto
    public SpaceListDto(Space space) {
        this.spaceId = space.getId();
        this.title = space.getTitle();
        this.openYn = space.getOpenYn();
        this.memberId = space.getMember().getId();
    }
}
