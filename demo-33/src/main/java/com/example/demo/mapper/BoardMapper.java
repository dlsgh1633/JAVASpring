package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.BoardDto;
import com.example.demo.model.FileDto;
import com.example.demo.model.PageDto;

@Mapper
public interface BoardMapper {
	public void registerBoard(BoardDto boardDto);
	public List<BoardDto> boardList();
	public BoardDto getBoardlist(int boardId);
	public int boardUpdate(BoardDto boardDto);
	public int boardDelete(int boardId);
	public int countBoard();
	public List<BoardDto> pageBoardList(PageDto pageDto);
	
}
