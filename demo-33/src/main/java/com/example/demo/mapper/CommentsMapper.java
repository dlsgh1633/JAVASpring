package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.CommentDto;

@Mapper
public interface CommentsMapper {
	public void RegisterComment(CommentDto commentDto);
	public List<CommentDto> listComment(int boardId);
	public int modifyComment(CommentDto commentDto);
	public int deleteComment(CommentDto commentDto);
	public int countComment(int boardId);
}
