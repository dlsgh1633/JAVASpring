package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity      // Spring Security Filter 가 Spring Filter Chain 에 등록되도록
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
class SecurityConfig extends WebSecurityConfigurerAdapter  {
	

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Spring Security Setting
     * @param http
     * @Author Seong-Mok Kim
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception{
    
        // CORS 설정 및 CSRF 비활성화
        

        http
        	.cors().and()
        	.csrf().disable()
            // URL에 따른 접근 제한 처리
            .authorizeRequests()
                .antMatchers("/api/user/**").authenticated()        // URL user : 인증이 되어야 함
                .antMatchers("/api/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/member/login","/resources/**","/board/**").permitAll()// URL admin : 권한 'ROLE_ADMIN'이 있어야함
                .anyRequest().permitAll()   
            .and()
            	.logout()
            	.logoutUrl("/member/logout")
            	.logoutSuccessHandler(logoutSuccessHandler())
            	.and()
            	.addFilterBefore(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        ;
    }

    // JSON 방식으로도 로그인 가능하도록 설정한 Filter 이부분에서 post url 주소설정을 통해 필터가 로그인 가로챔
    protected CustomUsernamePasswordAuthenticationFilter getAuthenticationFilter() {
        CustomUsernamePasswordAuthenticationFilter authFilter = new CustomUsernamePasswordAuthenticationFilter();
        try {
            authFilter.setFilterProcessesUrl("/member/logincheck");		// login post 주소 설정  
            authFilter.setAuthenticationManager(this.authenticationManagerBean());
            authFilter.setUsernameParameter("email");			// username 설정
            authFilter.setPasswordParameter("password");	// password 설정
            authFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());		// SuccessHandler 설정
            authFilter.setAuthenticationFailureHandler(authenticationFailureHandler());	// FailuerHandler 설정
          
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authFilter;
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
    	
        return new CustomSuccessHandler();
    }

    @Bean
    public CustomFailureHandler authenticationFailureHandler(){
        return new CustomFailureHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new CustomLogoutSuccessHandler();
    }

  
}
