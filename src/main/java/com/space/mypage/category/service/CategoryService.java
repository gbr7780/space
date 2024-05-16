package com.space.mypage.category.service;

import com.space.member.entity.Member;
import com.space.member.repository.MemberRepository;
import com.space.mypage.category.dto.CategoryDto;
import com.space.mypage.category.entity.Category;
import com.space.mypage.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * description    :
 * packageName    : com.space.mypage.category.service
 * fileName        : CategoryService
 * author         : kimminsol
 * date           : 2024/05/14
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/14        kimminsol       최초 생성
 */

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final MemberRepository memberRepository;

    // 카테고리 리스트 조회
    public List<CategoryDto> getCategoryList(Long memberId) {
        List<Category> lists = categoryRepository.findByMemberId(memberId);
        return lists.stream().map(CategoryDto::new).collect(Collectors.toList());
    }

    // 카테고리 수정
    public void update(List<CategoryDto> dtoList) {
        for (CategoryDto dto : dtoList) {
            Category category = new Category(dto);
            Member member = memberRepository.findMemberById(dto.getMemberId());
            category.setMember(member);
            categoryRepository.save(category);
        }
    }
}
