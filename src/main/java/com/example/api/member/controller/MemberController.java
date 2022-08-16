package com.example.api.member.controller;

import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<String> login(@RequestBody MemberDTO param) throws Exception {
		String token = memberService.memberLogin(param);
		
		return ResponseEntity.ok(token);
	}

	@PostMapping("/join")
	public ResponseEntity<Boolean> join(@RequestBody MemberDTO param) throws Exception {
		return ResponseEntity.ok(memberService.memberJoin(param));
	}
	
}
