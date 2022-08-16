package com.example.api.zconfig;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.api.utils.TokenProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {
	private final String TOKEN_TYPE = "Bearer";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// String uri = request.getRequestURI();
		Enumeration<String> headers = request.getHeaders(HttpHeaders.AUTHORIZATION);
		
		while(headers.hasMoreElements()) {
			String token = headers.nextElement();
			
			if(token.startsWith(TOKEN_TYPE)) {
				if(!TokenProvider.validateToken(token)) {
					Cookie cookie = new Cookie("token", null);

					cookie.setMaxAge(0);
					response.addCookie(cookie);
					
					throw new IOException("로그인이 만료되었습니다.");
				} else {
					String loginId = TokenProvider.parseJwtToken(token).get("loginId").toString();
					UserAuthentication authentication = new UserAuthentication(loginId, null, null);

					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
					
					log.info("Login user info : {}", SecurityContextHolder.getContext().getAuthentication());
				}
			}
		}
		
		filterChain.doFilter(request, response);
	}
}
