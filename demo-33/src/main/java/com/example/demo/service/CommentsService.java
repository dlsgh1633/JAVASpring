package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.example.demo.model.CommentDto;

public interface CommentsService {
	public CommentDto  registerComment(int boardId,int parentsId,String content);
	public List<CommentDto> listComment(int boardId,int memberId);
	public CommentDto modifyComment(int commentId,String content);
	public CommentDto deleteComment(int commentId);
	public int countComment(int boardId);
}
