package com.space.mypage.space.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import com.space.exception.CustomException;
import com.space.exception.ErrorCode;
import com.space.mypage.space.dto.SpaceAllUpdateDto;
import com.space.mypage.space.dto.SpaceListDto;
import com.space.space.dto.SpaceResponseDto;
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

	// 스페이스 리스트 조회
//	public List<SpaceListDto> list(Long memberId) {
//		List<Space> lists = spaceRepository.findByMemberId(memberId);
//		return lists.stream().map(SpaceListDto::new).collect(Collectors.toList());
//	}

	public List<SpaceListDto> list(Long memberId) {
		List<SpaceListDto> lists = spaceRepository.findSpaceList(memberId);
		return lists;
//		return lists.stream().map(SpaceListDto::new).collect(Collectors.toList());
	}

	// 마이페이지 작성하기 
	public Space saveSpace(@AuthenticationPrincipal PrincipalDetails principalDetails, Space space, String Category_id) {
		Long member_id = principalDetails.getMember().getId();
		Member member = memberRepository.findMemberById(member_id);
		
		Category category = categoryRepository.findCategoryById(Long.parseLong(Category_id));
		
		space.setMember(member);
		space.setCategory(category);
		
		return spaceRepository.save(space);
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

	public List<SpaceResponseDto> findSpcaeAll(Long memberId) {
		return spaceRepository.findSpcaeAll(memberId);
	}

	// 스페이스 삭제
	@Transactional
	public void delete(final Long spaceId) {
		spaceRepository.deleteById(spaceId);
	}

	// 스페이스 일괄 수정
	@Transactional
	public void update(SpaceAllUpdateDto dto) {
		Space space = new Space(dto);
		// 카테고리 id
		Category category = categoryRepository.findCategoryById(dto.getCategoryId());
		space.setCategory(category);
		Space temp = spaceRepository.findSpaceById(dto.getSpaceId());

		space.setMember(temp.getMember());
		space.setTitle(temp.getTitle());
		space.setContent(temp.getContent());
		spaceRepository.save(space);
	}
}
