package com.example.api.board.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.board.dto.CommBoardDTO;
import com.example.api.board.dto.QnABoardDTO;
import com.example.api.board.service.BoardService;
import com.example.api.board.service.CommBoardService;
import com.example.api.board.service.QnABoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
	private final BoardService boardService;
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
		return ResponseEntity.ok(boardService.boardRegist(param));
	}

	@PostMapping("/regist/community")
	public ResponseEntity<Boolean> commRegist(@RequestBody CommBoardDTO param) throws Exception {
		return ResponseEntity.ok(boardService.boardRegist(param));
	}
}
