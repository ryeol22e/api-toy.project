package com.example.api.board.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.api.board.dto.QnABoardDTO;
import com.example.api.board.repository.QnABoardDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

	public boolean boardRegist(QnABoardDTO data) throws Exception {
		boolean result = false;
		QnABoardDTO saveDto = qnABoardDAO.save(data);

		if(saveDto!=null) {
			result = true;
		}
		
		return result;
	}

	public Boolean deleteBoardData(QnABoardDTO param) throws Exception {
		boolean result = true;
		
		try {
			qnABoardDAO.delete(param);	
		} catch (Exception e) {
			// TODO: handle exception
			log.error("delete error : {}", e.getMessage());
			result = false;
		}

		return result;
	}

}
