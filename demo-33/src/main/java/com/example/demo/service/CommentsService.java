package com.example.demo.service;

import java.util.List;

import com.example.demo.model.CommentDto;

public interface CommentsService {
	public void registerComment(CommentDto commentDto);
	public List<CommentDto> listComment(int boardId);
	public int modifyComment(CommentDto commentDto);
	public int deleteComment(CommentDto commentDto);
	public int countComment(int boardId);
}
