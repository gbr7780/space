package com.space.mypage.space.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * description    :
 * packageName    : com.space.mypage.space.dto
 * fileName        : SpaceUpdateDto
 * author         : kimminsol
 * date           : 6/2/24
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/2/24        kimminsol       최초 생성
 */

@Getter
@Setter
public class SpaceUpdateDto {

    private Long spaceId;

    private Long categoryId;

    private String title;

    private String content;

    private String openYn;

    private Long memberId;
}

