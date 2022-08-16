package com.example.api.main.service;

import org.springframework.stereotype.Service;

import com.example.api.main.dto.SearchKeywordDTO;
import com.example.api.main.repository.SearchKeywordDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchService {
	private final SearchKeywordDAO searchKeywordDAO;

	public String search(SearchKeywordDTO entity) throws Exception {
		searchKeywordDAO.save(entity);
		return new StringBuilder("https://www.google.com/search?q=").append(entity.getWord()).toString();
	}
}
