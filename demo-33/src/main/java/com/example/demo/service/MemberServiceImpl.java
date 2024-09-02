package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.CustomUserDetails;
import com.example.demo.mapper.MemberMapper;
import com.example.demo.model.MemberDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MemberServiceImpl implements MemberService , UserDetailsService{
	
	
	private final  MemberMapper memberMapper;
	private final  PasswordEncoder passwordEncoder;
	
	@Autowired
	public MemberServiceImpl (MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
		this.memberMapper =memberMapper;
		this.passwordEncoder =passwordEncoder;
	}
	
	
	@Override
	public void registerMember (MemberDto memberDto) {
		String encodingPassword = passwordEncoder.encode(memberDto.getPASSWORD());
		memberDto.setPASSWORD(encodingPassword);
		memberMapper.registerMember(memberDto);
	}
	
	
	@Override
	public boolean checkName(String name) {
		
		return memberMapper.checkName(name) == 0;
	}
	
	@Override
	public boolean checkMail(String email) {
		
		return memberMapper.checkMail(email) == 0;
		
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			Optional<MemberDto> member = this.memberMapper.findUserEmail(email);
			
			if(!member.isPresent()) {
				throw new UsernameNotFoundException(email);
			}
			MemberDto memberDto = member.get();
			List<GrantedAuthority> authorities = new ArrayList<>();
			System.out.println(member);	
			System.out.println(memberDto);
		return new CustomUserDetails(memberDto);
		
	}
	
}
