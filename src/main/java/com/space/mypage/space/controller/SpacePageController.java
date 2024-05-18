package com.space.mypage.space.controller;

import com.space.config.auth.PrincipalDetails;
import com.space.member.entity.Member;
import com.space.member.repository.MemberRepository;
import com.space.mypage.category.repository.CategoryRepository;
import com.space.mypage.space.service.SpaceService;
import com.space.space.repository.SpaceRepository;
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
 * packageName    : com.space.mypage.space.controller
 * fileName        : spacePageController
 * author         : kimminsol
 * date           : 2024/05/18
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/18        kimminsol       최초 생성
 */

@RequestMapping("/mypage/space")
@Controller
@Slf4j
@RequiredArgsConstructor
public class SpacePageController {

    @Autowired
    private SpaceService spaceService;

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MemberRepository memberRepository;

    // 스페이스 리스트 조회
	@GetMapping("")
	public String space(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
		// 로그인한 사용자 ID
		Long memId = principalDetails.getMember().getId();
		model.addAttribute("memId", memId);

		// 사이드바 - 사용자 정보
		Member member = memberRepository.findMemberById(memId);
		model.addAttribute("member", member);

		return "myPage/space/spaceList";
	}
}