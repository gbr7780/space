package com.space.member.dto;

import org.springframework.web.multipart.MultipartFile;

import com.space.member.constant.Area;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateDto {
	
	private Area area;
	
	private String name;
	
    private MultipartFile filename;
    
    private String allPublicYn;
}
