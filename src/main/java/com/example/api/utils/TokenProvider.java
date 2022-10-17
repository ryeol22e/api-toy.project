package com.example.api.utils;


import java.time.Duration;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.api.member.dto.MemberDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TokenProvider {
	private static final String AUTH_KEY = "auth";

	public static String createToken(MemberDTO member) {
		Date now = new Date();

		return Jwts.builder()
			.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + Duration.ofMinutes(30L).toMillis()))
			.claim("loginId", member.getLoginId())
			.claim("userRole", member.getRole())
			.signWith(SignatureAlgorithm.HS256, AUTH_KEY)
			.compact();
	}

	public static String refreshToken(MemberDTO member) {
		Date now = new Date();

		return Jwts.builder()
			.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + Duration.ofDays(2L).toMillis()))
			.claim("loginId", member.getLoginId())
			.claim("userRole", member.getRole())
			.signWith(SignatureAlgorithm.HS256, AUTH_KEY)
			.compact();
	}

	public static Claims parseJwtToken(String token) {		
		Claims claims = null;
		
		try {
			claims = Jwts.parser()
				.setSigningKey(AUTH_KEY)
				.parseClaimsJws(removeBearer(token))
				.getBody();
		} catch (Exception e) {
			//TODO: handle exception
			claims = null;
			e.printStackTrace();
		}

		return claims;
	}

	public static boolean validateToken(String token) {
		boolean flag = false;
		
        try {
			
            Jwts.parser().setSigningKey(AUTH_KEY).parseClaimsJws(removeBearer(token));
            flag = true;
        } catch (SignatureException ex) {
			log.error(ex.getMessage());
        } catch (MalformedJwtException ex) {
			log.error(ex.getMessage());
        } catch (ExpiredJwtException ex) {
			log.error(ex.getMessage());
        } catch (UnsupportedJwtException ex) {
			log.error(ex.getMessage());
        } catch (IllegalArgumentException ex) {
			log.error(ex.getMessage());
        }
		
        return flag;
    }

	private static String removeBearer(String token) {
		if(token.startsWith("Bearer ")) {
			token = token.substring("Bearer ".length());
		}

		return token;
	}

}
