package com.space.member.controller;

import com.space.member.constant.Sgg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.space.config.auth.PrincipalDetails;
import com.space.member.constant.Area;
import com.space.member.dto.OauthAddInfoDto;
import com.space.member.entity.Member;
import com.space.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/members")
@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// 로그인 로직
	@GetMapping(value = "/login")
	public String loginMember() {
		return "member/memberLoginForm";
	}

	@GetMapping(value = "/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
		return "member/memberLoginForm";
	}

	// 마이페이지
	@GetMapping("/mypage")
	public String mypage(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
		Member member = memberService.mypageInfo(principalDetails);

		model.addAttribute("member", member);
		return "mypage/mypageMain";
	}

	// 소셜로그인 추가정보
	@GetMapping("/login/addInfo")
	public String addInfo(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		model.addAttribute("oauthAddInfoDto", new OauthAddInfoDto());
		model.addAttribute("local", Area.values());
		model.addAttribute("sgg", Sgg.values());
		return "member/memberAddInfo";
	}

	// 소셜로그인 추가정보 등록
	@PostMapping("/login/addInfo")
	public String addInfo(@AuthenticationPrincipal PrincipalDetails principalDetails, OauthAddInfoDto oauthAddInfoDto,Model model) {
		memberService.addInfo(principalDetails, oauthAddInfoDto);
		String spaceUrl = oauthAddInfoDto.getSpaceid();
		return "redirect:/space/"+spaceUrl+"";
	}

	// form로그인 테스트
	@GetMapping("/test/login")
	@ResponseBody
	public String testLogin(Authentication authentication,
			@AuthenticationPrincipal PrincipalDetails principalDetails2) {
		//log.info("/test/login ===============");
		// PrincipalDetail
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		log.info("authentication : " + principalDetails.getMember());
		log.info("userDetails : " + principalDetails2.getMember());
		return "세션 정보 확인하기";
	}

	// 소셜로그인 테스트
	@GetMapping("/test/oauth/login")
	@ResponseBody
	public String testLogin(Authentication authentication, @AuthenticationPrincipal OAuth2User oauth) {
		//log.info("/test/oauth/login ===============");
		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
		log.info("authentication : " + oAuth2User.getAttributes());
		log.info("OAuth2User : " + oauth.getAttributes());

		return "OAuth 세션 정보 확인하기";
	}

}
