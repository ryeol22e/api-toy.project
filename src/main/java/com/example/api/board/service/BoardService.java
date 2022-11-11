package com.example.api.board.service;

import org.springframework.stereotype.Service;

import com.example.api.board.dto.BaseBoardDTO;
import com.example.api.board.repository.BoardDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardDAO boardDAO;
	
	public Boolean boardRegist(BaseBoardDTO param) throws Exception {
		boardDAO.save(param);

		return true;
	}

	public Boolean deleteBoardData(BaseBoardDTO param) throws Exception {
		boolean result = true;
		
		try {
			boardDAO.delete(param);	
		} catch (Exception e) {
			// TODO: handle exception
			log.error("delete error : {}", e.getMessage());
			result = false;
		}

		return result;
	}
}
