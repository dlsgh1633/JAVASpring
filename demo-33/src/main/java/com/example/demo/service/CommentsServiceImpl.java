package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.CommentsMapper;
import com.example.demo.model.CommentDto;

@Service
public class CommentsServiceImpl implements CommentsService{
	
	private final CommentsMapper commentsMapper;
	
	@Autowired
	public CommentsServiceImpl(CommentsMapper commentsMapper) {
		this.commentsMapper = commentsMapper;
	}
	
	
	@Override
	public void registerComment(CommentDto commentDto) {
		commentsMapper.RegisterComment(commentDto);	
	}
	
	
	
	@Override
	public List<CommentDto> listComment(int boardId){
	 	
		 return commentsMapper.listComment(boardId);
	}
	
	@Override
	public int modifyComment(CommentDto commentDto) {
		
		return commentsMapper.modifyComment(commentDto);
	}
	@Override
	public int deleteComment(CommentDto commentDto) {
		
		return commentsMapper.deleteComment(commentDto);
	}


	@Override
	public int countComment(int boardId) {
		// TODO Auto-generated method stub
		return commentsMapper.countComment(boardId);
	}
	
}
