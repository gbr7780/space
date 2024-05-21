package com.space.member.controller;

import com.space.config.auth.PrincipalDetails;
import com.space.member.dto.MemberUpdateDto;
import com.space.member.dto.OauthAddInfoDto;
import com.space.member.service.MemberService;
import com.space.sgg.dto.SggResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description    :
 * packageName    : com.space.member.controller
 * fileName        : MemberApiController
 * author         : kimjongha
 * date           : 2024/05/13
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * —————————————————————————————
 * 2024/05/13        kimjongha       최초 생성
 */



@RestController
@RequestMapping("/members/api")
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/spaceIdCheck")
    public Long spaceIdCheck(@RequestBody final String spaceId){
        return memberService.spaceIdCheck(spaceId);
    }

    @GetMapping("/getSido")
    public List<SggResponseDto> getSido(){
        return memberService.getSidoList();
    }

    @GetMapping("/getSgg")
    public List<SggResponseDto> getSgg(@RequestParam final String sido){
        return memberService.getSgg(sido);
    }

    @GetMapping("/getMemberSido")
    public SggResponseDto getMemberSido(@RequestParam final String memberSgg){
        return memberService.getMemberSido(memberSgg);
    }



    @PostMapping("/addLoginInfo")
    public void addInfo(@AuthenticationPrincipal PrincipalDetails principalDetails,@RequestBody OauthAddInfoDto params) {
        memberService.addInfo(principalDetails, params);
    }

    @PostMapping("/updateMemberInfo")
    public void updateMemberInfo(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody final MemberUpdateDto params){
        memberService.updateProfile(principalDetails.getMember().getId(),params);
    }



}

