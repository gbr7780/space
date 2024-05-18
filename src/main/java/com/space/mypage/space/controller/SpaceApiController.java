package com.space.mypage.space.controller;

import com.space.mypage.space.service.SpaceService;
import com.space.mypage.space.dto.SpaceListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description    :
 * packageName    : com.space.mypage.space.controller
 * fileName        : SpaceApiController
 * author         : kimminsol
 * date           : 2024/05/18
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/18        kimminsol       최초 생성
 */

@RestController
@RequestMapping("/mypage/space/api")
@RequiredArgsConstructor
public class SpaceApiController {

    private final SpaceService spaceService;

    @PostMapping("/list")
    public List<SpaceListDto> spaceList (@RequestBody final SpaceListDto dto) {
        // 로그인한 사용자의 스페이스 리스트 조회
        List<SpaceListDto> spaceList = spaceService.list(dto.getMemberId());
        return spaceList;
    }
}
