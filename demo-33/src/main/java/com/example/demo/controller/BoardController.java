package com.example.demo.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import com.example.demo.Utils.FileUtis;
import com.example.demo.config.CustomUserDetails;
import com.example.demo.model.BoardDto;
import com.example.demo.model.FileDto;
import com.example.demo.service.BoardService;
import com.example.demo.service.CommentsService;
import com.example.demo.service.FileService;

@Controller
public class BoardController {

	private final BoardService boardService;

	private final FileService fileService;

	@Autowired
	public BoardController(BoardService boardService, FileService fileService) {
		this.boardService = boardService;
		this.fileService = fileService;

	}

	// 작성 페이지 접속
	@GetMapping("/board/write")
	public String getWrite() {

		return "board/write";
	}

	@PostMapping("/board/postwrite")
	public ResponseEntity<BoardDto> postWrite(@RequestParam("TITLE") @Valid String title,
			@RequestParam("CONTENT") @Valid String content,
			@RequestParam(value = "files", required = false) List<MultipartFile> files) {

		BoardDto boardDto = boardService.RegisterBoard(title, content, files);

		return ResponseEntity.ok(boardDto);
	}

	@GetMapping("/board/detail")
	public String getBoardList(Model model, int boardId) {

		model.addAttribute("boardcheck", boardService.getboard(boardId));
		model.addAttribute("fileList", fileService.fileList(boardId));
		
		return "board/detail";
	}

	@GetMapping("/board/modify")
	public String getModify(Model model, int boardId) {

		model.addAttribute("boardmodify", boardService.getboard(boardId));
		model.addAttribute("fileList", fileService.fileList(boardId));

		return "board/modify";
	}

	// 게시판 수정
	@PostMapping("/board/postmodify")
	public ResponseEntity<BoardDto> postModify(@RequestParam("TITLE") @Valid String title,
			@RequestParam("CONTENT") @Valid String content, @RequestParam("BOARDID") int boardId,
			@RequestParam(value = "FILE_NAME", required = false) List<String> FILE_NAME,
			@RequestParam(value = "files", required = false) List<MultipartFile> files) {

		BoardDto boardDto = boardService.updateBoard(title, content, boardId, files, FILE_NAME);

		return ResponseEntity.ok(boardDto);
	}

	@PostMapping("/board/delete")
	public ResponseEntity<String> boardDelete(int boardId) {

		boardService.deleteBoard(boardId);
		fileService.boardDeleteFiles(boardId);
		return ResponseEntity.ok("success");

	}

}
