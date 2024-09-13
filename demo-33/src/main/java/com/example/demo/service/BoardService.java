package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.BoardDto;
import com.example.demo.model.PageDto;

public interface BoardService {

	public BoardDto RegisterBoard(String title,String content,List<MultipartFile> files);
	public List<BoardDto> boardList();
	public BoardDto getboard(int boardId);
	public BoardDto updateBoard(String title,String content,int boardId,List<MultipartFile>files,List<String> FILE_NAME);
	public int deleteBoard(int boardId);
	public int countBoard();
	public List<BoardDto> pageBoardList(PageDto pageDto);
}
