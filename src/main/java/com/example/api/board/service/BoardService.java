package com.example.api.board.service;

import org.springframework.stereotype.Service;

import com.example.api.board.dto.BaseBoardDTO;
import com.example.api.board.repository.BoardDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardDAO boardDAO;
	
	public Boolean boardRegist(BaseBoardDTO param) throws Exception {
		boardDAO.save(param);

		return true;
	}
}
