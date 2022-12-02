package com.example.api.board.controller;

import java.sql.Blob;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.api.board.dto.CommBoardDTO;
import com.example.api.board.dto.QnABoardDTO;
import com.example.api.board.service.CommBoardService;
import com.example.api.board.service.QnABoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
	private final QnABoardService qnaBoardService;
	private final CommBoardService commBoardService;

	@GetMapping("/qna/list")
	public ResponseEntity<List<QnABoardDTO>> qnaList() throws Exception {
		return ResponseEntity.ok(qnaBoardService.qnaList());
	}

	@GetMapping("/qna/list/{boardSeq}")
	public ResponseEntity<QnABoardDTO> qnaDetail(@PathVariable(name="boardSeq") Long boardSeq) throws Exception {
		return ResponseEntity.ok(qnaBoardService.qnaDetail(boardSeq));
	}

	@GetMapping("/comm/list")
	public ResponseEntity<List<CommBoardDTO>> commList() throws Exception {
		return ResponseEntity.ok(commBoardService.commList());
	}

	@GetMapping("/comm/list/{boardSeq}")
	public ResponseEntity<CommBoardDTO> commDetail(@PathVariable(name="boardSeq") Long boardSeq) throws Exception {
		return ResponseEntity.ok(commBoardService.commDetail(boardSeq));
	}

	@PostMapping("/regist/qna")
	public ResponseEntity<Boolean> qnaRegist(@RequestBody QnABoardDTO param) throws Exception {
		return ResponseEntity.ok(qnaBoardService.boardRegist(param));
	}

	@PostMapping("/regist/community")
	public ResponseEntity<Boolean> commRegist(CommBoardDTO param) throws Exception {
		if(param.getImage()!=null) {
			MultipartFile file = param.getImage();
			byte[] byteImage = file.getBytes();
			Blob image = new SerialBlob(byteImage);
			
			param.setImageData(image);
			param.setImageName(file.getOriginalFilename());
			param.setImageUrl("http://localhost:9999/image/".concat(file.getOriginalFilename()));
		}

		return ResponseEntity.ok(commBoardService.boardRegist(param));
	}

	@DeleteMapping("/qna/del/{boardSeq}")
	public ResponseEntity<Boolean> deletQnAeBoard(QnABoardDTO data) throws Exception {
		return ResponseEntity.ok(qnaBoardService.deleteBoardData(data));
	}

	@DeleteMapping("/comm/del/{boardSeq}")
	public ResponseEntity<Boolean> deleteCommBoard(CommBoardDTO data) throws Exception {
		return ResponseEntity.ok(commBoardService.deleteBoardData(data));
	}
}
