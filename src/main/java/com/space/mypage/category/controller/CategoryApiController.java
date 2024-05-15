package com.space.mypage.category.controller;

import com.space.member.repository.MemberRepository;
import com.space.mypage.category.dto.CategoryDto;
import com.space.mypage.category.repository.CategoryRepository;
import com.space.mypage.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description    :
 * packageName    : com.space.mypage.category.controller
 * fileName        : CategoryApiController
 * author         : kimminsol
 * date           : 2024/05/14
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/14        kimminsol       최초 생성
 */

@RestController
@RequestMapping("/mypage/category/api")
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryService categoryService;

    private final CategoryRepository categoryRepository;

    private final MemberRepository memberRepository;

    // 카테고리 리스트 조회
    @PostMapping("/list")
    public List<CategoryDto> categoryList (@RequestBody final CategoryDto dto) {
        // 로그인한 사용자의 카테고리 리스트조회
        List<CategoryDto> categoryList = categoryService.getCategoryList(dto.getMemberId());
        return categoryList;
    }

    // 카테고리 수정
    @PatchMapping("/update")
    public List<CategoryDto> categoryUpdate(@RequestBody final List<CategoryDto> dtoList) {
        categoryService.update(dtoList);
//        return categoryService.update(id, params);
        return dtoList;
    }
}
