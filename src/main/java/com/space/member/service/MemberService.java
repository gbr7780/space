package com.space.member.service;

import com.space.member.dto.MemberExternalDto;
import com.space.member.dto.MemberUpdateDto;
import com.space.mypage.category.dto.CategoryDto;
import com.space.sgg.dto.SggResponseDto;
import com.space.sgg.entity.Sgg;
import com.space.sgg.repository.SggRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.space.config.auth.PrincipalDetails;
import com.space.member.dto.OauthAddInfoDto;
import com.space.member.entity.Member;
import com.space.member.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
//로직을 처리하다가 에러가 발생하면 변경된 데이터를 조직 이전으로 콜백 시켜주기 위해
@Transactional
//final이나 NonNull 붙은 필드에 생성자를 생성해줌
@RequiredArgsConstructor
@Slf4j
//UserDetailsService는 데이터베이스에서 회원정보를 가져오는 역할 (즉, 시큐리티에서 로그인 담당한다고 생각하면 됨)
public class MemberService implements UserDetailsService {
	// 빈에 생성자가 1개이고 생성자의 파라미터 타입이 빈으로 등록이 가능하면 @Autowired 없이 의존성 주입 가능
	@Autowired
	private MemberRepository memberRepository;

	private final SggRepository sggRepository;

	public Member saveMember(Member member) {
		validateDuplicateMember(member);
		return memberRepository.save(member);
	}

	/* 조회수 증가 */
//    @Transactional
//    public int updateView(Long id) {
//        return memberRepository.updateView(id);
//    }

	// 회원 중복체크
	private void validateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByEmail(member.getEmail());
		if (findMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	}

	// OAuth2 추가정보 등록
	public Member addInfo(@AuthenticationPrincipal PrincipalDetails principalDetails, OauthAddInfoDto addInfoDto) {
		String email = principalDetails.getMember().getEmail();
		Member member = memberRepository.findByEmail(email);
		member.addInfoOAuth2(addInfoDto);
		return member;
	}

	// mypage 정보 출력
	public Member mypageInfo(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		String email = principalDetails.getMember().getEmail();
		Member member = memberRepository.findByEmail(email);
		return member;
	}

	// 로그인한 회원 ID 출력
	public Long getIdFromAuth(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		Long id = principalDetails.getMember().getId();
		return id;
	}

	// 프로필 수정(이미지 변경할 경우)
	public Member updateImage(Member member, String fileName, MemberUpdateDto memberUpdateDto) {
//		member.setImage(fileName);
//		member.setBnumber(memberUpdateDto.getBnumber());
//		member.setBname(memberUpdateDto.getBname());
//		member.setAddress(memberUpdateDto.getAddress());
//		member.setPnum(memberUpdateDto.getPnum());
		
		// marketRepository.save(market);
		
		member.setImage(fileName);
		member.setName(memberUpdateDto.getName());
		member.setArea(memberUpdateDto.getArea());
		member.setAllPublicYn(memberUpdateDto.getAllPublicYn());
		
		return memberRepository.save(member);
	}


	// UserDetailsService 인터페이스의 오버라이딩한다. 로그인할 유저의 email을 파라미터로 전달함( 이름은 동명이인이 있을수
	// 있기 때문에)
	// 시큐리티 session(내부 Authentication(내부 UserDetails))
	// 메서드 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(email);
		if (member == null) {
			System.out.println("DB에 유저가 없어용 ㅠ");
			throw new UsernameNotFoundException(email);
		} else {
			return new PrincipalDetails(member);
		}
	}

	/**
	 * 회원가입 추가정보 페이지에서 스페이스아이디 중복체크
	 * @param spaceId
	 * @return 카운트
	 */
	public Long spaceIdCheck(String spaceId) {
		return memberRepository.countBySpaceId(spaceId);
	}

	/**
	 * sido 구하는 메소드
	 * @return
	 */
	public List<SggResponseDto> getSidoList() {
		List<Sgg> lists = sggRepository.getSidoList();
		return lists.stream().map(SggResponseDto::new).collect(Collectors.toList());
	}

	/**
	 * sido 구하는 메소드
	 * @return
	 */
	public List<SggResponseDto> getSgg(String sido) {
		List<Sgg> lists = sggRepository.getSggList(sido);
		return lists.stream().map(SggResponseDto::new).collect(Collectors.toList());
	}

	/**
	 * 회원 sgg 코드를 이용해여 sido코드 값 리턴하는 함수
	 * @param memberSgg
	 * @return sido에 대한 sgg코드
	 */
	public SggResponseDto getMemberSido(String memberSgg) {
		return sggRepository.findBySggCd(memberSgg);
	}



	public void updateProfile(Long id, MemberUpdateDto params) {
		Member member = memberRepository.findMemberById(id);
		member.setSpaceName(params.getSpaceName());
		member.setArea(params.getArea());
		member.setAllPublicYn(params.getAllPublicYn());
		member.setSggCd(params.getSggCd());
	}

	// 외부 스페이스 목록 조회
    public List<MemberExternalDto> externalSpaceList(String openYn) {
		List<Member> lists = memberRepository.findMemberByAllPublicYn(openYn);
		return lists.stream().map(MemberExternalDto::new).collect(Collectors.toList());
    }

	// 외부 스페이스 목록 조회
    public List<MemberExternalDto> externalSpaceList() {
		List<MemberExternalDto> lists = memberRepository.findExternalSpaceList();
		log.info(">>>> lists" + lists);
		return lists;
    }
}
