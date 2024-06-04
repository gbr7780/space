package com.space.main.controller;

import com.space.config.auth.PrincipalDetails;
import com.space.hitCount.entity.HitCount;
import com.space.hitCount.service.HitCountService;
import com.space.member.entity.Member;
import com.space.member.repository.MemberRepository;
import com.space.member.service.MemberService;
import com.space.mypage.category.entity.Category;
import com.space.mypage.category.repository.CategoryRepository;
import com.space.mypage.space.service.SpaceService;
import com.space.space.dto.SpaceResponseDto;
import com.space.space.entity.Space;
import com.space.space.repository.SpaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * description    :
 * packageName    : com.space.main.controller
 * fileName        : MainController
 * author         : kimminsol
 * date           : 2024/05/15
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/15        kimminsol       최초 생성
 */

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private SpaceService spaceService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SpaceRepository spaceRepository;

    private final HitCountService countService;

    @GetMapping(value = "/")
    public String main(Model model) {
        return "mainFG";
    }

    // 스페이스 메인페이지
    @GetMapping("/space/{spaceId}")
    public String spaceMain(@PathVariable("spaceId") String spaceId, Model model,
                            @AuthenticationPrincipal PrincipalDetails principalDetails, HitCount hitcount) {



        model.addAttribute("spaceId", spaceId);
        log.info(">>>>>>>>>>>> spaceId : " + spaceId);

        // 현재 로그인한 정보
        Long memid = principalDetails.getMember().getId();
        Member memMember = memberRepository.findMemberById(memid);
        model.addAttribute("memMember", memMember);

        List<SpaceResponseDto> spaceLists = spaceService.findSpcaeAll(memid);
        model.addAttribute("spaceLists", spaceLists);




        // 스페이스 계정에 대한 정보
        List<Member> list = memberRepository.findAll();
        Long id = (long) 0; // 스페이스의 공개여부
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSpaceId().equals(spaceId)) {
                id = list.get(i).getId();
            }
        }

        Member member = memberRepository.findMemberById(id);
        model.addAttribute("member", member);

        log.info(">>>>>>>>>>>> 현재로그인한 space ID : " + memMember.getSpaceId());
        log.info(">>>>>>>>>>>> 계정 space ID : " + spaceId);

        log.info(">>>>>>>>>>>> 공개여부 : " + member.getAllPublicYn());

        // 조회수 증가
        countService.hitCountSave(spaceId, "날짜", hitcount);
        log.info("조회수 증가");





        // 카테고리 출력
        List<Category> lists = categoryRepository.findAll();
        List<String> categorys = new ArrayList<>(); // 로그인한 사용자의 카테고리 이름을 저장할 리스트 선언
        List<Long> categorysIds = new ArrayList<>(); // 로그인한 사용자의 카테고리 ID 저장 리스트
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getMember().getId().equals(principalDetails.getMember().getId())) {
                String categoryName = lists.get(i).getType();
                categorys.add(categoryName); // 리스트에 카테고리 이름 저장

                Long categorysId = lists.get(i).getId();
                categorysIds.add(categorysId); // 리스트에 카테고리 이름 저장
            }
        }
        model.addAttribute("categorys1", categorys.get(0));
        model.addAttribute("categorys2", categorys.get(1));
        model.addAttribute("categorys3", categorys.get(2));
        model.addAttribute("categorys4", categorys.get(3));
        model.addAttribute("categorys5", categorys.get(4));

        Long categorysIds1 = categorysIds.get(0);
        Long categorysIds2 = categorysIds.get(1);
        Long categorysIds3 = categorysIds.get(2);
        Long categorysIds4 = categorysIds.get(3);
        Long categorysIds5 = categorysIds.get(4);

        // 로그인한 사용자 조회수
        Long hitCount = countService.getUserHit(memMember.getSpaceId());
        model.addAttribute("hitCount",hitCount);

        return "space/spaceMain";
    }
}
