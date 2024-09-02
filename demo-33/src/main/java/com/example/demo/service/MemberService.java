package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.MemberDto;

public interface MemberService {
	
	public void registerMember(MemberDto memberDto);
	public boolean checkName(String name);
	public boolean checkMail(String name);
	public UserDetails loadUserByUsername(String email);
	
}
