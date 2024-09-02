package com.example.demo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.example.demo.model.JsonDto;

public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler  {
	@Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //super.onLogoutSuccess(request, response, authentication);
		
		
		//로그아웃시 JSON형태로 반환하기 위한 코드 
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        MediaType jsonMimeType = MediaType.APPLICATION_JSON;

        String message = "로그아웃 완료";
        JsonDto jsonDto = JsonDto.success(null, message);
        if (jsonConverter.canWrite(jsonDto.getClass(), jsonMimeType)) {
            jsonConverter.write(jsonDto, jsonMimeType, new ServletServerHttpResponse(response));
        }
       
    }
}
