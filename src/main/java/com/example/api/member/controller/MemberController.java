package com.example.api.member.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.member.dto.MemberDTO;
import com.example.api.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
	private final MemberService memberService;
	
	@PostMapping("/login")
	public ResponseEntity<MemberDTO> login(@RequestBody @Validated MemberDTO param, Errors errors) throws Exception {
		return ResponseEntity.ok(memberService.memberLogin(param));
	}

	@PostMapping("/join")
	public ResponseEntity<Boolean> join(@RequestBody @Valid MemberDTO param) throws Exception {
		return ResponseEntity.ok(memberService.memberJoin(param));
	}
	
}
