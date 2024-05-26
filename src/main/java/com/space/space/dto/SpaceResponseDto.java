package com.space.space.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * description    : 화면 출력 사용할 Dto
 * packageName    : com.space.space.dto
 * fileName        : SpaceResponseDto
 * author         : kimjongha
 * date           : 2024/05/21
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * —————————————————————————————
 * 2024/05/21        kimjongha       최초 생성
 */
/*
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
 */
public interface SpaceResponseDto {
    String getTitle();

    String getContent();

    String getOpenYn();

    String getType();


}
