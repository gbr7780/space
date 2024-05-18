package com.space.member.dto;

import com.space.member.constant.Area;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * description    :
 * packageName    : com.space.member.dto
 * fileName        : MemberUpdateDto
 * author         : kimjongha
 * date           : 2024/05/16
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * —————————————————————————————
 * 2024/05/16        kimjongha       최초 생성
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateDto {
    private Area area;

    private String name;

    private MultipartFile filename;

    private String allPublicYn;

    private String spaceName;

    private String sggCd;

}
