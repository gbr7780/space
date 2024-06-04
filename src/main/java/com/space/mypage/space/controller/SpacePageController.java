package com.space.mypage.space.controller;

import com.space.config.auth.PrincipalDetails;
import com.space.member.entity.Member;
import com.space.member.repository.MemberRepository;
import com.space.mypage.category.entity.Category;
import com.space.mypage.category.repository.CategoryRepository;
import com.space.mypage.constant.PrivateYn;
import com.space.mypage.space.dto.SpaceUpdateDto;
import com.space.mypage.space.service.SpaceService;
import com.space.space.dto.SpaceWriteDto;
import com.space.space.entity.Space;
import com.space.space.repository.SpaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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
    private MemberRepository memberRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SpaceService spaceService;

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

	// 스페이스 수정
	@GetMapping(value = "/update/{spaceId}")
	public String mypageSpaceUpdate(@PathVariable("spaceId") Long id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		// 로그인한 사용자 ID
		Long memId = principalDetails.getMember().getId();
		model.addAttribute("memId", memId);

		Member member = memberRepository.findMemberById(memId);
		model.addAttribute("member", member);


		Space content = spaceService.spaceDetail(id);

		model.addAttribute("content", content);
		model.addAttribute("category_value", content.getCategory().getId());

		return "myPage/space/spaceUpdate";
	}
}