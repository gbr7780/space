package com.space.mypage.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.space.space.entity.Space;
import com.space.space.repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.space.config.auth.PrincipalDetails;
import com.space.member.entity.Member;
import com.space.member.repository.MemberRepository;
import com.space.mypage.category.entity.Category;
import com.space.mypage.category.repository.CategoryRepository;

@Service
@Transactional
public class SpaceService {
	
	@Autowired
	private SpaceRepository spaceRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	// 마이페이지 작성하기 
	public Space saveSpace(@AuthenticationPrincipal PrincipalDetails principalDetails, Space space, String Category_id) {
		Long member_id = principalDetails.getMember().getId();
		Member member = memberRepository.findMemberById(member_id);
		
		Category category = categoryRepository.findCategoryById(Long.parseLong(Category_id));
		
		space.setMember(member);
		space.setCategory(category);
		
		return spaceRepository.save(space);
	}
	 
	public void allCatgory() {
		
		List<Category> categoryAll = categoryRepository.findAll();
	}
	
	// 카테고리 수정
//	public void updateContent(CategoryUpdateDto dto) {
//		 String[] id = dto.getId().split(",");
//		 String[] type = dto.getType().split(",");
//			
//		 for (int i = 0; i < id.length; i++) {
//			 long categoryId = Long.parseLong(id[i]);
//			 Category category = categoryRepository.findCategoryById(categoryId);
//			 category.setType(type[i]);
//		}
//	}
	
	public void updateCtegory(Long id, String type) {
		Category c = categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		c.setType(type);
		categoryRepository.save(c);
	}
	
	// 기본 카테고리 생성
	public void createCategory(List<Category> categorys, Member member) {
		for (Category category : categorys) {
			category.setMember(member);
			categoryRepository.save(category);
		}
	}

	// 스페이스 삭제
	public void deletespace(Long id) {
		spaceRepository.deleteById(id);
	}

	// 스페이스 수정
	public Space spaceDetail(Long id) {
		Optional<Space> optional = spaceRepository.findById(id);
		if (optional.isPresent()) {
			Space content = optional.get();
			return content;
		} else {
			throw new NullPointerException();
		}
	}
	
}
