package com.example.demo.service;

import java.util.List;

import com.example.demo.model.BoardDto;
import com.example.demo.model.PageDto;

public interface BoardService {
		
	public void RegisterBoard(BoardDto boardDto);
	public List<BoardDto> boardList();
	public BoardDto getboard(int boardId);
	public int updateBoard(BoardDto boardDto);
	public int deleteBoard(int boardId);
	public int countBoard();
	public List<BoardDto> pageBoardList(PageDto pageDto);
}
