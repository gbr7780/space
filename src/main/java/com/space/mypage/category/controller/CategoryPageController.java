package com.space.mypage.category.controller;

import com.space.config.auth.PrincipalDetails;
import com.space.member.entity.Member;
import com.space.member.repository.MemberRepository;
import com.space.mypage.category.dto.CategoryDto;
import com.space.mypage.category.entity.Category;
import com.space.mypage.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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
    private CategoryRepository categoryRepository;

    @Autowired
    private MemberRepository memberRepository;

    // 마이페이지 - 카테고리 관리
    @GetMapping("")
    public String categoryList(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {

        // 로그인한 사용자 ID
        Long memId = principalDetails.getMember().getId();

        // 사이드바 - 사용자 정보
        Member member = memberRepository.findMemberById(memId);
        model.addAttribute("member", member);
        model.addAttribute("memId", memId);

        return "myPage/category/categoryList";
    }

//    // 마이페이지 - 카테고리 수정
//    @GetMapping("/category/update")
//    public String mypageCategoryUpdate(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
//        // 스페이스 이름
////		String spaceId = principalDetails.getMember().getSpaceId();
////		model.addAttribute("spaceId", spaceId);
//
//        // 로그인한 사용자 ID
//        Long memId = principalDetails.getMember().getId();
//        model.addAttribute("memId", memId);
//
//        List<Category> lists = categoryRepository.findAll();
//        List<String> categorys = new ArrayList<>(); // 로그인한 사용자의 카테고리 이름을 저장할 리스트 선언
//
//        List<Long> categorysId = new ArrayList<>(); // 로그인한 사용자의 카테고리 이름을 저장할 리스트 선언
//
//        for (int i = 0; i < lists.size(); i++) {
//            if (lists.get(i).getMember().getId().equals(memId)) {
//                String categoryName = lists.get(i).getType();
//
//                categorys.add(categoryName); // 리스트에 카테고리 이름 저장
//
//                Long categoryId = lists.get(i).getId();
//                categorysId.add(categoryId); // 리스트에 카테고리 이름 저장
//            }
//        }
//        model.addAttribute("categorys", categorys);
//        model.addAttribute("categorysId", categorysId);
//
////		List<Category> lists = categoryRepository.findAll();
////		model.addAttribute("lists", lists);
//
////		for (Long i = Long.valueOf(1); i < 6; i++) {
////			Category category = categoryRepository.findCategoryById(i);
////			model.addAttribute("category"+i, category);
////		}
//
//        //model.addAttribute("categoryUpdateDto", new CategoryDto());
//        return "myPage/category/categoryUpdate";
//    }

//    // 마이페이지 - 카테고리 수정
//    @PostMapping("/category/update")
//    public String mypageCategoryPost(CategoryDto categoryUpdateDto, Model model, List<Category> categories) {
////		log.info("-=============== 카테고리 관리 POST : " + categoryUpdateDto);
////		log.info("-=============== 카테고리 Id : " + categoryUpdateDto.getId());
////		log.info("-=============== 카테고리 Type : " + categoryUpdateDto.getType());
//
////		Long id = categoryUpdateDto.getId();
////		String type = categoryUpdateDto.getType();
////		categoryContentService.updateCtegory(id, type);
//
//        categoryRepository.saveAll(categories);
//
//        return "redirect:/myPage/category";
//    }
}
