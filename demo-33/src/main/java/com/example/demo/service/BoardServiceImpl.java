package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Utils.FileUtis;
import com.example.demo.config.CustomUserDetails;
import com.example.demo.mapper.BoardMapper;
import com.example.demo.model.BoardDto;
import com.example.demo.model.FileDto;
import com.example.demo.model.PageDto;

@Service
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;
	private final FileUtis fileUtis;
	private final FileService fileService;

	@Autowired
	public BoardServiceImpl(BoardMapper boardMapper, FileUtis fileUtis, FileService fileService) {
		this.boardMapper = boardMapper;
		this.fileUtis = fileUtis;
		this.fileService = fileService;
	}

	// 게시판 등록
	@Transactional
	@Override
	public BoardDto RegisterBoard(String title, String content, List<MultipartFile> files) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		BoardDto boardDto = new BoardDto();
		boardDto.setTITLE(title);
		boardDto.setCONTENT(content);
		boardDto.setMEMBERID(userDetails.getUserid());
		boardMapper.registerBoard(boardDto);

		if (files != null && !files.isEmpty()) {
			List<FileDto> uploadedFiles = fileUtis.uploadFiles(files);
			fileService.saveFiles(boardDto.getBOARDID(), uploadedFiles);
		}

		return boardDto;
	}

	// 게시판 리스트
	@Override
	public List<BoardDto> boardList() {
		return boardMapper.boardList();
	}

	// 게시판 디테일
	@Override
	public BoardDto getboard(int boardId) {

		return boardMapper.getBoardlist(boardId);
	}

	// 게시판 수정
	@Transactional
	@Override
	public BoardDto updateBoard(String title, String content, int boardId, List<MultipartFile> files,
			List<String> FILE_NAME) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		BoardDto boardDto = new BoardDto();
		boardDto.setTITLE(title);
		boardDto.setCONTENT(content);
		boardDto.setMEMBERID(userDetails.getUserid());
		boardDto.setBOARDID(boardId);
		boardMapper.boardUpdate(boardDto);

		if (files != null && !files.isEmpty()) {
			List<FileDto> uploadedFiles = fileUtis.uploadFiles(files);
			System.out.println("Modify의 업로드 로직 실행되었는가 ? ");
			fileService.saveFiles(boardDto.getBOARDID(), uploadedFiles);
		}
		if (FILE_NAME != null && !FILE_NAME.isEmpty()) {
			fileService.DeleteFlagByUUIDs(FILE_NAME);
		}

		return boardDto;
	}

	// 게시판 삭제
	@Override
	public int deleteBoard(int boardId) {

		return boardMapper.boardDelete(boardId);
	}

	// 게시판 카운트
	@Override
	public int countBoard() {

		return boardMapper.countBoard();
	}

	// 게시판 페이징
	@Override
	public List<BoardDto> pageBoardList(PageDto pageDto) {

		return boardMapper.pageBoardList(pageDto);
	}

}
