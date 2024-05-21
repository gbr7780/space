package com.space.mypage.externalSpace.controller;

import com.space.config.auth.PrincipalDetails;
import com.space.member.constant.Area;
import com.space.member.entity.Member;
import com.space.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description    :
 * packageName    : com.space.mypage.externalSpace.controller
 * fileName        : ExternalSpacePageController
 * author         : kimminsol
 * date           : 2024/05/18
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/18        kimminsol       최초 생성
 */

@RequestMapping("/mypage/externalSpace")
@Controller
@Slf4j
@RequiredArgsConstructor
public class ExternalSpacePageController {

    @Autowired
    private MemberRepository memberRepository;

    // 외부 스페이스 리스트 조회
    @GetMapping("")
    public String externalList(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
        // 로그인한 사용자 ID
        Long memId = principalDetails.getMember().getId();
        model.addAttribute("memId", memId);

        // 사이드바 - 사용자 정보
        Member member = memberRepository.findMemberById(memId);
        model.addAttribute("member", member);

        // 관심분야
        model.addAttribute("local", Area.values());

        return "/myPage/externalSpace/externalSpaceList";
    }
}
