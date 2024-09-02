package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mapper.BoardMapper;
import com.example.demo.model.BoardDto;
import com.example.demo.model.PageDto;

@Service
public class BoardServiceImpl implements BoardService{
	
	private final BoardMapper boardMapper;
	
	@Autowired
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
		
	}
	
	
	
	
	@Override
	@Transactional
	public void RegisterBoard(BoardDto boardDto) {
	 boardMapper.registerBoard(boardDto);	
	 	
	}
	
	@Override
	public List<BoardDto> boardList(){
		return boardMapper.boardList();
	}
	
	@Override
	public BoardDto getboard(int boardId) {
		
		return boardMapper.getBoardlist(boardId);
	}
	
	
	@Override
	@Transactional
	public int updateBoard(BoardDto boardDto) {
		
		return boardMapper.boardUpdate(boardDto);
	}

	@Override
	@Transactional
	public int deleteBoard(int boardId) {
		    
		return boardMapper.boardDelete(boardId);
	}



	@Override
	public int countBoard() {
		
		return boardMapper.countBoard();
	}


	@Override
	public List<BoardDto> pageBoardList(PageDto pageDto) {
		
		return boardMapper.pageBoardList(pageDto);
	}
	
	
	
}
