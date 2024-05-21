package com.space.mypage.externalSpace.controller;

import com.space.member.dto.MemberExternalDto;
import com.space.member.service.MemberService;
import com.space.mypage.space.dto.SpaceListDto;
import com.space.mypage.space.service.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description    :
 * packageName    : com.space.mypage.externalSpace.controller
 * fileName        : ExternalSpaceApiController
 * author         : kimminsol
 * date           : 2024/05/18
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/18        kimminsol       최초 생성
 */

@RestController
@RequestMapping("/mypage/externalSpace/api")
@RequiredArgsConstructor
public class ExternalSpaceApiController {
    private final MemberService memberService;

    // 외부 스페이스 목록 조회
    @GetMapping("/list")
    public List<MemberExternalDto> spaceList () {
//        List<MemberExternalDto> externalSpaceList = memberService.externalSpaceList("Y");
        List<MemberExternalDto> externalSpaceList = memberService.externalSpaceList();
        return externalSpaceList;
    }
}
