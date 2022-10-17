package com.example.api.common.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.common.dto.CommonDTO;
import com.example.api.common.service.CommonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common")
public class CommonController {
	private final CommonService commonService;
	
	@GetMapping("/headers")
	public ResponseEntity<List<CommonDTO>> getHeaders(CommonDTO parameter) throws Exception {
		// getmapping은 스프링부트에서 javabean 방식으로 데이터를 받음. 그래서 dto로 받을려면 dto에 setter가 있어야한다.
		// 추가 setter를 안해도 받는방법이 있다 내용은 globalControllerAdvice.java에 설정되어있음.
		log.info(">>>>>>>>>>>>>>>>>>>>>>>> data : {}", parameter);
		return ResponseEntity.ok(commonService.getHeaders(parameter));
	}

	@GetMapping("/future1")
	public ResponseEntity<String> futrue1() throws Exception {
		return ResponseEntity.ok(commonService.completeFutrueMethod());
	}

	@GetMapping("/future2")
	public ResponseEntity<String> futrue2() throws Exception {
		return ResponseEntity.ok(commonService.completeFutrueMethod2());
	}

	@GetMapping("/clicks")
	public ResponseEntity<Boolean> clicks() throws Exception {
		return ResponseEntity.ok(true);
	}
}
