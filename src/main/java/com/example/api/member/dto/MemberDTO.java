package com.example.api.member.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@JsonIgnoreProperties(value = {"changeBcryptPassword", "password", "updateLoginDtm"}, allowSetters = true)
public class MemberDTO {
	@Id
	private String loginId;
	private String password;
	private String userName;
	private String userAddr;
	@CreationTimestamp
	private LocalDateTime joinDtm = LocalDateTime.now();
	@UpdateTimestamp
	private LocalDateTime loginDtm;

	public void changeBcryptPassword() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		this.password = encoder.encode(getPassword());
	}

	public void updateLoginDtm() {
		this.loginDtm = LocalDateTime.now();
	}
	
}
