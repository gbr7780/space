package com.space.mypage.category.controller;

import com.space.config.auth.PrincipalDetails;
import com.space.member.entity.Member;
import com.space.member.repository.MemberRepository;
import com.space.mypage.category.repository.CategoryRepository;
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
 * packageName    : com.space.mypage.category.controller
 * fileName        : CategoryPageController
 * author         : kimminsol
 * date           : 2024/05/14
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/14        kimminsol       최초 생성
 */


@RequestMapping("/mypage/category")
@Controller
@Slf4j
@RequiredArgsConstructor
public class CategoryPageController {

    @Autowired
    private MemberRepository memberRepository;

    // 카테고리 리스트 조회
    @GetMapping("")
    public String categoryList(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {

        // 로그인한 사용자 ID
        Long memId = principalDetails.getMember().getId();
        model.addAttribute("memId", memId);

        // 사이드바 - 사용자 정보
        Member member = memberRepository.findMemberById(memId);
        model.addAttribute("member", member);

        return "myPage/category/categoryList";
    }

}
