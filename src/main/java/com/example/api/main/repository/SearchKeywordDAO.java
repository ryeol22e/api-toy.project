package com.example.api.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.main.dto.SearchKeywordDTO;

@Repository
public interface SearchKeywordDAO extends JpaRepository<SearchKeywordDTO, Long> {
	
}
