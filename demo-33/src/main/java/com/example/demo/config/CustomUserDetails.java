package com.example.demo.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.MemberDto;

public class CustomUserDetails implements UserDetails {
	
	private final MemberDto memberDto;
	
	@Autowired
	public CustomUserDetails (MemberDto memberDto) {
		this.memberDto = memberDto;
	}
	
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // 빈 권한 리스트...
    }

    @Override
    public String getPassword() {
        return memberDto.getPASSWORD();
    }

    @Override
    public String getUsername() {
        return memberDto.getNAME(); // 닉네임 반환이다
    }
    
    
    public int getUserid() {
    	return memberDto.getMEMBERID();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public MemberDto getMemberDto() {
        return memberDto;
    }
	
	
}
