package com.space.member.dto;

import com.space.member.constant.Area;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OauthAddInfoDto {
	
    private Area area;
    
    private String pnum;   
    
    private String bnumber;
    
    private String spaceId;
    
}
