package com.example.demo.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
	private final FileUtis fileUtis;
	private final FileService fileService;
	private final CommentsService commentsService;

	@Autowired
	public BoardController(BoardService boardService, FileUtis fileUtis, FileService fileService,CommentsService commentsService) {
		this.boardService = boardService;
		this.fileUtis = fileUtis;
		this.fileService = fileService;
		this.commentsService = commentsService;
	}

	@GetMapping("/board/write")
	public String getWrite() {

		return "board/write";
	}
	
	
	
	
	/*
	 * 동기식 게시판 등록.
	 * 
	 * @PostMapping("/board/postwrite") public String postWrite(BoardDto boardDto) {
	 * Authentication authentication =
	 * SecurityContextHolder.getContext().getAuthentication(); CustomUserDetails
	 * userDetails = (CustomUserDetails) authentication.getPrincipal();
	 * System.out.println("게시판 등록 컨트롤러 진입");
	 * boardDto.setMEMBERID(userDetails.getUserid());
	 * boardService.RegisterBoard(boardDto);
	 * 
	 * return "/main"; }
	 */ // 파일 업로드 진행
		// 나중에 DTO 클래스를 이용해서 파일업로드시 -> 임시파일이 생성되는지 디버깅해서 알아보기
		//트랜잭션 처리 ? 
	@PostMapping("/board/postwrite")
	public ResponseEntity<BoardDto> postWrite(@RequestParam("TITLE") String title,
			@RequestParam("CONTENT") String content,
			@RequestParam(value = "files", required = false) List<MultipartFile> files) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		System.out.println("작성시 files 정보 " + files);
		BoardDto boardDto = new BoardDto();
		boardDto.setTITLE(title);
		boardDto.setCONTENT(content);
		boardDto.setMEMBERID(userDetails.getUserid());
		boardService.RegisterBoard(boardDto);
			
		if (files != null && !files.isEmpty()) {
			List<FileDto> uploadedFiles = fileUtis.uploadFiles(files);
			System.out.println("Boardid는 등록이 되고나서 가져와진건가 ? " + boardDto.getBOARDID());
			fileService.saveFiles(boardDto.getBOARDID(), uploadedFiles);
		}

		return ResponseEntity.ok(boardDto);
	}

	@GetMapping("/board/detail")
	public String getBoardList(Model model, int boardId) {

		model.addAttribute("boardcheck", boardService.getboard(boardId));
		model.addAttribute("fileList", fileService.fileList(boardId));
		
		System.out.println();
		return "board/detail";

	}

	@GetMapping("/board/modify")
	public String getModify(Model model, int boardId) {

		model.addAttribute("boardmodify", boardService.getboard(boardId));
		model.addAttribute("fileList", fileService.fileList(boardId));

		return "board/modify";
	}

	// UUID 리스트 테스트
	@PostMapping("/board/postmodify")
	public ResponseEntity<BoardDto> postModify(@RequestParam("TITLE") String title,
			@RequestParam("CONTENT") String content,@RequestParam("BOARDID") int boardId,
			@RequestParam(value = "FILE_NAME", required = false) List<String> FILE_NAME,
			@RequestParam(value = "files", required = false) List<MultipartFile> files) {
	
		System.out.println("파일즈 정보 나오냐 ?" + files);
		System.out.println("UUID 정보 확인 " + FILE_NAME);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		BoardDto boardDto = new BoardDto();
		boardDto.setTITLE(title);
		boardDto.setCONTENT(content);
		boardDto.setMEMBERID(userDetails.getUserid());
		boardDto.setBOARDID(boardId);
		
		
		
		boardService.updateBoard(boardDto);
		
		
		if (files != null && !files.isEmpty()) {
			List<FileDto> uploadedFiles = fileUtis.uploadFiles(files);
			System.out.println("Modify의 업로드 로직 실행되었는가 ? ");
			fileService.saveFiles(boardDto.getBOARDID(), uploadedFiles);
		}
		if(FILE_NAME != null && !FILE_NAME.isEmpty()) {
			fileService.DeleteFlagByUUIDs(FILE_NAME);
		}
		
		return ResponseEntity.ok(boardDto);
	}

	@PostMapping("/board/delete")
	public ResponseEntity<String> boardDelete(int boardId) {

		boardService.deleteBoard(boardId);
        fileService.boardDeleteFiles(boardId);
		return ResponseEntity.ok("success");

	}
	
	

	
	
	
}
