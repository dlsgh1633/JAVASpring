package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.MemberDto;
import com.example.demo.service.MemberService;

@Controller
public class LoginController {
	
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public LoginController (MemberService memberService , PasswordEncoder passwordEncoder) {
		
		this.memberService = memberService;
		this.passwordEncoder =passwordEncoder;
	}
	
	
	
	 @GetMapping("/member/login")
	    public String getLogin() {
		 
	        return "member/login";
	    }
	 
	 
	
	 @GetMapping("/member/userstatus")
	 @ResponseBody
	   public String getUserStatus (@AuthenticationPrincipal UserDetails userDetails) {
		 
		 if(userDetails != null) {
			 return userDetails.getUsername();
		 }
		 else {
			 return "null";
		 }
	 }
	 
	 
	}
	

