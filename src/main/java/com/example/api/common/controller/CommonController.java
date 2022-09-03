package com.example.api.common.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.common.dto.HeaderDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common")
public class CommonController {
	
	@GetMapping("/auth/check")
	public void loginCheck() throws Exception {}

	@GetMapping ("/headers")
	ResponseEntity<List<HeaderDTO>> getHeaderList() throws Exception {
		List<HeaderDTO> headers = new ArrayList<>();
		HeaderDTO dto1 = new HeaderDTO();
		HeaderDTO dto2 = new HeaderDTO();
		HeaderDTO dto3 = new HeaderDTO();

		dto1.setId("1");
		dto1.setName("Q&A");
		dto1.setPath("/boards/qna");
		headers.add(dto1);

		dto2.setId("2");
		dto2.setName("COMMUNITY");
		dto2.setPath("/boards/community");
		headers.add(dto2);

		dto3.setId("3");
		dto3.setName("TESTPAGE");
		dto3.setPath("/setup/test");
		headers.add(dto3);

		return ResponseEntity.ok(headers);
	}
}
