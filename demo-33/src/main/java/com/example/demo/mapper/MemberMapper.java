package com.example.demo.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.MemberDto;

@Mapper
public interface MemberMapper {
	public void registerMember(MemberDto memberDto);
	public int checkName(String name);
	public int checkMail(String email);
	public Optional<MemberDto> findUserEmail(String email);
	
	
}	
