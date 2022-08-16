package com.example.api.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.board.dto.CommBoardDTO;

@Repository
public interface CommBoardDAO extends JpaRepository<CommBoardDTO, Long> {
	CommBoardDTO findByBoardSeq(Long boardSeq);
}
