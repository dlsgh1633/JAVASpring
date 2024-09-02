package com.example.demo.config;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.MimeTypeUtils;

import com.example.demo.model.MemberDto;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		System.out.println("커스터머 네임패스워드필터 진입");
System.out.println(request.getAttribute("email"));
System.out.println(request.getAttribute("password"));
		UsernamePasswordAuthenticationToken authenticationToken = null;

		String userId = null;
		String userPassword = null;
		String contentType = request.getContentType();
		// JSON 요청일 경우 -> AJAX로 로그인 하기 위한 설정
		if (contentType != null && contentType.equals(MimeTypeUtils.APPLICATION_JSON_VALUE)) {
			try {
				// ObjectMapper를 이용해서 JSON 데이터를 dto에 저장 후 dto의 데이터를 이용

				MemberDto loginDto = objectMapper.readValue(request.getReader().lines().collect(Collectors.joining()),
						MemberDto.class);

				userId = loginDto.getEMAIL();
				userPassword = loginDto.getPASSWORD();

			} catch (IOException e) {
				e.printStackTrace();
			}

			// POST 요청일 경우 기존과 같은 방식 이용 (기본방식)
		} else if (request.getMethod().equals("POST")) {
			userId = obtainUsername(request);
			userPassword = obtainPassword(request);

		} else {

			throw new AuthenticationServiceException("Authentication Method Not Supported : " + request.getMethod());
		}

		if (userId.equals("") || userPassword.equals("")) {
			System.out.println("ID 혹은 PW를 입력하지 않았습니다.");
			throw new AuthenticationServiceException("ID 혹은 PW를 입력하지 않았습니다.");
		}
		// 여기서 사용자가 입력한 email과 userPassword를 토큰값에 넣는다. 그렇게 토큰을 생성해서
		// getAuthenticationManager로 리턴. 이때의 userPassword 값은 !qkrwogus0358. provider에서
		// passwordEncode를 한다. provider -> DaoAuthenticationProvider의 authenticate메서드에서 비교하는 비밀번호 로직.
		authenticationToken = new UsernamePasswordAuthenticationToken(userId, userPassword); // 토큰을 생성하여
		System.out.println("UserId는 ?????" + userId);
		System.out.println("UserId는 ?????" + userPassword);
		this.setDetails(request, authenticationToken);
		System.out.println("CustomUsernamePasswordAuthenticationFilter - attemptAuthentication END");
		return this.getAuthenticationManager().authenticate(authenticationToken); // getAuthenticationManager 전달 ->

	}

}
