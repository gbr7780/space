package com.space.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

//    private final ItemService itemService;

	// 메인 컨트롤러(동영상)
	@GetMapping(value = "/")
	public String main(Model model) {
		//model.addAttribute("videoUrl", "/video/main.mp4");
		return "mainFG";
	}

}
