package com.example.api.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.board.dto.BaseBoardDTO;

@Repository
public interface BoardDAO extends JpaRepository<BaseBoardDTO, Long> {
	
}
