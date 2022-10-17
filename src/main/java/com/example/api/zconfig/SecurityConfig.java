package com.example.api.zconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.api.member.dto.MemberRoleEnum;
import com.example.api.member.service.MemberService;
import com.example.api.zconfig.auth.AuthEntryPoint;
import com.example.api.zconfig.filter.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final MemberService memberService;
	private final AuthEntryPoint authEntryPoint;

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * filter chain 시큐리티에 관한 메커니즘을 builder패턴으로 표현한다.
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.cors()
			.configurationSource(corsConfigurationSource())
			.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.exceptionHandling()
				.authenticationEntryPoint(authEntryPoint)
			.and()
				.authorizeRequests()
				.antMatchers("/api/common/auth/**").authenticated()
				.antMatchers("/api/display/corner/**").authenticated()
				.antMatchers("/api/boards/regist/**").hasRole(MemberRoleEnum.ADMIN.getValue())
				.antMatchers("/api/boards/qna/?").authenticated()
				.antMatchers("/api/common/headers").permitAll()
				.antMatchers("/api/search/**").permitAll()
				.antMatchers("/api/member/**").permitAll()
				.antMatchers("/api/**/list").permitAll()
			.and()
				.addFilterBefore(new JwtFilter(memberService), UsernamePasswordAuthenticationFilter.class)
				.formLogin().disable().httpBasic().disable().logout().disable().csrf().disable();
			
		return http.build();
	}

	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return web-> web.ignoring()
			.antMatchers("/h2-console/**");
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:9999");
		config.addAllowedOrigin("http://127.0.0.1:5555");
		config.addAllowedHeader("*");
		config.addAllowedMethod(HttpMethod.GET);
		config.addAllowedMethod(HttpMethod.POST);
		config.setMaxAge(3600L);

		source.registerCorsConfiguration("/api/**", config);

		return source;
	}
}
