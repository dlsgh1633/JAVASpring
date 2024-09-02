package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;


import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.example.demo.model.MemberDto;
import com.example.demo.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private final MemberService memberService;
	
	
	
	@Autowired
	public MemberController (MemberService memberService) {
			this.memberService = memberService;
			
	}
	
	@GetMapping("/membership")
	public String getMember () {
		
		
		return "member/membership";
	}
	@PostMapping("/membership")
	public String postMember (@Valid MemberDto memberDto, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			List<ObjectError> errorList = bindingResult.getAllErrors();
			for(ObjectError error : errorList) {
				System.out.println(error.getDefaultMessage());
			}
			return "member/membership";
		}
		
		
		memberService.registerMember(memberDto);
		return "redirect:/member/login";
	}
	
	
	@PostMapping("/checkName")
	@ResponseBody
	public String checkName(@RequestParam String name) {
		
			
		return memberService.checkName(name) ? "possible" : "impossible" ;
	}
	
	@PostMapping("/checkMail")
	@ResponseBody
	public String checkMail(@RequestParam String email) {
		
		return memberService.checkMail(email) ? "possible" : "impossible";
		 
	}
	
}
