package com.space.mypage.space.dto;

import com.space.space.entity.Space;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * description    : 스페이스 일괄 수정 DTO
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
@NoArgsConstructor
public class SpaceAllUpdateDto {
    private Long spaceId;       // 스페이스 id

    private String openYn;      // 스페이스 공개여부

    private Long categoryId;    // 카테고리 id
}
