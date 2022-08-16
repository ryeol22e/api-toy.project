package com.example.api.display.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.display.dto.DisplayCornerDTO;
import com.example.api.display.service.DisplayCornerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/display")
@RequiredArgsConstructor
public class DisplayCornerController {
	private final DisplayCornerService displayCornerService;

	@GetMapping("/corner")
	public ResponseEntity<List<DisplayCornerDTO>> displayCornerList(DisplayCornerDTO param) throws Exception {
		return ResponseEntity.ok(displayCornerService.getCornerList(param));
	}
}
