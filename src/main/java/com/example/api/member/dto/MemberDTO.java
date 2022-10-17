package com.example.api.member.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Entity
@Table(name = "MEMBER")
@NoArgsConstructor
@JsonIgnoreProperties(value = {"changeBcryptPassword", "password", "updateLoginDtm", "role", "refreshToken"}, allowSetters = true)
public class MemberDTO {
	@Id
	@Email(message = "이메일형식이 아닙니다.")
	private String loginId;
	private String password;
	private String userName;
	private String userAddr;
	private String role;
	@CreationTimestamp
	private LocalDateTime joinDtm = LocalDateTime.now();
	@UpdateTimestamp
	private LocalDateTime loginDtm;
	@Transient
	private String token;
	private String refreshToken;

	@Transient
	public void setToken(String token) {
		this.token = token;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	@Transient
	public void setRole(String role) {
		this.role = role;
	}

	public void changeBcryptPassword() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		this.password = encoder.encode(getPassword());
	}

	public void updateLoginDtm() {
		this.loginDtm = LocalDateTime.now();
	}
	
}
