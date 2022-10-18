package com.example.api.zconfig.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.api.member.dto.MemberRoleEnum;
import com.example.api.member.service.MemberService;
import com.example.api.utils.TokenProvider;
import com.example.api.zconfig.auth.UserAuthentication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {
	private final String TOKEN_TYPE = "Bearer";
	private final MemberService memberService;

	public JwtFilter(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean checkAuth = request.getRequestURI().equalsIgnoreCase("/api/auth/check") ? true : false;
		boolean checkAuthExpire = false;
		String token = "";
		Enumeration<String> headers = request.getHeaders(HttpHeaders.AUTHORIZATION);
		
		while(headers.hasMoreElements()) {
			String element = headers.nextElement();
			
			if(element.startsWith(TOKEN_TYPE)) {
				token = element;

				if(!TokenProvider.validateToken(token)) {
					log.error("user access token is expired.");
					String memberId = request.getHeader("MemberId");
					token = memberService.getRefreshToken(memberId);
					
					if(!TokenProvider.validateToken(token)) {
						log.error("user refresh token is expired.");
						checkAuthExpire = true;
					}
				}
			}
		}

		String loginId = MemberRoleEnum.ANONYMOUS.name();
		String userRole = MemberRoleEnum.ANONYMOUS.getValue();

		if(checkAuth) {
			if("".equals(token) || checkAuthExpire) {
				throw new IOException("로그인이 만료되었습니다.");
			}
		}

		if(!"".equals(token) && !checkAuthExpire) {
			loginId = TokenProvider.parseJwtToken(token).get("loginId").toString();
			userRole = TokenProvider.parseJwtToken(token).get("userRole").toString();
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		authorities.add(new SimpleGrantedAuthority(userRole));
		UserAuthentication authentication = new UserAuthentication(loginId, null, authorities);

		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		log.info("Login user info : {}", SecurityContextHolder.getContext().getAuthentication());
		filterChain.doFilter(request, response);
	}
}
