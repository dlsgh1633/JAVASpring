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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.example.demo.model.JsonDto;

public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	
		private RequestCache requestCache = new HttpSessionRequestCache();


	    @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
	        SavedRequest savedRequest = requestCache.getRequest(request, response);

	        if (savedRequest != null) {
	            requestCache.removeRequest(request, response);
	            clearAuthenticationAttributes(request);
	        }

	        String accept = request.getHeader("accept");

	        UserDetails principalDetails = null;
	        if (SecurityContextHolder.getContext().getAuthentication() != null) {
	            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	            if (principal != null && principal instanceof UserDetails) {
	                principalDetails = (UserDetails) principal;
	            }
	        }

	        // 로그인 시, JSON 으로 반환하기 위한 코드
	        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
	        MediaType jsonMimeType = MediaType.APPLICATION_JSON;
	        
	        String message = "로그인 성공";
	        JsonDto jsonDto = JsonDto.success(principalDetails, message);
	        if (jsonConverter.canWrite(jsonDto.getClass(), jsonMimeType)) {
	            jsonConverter.write(jsonDto, jsonMimeType, new ServletServerHttpResponse(response));
	        }
	        
	        
	    }

	    public void setRequestCache(RequestCache requestCache) {
	        this.requestCache = requestCache;
	    }

}
