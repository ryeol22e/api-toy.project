package com.example.api.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.board.dto.QnABoardDTO;

@Repository
public interface QnABoardDAO extends JpaRepository<QnABoardDTO, Long>{
	QnABoardDTO findByBoardSeq(Long boardSeq);
}
