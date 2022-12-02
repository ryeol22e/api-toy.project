package com.example.api.common.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.common.service.TestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {
	private final TestService testService;
	@GetMapping("/future1")
	public ResponseEntity<String> futrue1() throws Exception {
		return ResponseEntity.ok(testService.completeFutrueMethod());
	}

	@GetMapping("/future2")
	public ResponseEntity<String> futrue2() throws Exception {
		return ResponseEntity.ok(testService.completeFutrueMethod2());
	}

	@GetMapping("/clicks")
	public ResponseEntity<Boolean> clicks() throws Exception {
		return ResponseEntity.ok(true);
	}
}
