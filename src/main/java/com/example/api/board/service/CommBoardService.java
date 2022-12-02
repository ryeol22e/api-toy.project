package com.example.api.board.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.api.board.dto.CommBoardDTO;
import com.example.api.board.repository.CommBoardDAO;
import com.example.api.utils.UtilsData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommBoardService {
	private final CommBoardDAO commBoardDAO;

	public List<CommBoardDTO> commList() throws Exception {
		return commBoardDAO.findAll(Sort.by(Sort.Direction.DESC, "writeTime"));
	}
	
	public CommBoardDTO commDetail(Long boardSeq) throws Exception {
		CommBoardDTO result = commBoardDAO.findByBoardSeq(boardSeq);

		if(result.getImageData()!=null) {
			result.setImageDataByte(UtilsData.returnBlobData(result.getImageData()));
		}

		return result;
	}

	public boolean boardRegist(CommBoardDTO data) throws Exception {
		boolean result = false;
		CommBoardDTO saveDto = commBoardDAO.save(data);

		if(saveDto!=null) {
			result = true;
		}
		
		return result;
	}

	public Boolean deleteBoardData(CommBoardDTO param) throws Exception {
		boolean result = true;
		
		try {
			commBoardDAO.delete(param);	
		} catch (Exception e) {
			// TODO: handle exception
			log.error("delete error : {}", e.getMessage());
			result = false;
		}

		return result;
	}

}
