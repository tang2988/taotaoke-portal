package com.taotaoke.portal.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotaoke.portal.service.ContentService;

@Controller
public class IndexController {
	
	@Resource
	ContentService contentservice;	
	
	@RequestMapping("/index")
	public String IndexMethod(Model model){
		 String contentlist = contentservice.getContentList();
		 model.addAttribute("ad1", contentlist);
		return "index";
	}

}
