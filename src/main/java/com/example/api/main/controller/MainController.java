package com.example.api.main.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.main.service.MainService;

import lombok.RequiredArgsConstructor;

/**
 * MainController
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {
	private final MainService mainService;

	@GetMapping("")
	public ResponseEntity<Map<String, Object>> main() {
		
		return ResponseEntity.ok(mainService.mainData());
	}

	
	
}