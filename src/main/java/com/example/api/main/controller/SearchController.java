package com.example.api.main.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.main.dto.SearchKeywordDTO;
import com.example.api.main.service.SearchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {
	private final SearchService searchService;

	@PostMapping("/{word}")
	public ResponseEntity<String> search(@PathVariable(required = true) String word, @RequestBody SearchKeywordDTO entity) throws Exception {
		return ResponseEntity.ok(searchService.search(entity));
	}
}
