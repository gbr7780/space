package com.space.mypage.controller;

import java.io.File;

import java.util.ArrayList;

import java.io.IOException;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.space.member.constant.Sgg;
import com.space.space.dto.SpaceWriteDto;
import com.space.space.entity.Space;
import com.space.space.repository.SpaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.space.config.auth.PrincipalDetails;
import com.space.hitCount.entity.HitCount;
import com.space.hitCount.service.HitCountService;
import com.space.member.constant.Area;
import com.space.member.entity.Member;
import com.space.member.repository.MemberRepository;
import com.space.member.service.MemberService;
import com.space.mypage.constant.PrivateYn;
import com.space.member.dto.MemberUpdateDto;
import com.space.mypage.category.entity.Category;
import com.space.mypage.category.repository.CategoryRepository;
import com.space.mypage.space.service.SpaceService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/mypage")
@Controller
@Slf4j
@RequiredArgsConstructor
public class MypageController {

	@Autowired
	private SpaceService spaceService;

	@Autowired
	private SpaceRepository spaceRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private HitCountService countService;

	@Autowired
	private MemberService memberService;

	@Value("${contentImgLocation}")
	private String contentImgLocation;

	@Value("${profileImgLocation}")
	private String profileImgLocation;

	// 마이페이지 - 메인 (방문자 통계)
	@GetMapping("/main")
	public String mypageMain(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
		// 방문자 통계
		String spaceId = principalDetails.getMember().getSpaceId();
		List<HitCount> lists = countService.countList();
		model.addAttribute("lists", lists);
		model.addAttribute("spaceId", spaceId);

		String memSpaceId = principalDetails.getMember().getSpaceId();
		model.addAttribute("memSpaceId", memSpaceId);

		//String name = principalDetails.getMember().getName();
		//model.addAttribute("name", name);

		Long id = principalDetails.getMember().getId();
		Member member = memberRepository.findMemberById(id);


		model.addAttribute("member", member);

		// 관심분야 및 지역
		model.addAttribute("local", Area.values());
		//model.addAttribute("sgg", Sgg.values());

		int counts = 0;
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i).getSpaceId().equals(memSpaceId)) {
				// 방문자 목록 중 내 스페이스 아이디 검색
				counts += 1;
			}
		}
		model.addAttribute("total", counts);
		return "myPage/mypageMain";
	}

	// 마이페이지 - 스페이스 삭제
	@GetMapping(value = "/spaceEdit/delete/{id}")
	public String spaceDelete(@PathVariable("id") Long id) {
		log.info(">>>>>>>>>>  스페이스 삭제 컨트롤러 접근");
		spaceService.deletespace(id);
		return "redirect:mypage/space";
	}

	// 마이페이지 - 설정
	@GetMapping("/setting")
	public String mypageSetting(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {

		Long id = principalDetails.getMember().getId();
		Member member = memberRepository.findMemberById(id);
		model.addAttribute("member", member);
		model.addAttribute("local", Area.values());
		model.addAttribute("memberUpdateDto", new MemberUpdateDto());

		return "myPage/mypageSetting";
	}

	// 마이페이지 - 작성하기
	@GetMapping("/write")
	public String mypageWrite(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
		// String spaceId = principalDetails.getMember().getSpaceId();
		// model.addAttribute("spaceId", spaceId);
		Long id = principalDetails.getMember().getId();
		Member member = memberRepository.findMemberById(id);
		model.addAttribute("member", member);

		model.addAttribute("categoryWriteDto", new SpaceWriteDto());
		model.addAttribute("private", PrivateYn.values());

		// 카테고리 출력
		List<Category> lists = categoryRepository.findAll();
		List<String> categorys = new ArrayList<>(); // 로그인한 사용자의 카테고리 이름을 저장할 리스트 선언
		List<Long> categorysIds = new ArrayList<>(); // 로그인한 사용자의 카테고리 ID 저장 리스트
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i).getMember().getId().equals(principalDetails.getMember().getId())) {
				String categoryName = lists.get(i).getType();
				Long categorysId = lists.get(i).getId();
				categorys.add(categoryName); // 리스트에 카테고리 이름 저장
				categorysIds.add(categorysId); // 리스트에 카테고리 이름 저장
			}
		}
		model.addAttribute("categorys1", categorys.get(0));
		model.addAttribute("categorys2", categorys.get(1));
		model.addAttribute("categorys3", categorys.get(2));
		model.addAttribute("categorys4", categorys.get(3));
		model.addAttribute("categorys5", categorys.get(4));

		model.addAttribute("categorysID1", categorysIds.get(0));
		model.addAttribute("categorysID2", categorysIds.get(1));
		model.addAttribute("categorysID3", categorysIds.get(2));
		model.addAttribute("categorysID4", categorysIds.get(3));
		model.addAttribute("categorysID5", categorysIds.get(4));

		return "myPage/mypageWrite";
	}

	// 마이페이지 - 스페이스 수정하기
	@GetMapping(value = "/spaceEdit/update/{spaceId}")
	public String mypageSpaceUpdate(@PathVariable("spaceId") Long id, Model model,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		Long memid = principalDetails.getMember().getId();
		Member member = memberRepository.findMemberById(memid);
		model.addAttribute("member", member);

		model.addAttribute("categoryWriteDto", new SpaceWriteDto());
		model.addAttribute("private", PrivateYn.values());

		// 카테고리 출력
		List<Category> lists = categoryRepository.findAll();
		List<String> categorys = new ArrayList<>(); // 로그인한 사용자의 카테고리 이름을 저장할 리스트 선언
		List<Long> categorysIds = new ArrayList<>(); // 로그인한 사용자의 카테고리 ID 저장 리스트
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i).getMember().getId().equals(principalDetails.getMember().getId())) {
				String categoryName = lists.get(i).getType();
				Long categorysId = lists.get(i).getId();
				categorys.add(categoryName); // 리스트에 카테고리 이름 저장
				categorysIds.add(categorysId); // 리스트에 카테고리 이름 저장
			}
		}
		model.addAttribute("categorys1", categorys.get(0));
		model.addAttribute("categorys2", categorys.get(1));
		model.addAttribute("categorys3", categorys.get(2));
		model.addAttribute("categorys4", categorys.get(3));
		model.addAttribute("categorys5", categorys.get(4));

		model.addAttribute("categorysID1", categorysIds.get(0));
		model.addAttribute("categorysID2", categorysIds.get(1));
		model.addAttribute("categorysID3", categorysIds.get(2));
		model.addAttribute("categorysID4", categorysIds.get(3));
		model.addAttribute("categorysID5", categorysIds.get(4));

		Space content = spaceService.spaceDetail(id);

		model.addAttribute("content", content);

		return "myPage/mypagespaceUpdate";
	}

	// 마이페이지 - 작성하기
	@PostMapping("/write")
	public String mypageWritePost(@AuthenticationPrincipal PrincipalDetails principalDetails,
			SpaceWriteDto categoryWriteDto, Model model,
			@RequestParam(value = "radio", required = false) String radio) {
		categoryWriteDto.setOpenYn(radio);
		String category_id = categoryWriteDto.getCategory();
//		log.info(">>>>>>>>>>>>>>> id" + category_id);
		Space content = Space.createContent(categoryWriteDto);
		spaceService.saveSpace(principalDetails, content, category_id);

		return "redirect:/mypage/space";
	}

	/**
	 * 24.05.07 KJH
	 * 취업 관리
	 * @param principalDetails
	 * @param model
	 * @return mypageJob.html
	 */
	@GetMapping("/job")
	public String mypageJob(@AuthenticationPrincipal PrincipalDetails principalDetails,Model model){
		Long id = principalDetails.getMember().getId();
		Member member = memberRepository.findMemberById(id);
		model.addAttribute("member", member);

		// 관심 분야
		model.addAttribute("local", Area.values());
		return "/myPage/mypageJob";
	}

	// ckeditor 이미지 처리
	@PostMapping(value = "/image/upload")
	public ModelAndView image(MultipartHttpServletRequest request) throws Exception {

		// ckeditor는 이미지 업로드 후 이미지 표시하기 위해 uploaded 와 url을 json 형식으로 받아야 함
		// modelandview를 사용하여 json 형식으로 보내기위해 모델앤뷰 생성자 매개변수로 jsonView 라고 써줌
		// jsonView 라고 쓴다고 무조건 json 형식으로 가는건 아니고 @Configuration 어노테이션을 단
		// WebConfig 파일에 MappingJackson2JsonView 객체를 리턴하는 jsonView 매서드를 만들어서 bean으로 등록해야
		// 함
		ModelAndView mav = new ModelAndView("jsonView");

		// ckeditor 에서 파일을 보낼 때 upload : [파일] 형식으로 해서 넘어오기 때문에 upload라는 키의 밸류를 받아서
		// uploadFile에 저장함
		MultipartFile uploadFile = request.getFile("upload");

		// 파일의 오리지널 네임
		String originalFileName = uploadFile.getOriginalFilename();

		// 파일의 확장자
		String ext = originalFileName.substring(originalFileName.indexOf("."));

		// 서버에 저장될 때 중복된 파일 이름인 경우를 방지하기 위해 UUID에 확장자를 붙여 새로운 파일 이름을 생성
		String newFileName = UUID.randomUUID() + ext;

		// 현재경로/upload/파일명이 저장 경로
		String savePath = contentImgLocation + newFileName;

		// 브라우저에서 이미지 불러올 때 절대 경로로 불러오면 보안의 위험 있어 상대경로를 쓰거나 이미지 불러오는 jsp 또는 클래스 파일을 만들어
		// 가져오는 식으로 우회해야 함
		// 때문에 savePath와 별개로 상대 경로인 uploadPath 만들어줌
		String uploadPath = "/mypage/image/upload/" + newFileName;

		// 저장 경로로 파일 객체 생성
		File file = new File(savePath);

		// 파일 업로드
		uploadFile.transferTo(file);

		// uploaded, url 값을 modelandview를 통해 보냄
		mav.addObject("uploaded", true); // 업로드 완료
		mav.addObject("url", uploadPath); // 업로드 파일의 경로

		return mav;
	}

}
