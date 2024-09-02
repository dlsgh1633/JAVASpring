package com.example.demo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.example.demo.model.JsonDto;

public class CustomFailureHandler implements AuthenticationFailureHandler{
	

	    private RequestCache requestCache = new HttpSessionRequestCache();
	    
	    
	    @Override
	    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
	    
	    	
	        // 로그인 시, JSON 으로 반환하기 위한 코드 
	        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
	        MediaType jsonMimeType = MediaType.APPLICATION_JSON;

	        String message = "로그인에 실패하였습니다.";
	        JsonDto jsonDto = JsonDto.fail(message);
	        if (jsonConverter.canWrite(jsonDto.getClass(), jsonMimeType)) {
	            jsonConverter.write(jsonDto, jsonMimeType, new ServletServerHttpResponse(response));
	        }
	        
	    }

	    public void setRequestCache(RequestCache requestCache) {
	        this.requestCache = requestCache;
	    }
}
