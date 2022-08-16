package com.example.api.utils;


import java.time.Duration;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class TokenProvider {
	private static final String AUTH_KEY = "auth";

	public static String createToken(String id) {
		Date now = new Date();

		return Jwts.builder()
			.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + Duration.ofMinutes(30L).toMillis()))
			.claim("loginId", id)
			.signWith(SignatureAlgorithm.HS256, AUTH_KEY)
			.compact();
	}

	public static Claims parseJwtToken(String token) {		
		Claims claims = null;
		
		try {
			claims = Jwts.parser()
				.setSigningKey(AUTH_KEY)
				.parseClaimsJws(token.substring("Bearer ".length()))
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
            Jwts.parser().setSigningKey(AUTH_KEY).parseClaimsJws(token.substring("Bearer ".length()));
            flag = true;
        } catch (SignatureException ex) {
            ex.printStackTrace();
        } catch (MalformedJwtException ex) {
            ex.printStackTrace();
        } catch (ExpiredJwtException ex) {
            ex.printStackTrace();
        } catch (UnsupportedJwtException ex) {
            ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
		
        return flag;
    }

}
