package com.space.member.controller;

import com.space.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members/api")
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/spaceIdCheck")
    public Long spaceIdCheck(@RequestBody final String spaceId){
        return memberService.spaceIdCheck(spaceId);
    }
}
