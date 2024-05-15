package com.space.mypage.category.service;

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


    /**
     *
     * @param memberId
     * @return
     */
    public List<CategoryDto> getCategoryList(Long memberId) {
        List<Category> lists = categoryRepository.findByMemberId(memberId);
        return lists.stream().map(CategoryDto::new).collect(Collectors.toList());
    }
}
