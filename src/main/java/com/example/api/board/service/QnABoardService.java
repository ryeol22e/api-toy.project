package com.example.api.board.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.api.board.dto.QnABoardDTO;
import com.example.api.board.repository.QnABoardDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnABoardService {
	private final QnABoardDAO qnABoardDAO;
	
	public List<QnABoardDTO> qnaList() throws Exception {
		return qnABoardDAO.findAll(Sort.by(Sort.Direction.DESC, "writeTime"));
	}

	public QnABoardDTO qnaDetail(Long boardSeq) throws Exception {
		return qnABoardDAO.findByBoardSeq(boardSeq);
	}

}
