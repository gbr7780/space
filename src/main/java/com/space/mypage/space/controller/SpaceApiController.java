package com.space.mypage.space.controller;

import com.space.mypage.space.dto.SpaceAllUpdateDto;
import com.space.mypage.space.service.SpaceService;
import com.space.mypage.space.dto.SpaceListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
@Slf4j
public class SpaceApiController {

    private final SpaceService spaceService;

    // 로그인한 사용자의 스페이스 리스트 조회
    @PostMapping("/list")
    public List<SpaceListDto> spaceList (@RequestBody final SpaceListDto dto) {
        List<SpaceListDto> spaceList = spaceService.list(dto.getMemberId());
        return spaceList;
    }

    // 스페이스 삭제
    @DeleteMapping("delete/{checkArr}")
    public void delete(@PathVariable final Long[] checkArr) {
        for(int i=0; i<checkArr.length; i++){
            spaceService.delete(checkArr[i]);
        }
    }

    // 스페이스 일괄 수정
    @PostMapping("listUpdate/{checkArr}")
    public void listUpdate(@PathVariable final Long[] checkArr, @RequestBody final SpaceAllUpdateDto dto) {
        for(int i=0; i<checkArr.length; i++){
            dto.setSpaceId(checkArr[i]);
            spaceService.update(dto);
        }
    }
}
