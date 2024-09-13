package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.config.CustomUserDetails;
import com.example.demo.mapper.CommentsMapper;
import com.example.demo.model.BoardDto;
import com.example.demo.model.CommentDto;

@Service
public class CommentsServiceImpl implements CommentsService {

	private final CommentsMapper commentsMapper;

	@Autowired
	public CommentsServiceImpl(CommentsMapper commentsMapper) {
		this.commentsMapper = commentsMapper;
	}

	// 댓글 등록
	@Override
	public CommentDto registerComment(int boardId, int parentsId, String content) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		CommentDto commentDto = new CommentDto();
		commentDto.setBOARDID(boardId);
		commentDto.setMEMBERID(userDetails.getUserid());
		commentDto.setCONTENT(content);
		commentDto.setPARENTSID(parentsId);

		commentsMapper.RegisterComment(commentDto);
		return commentDto;
	}

	// 댓글 불러오기
	@Override
	public List<CommentDto> listComment(int boardId, int memberId) {
		List<CommentDto> commentList = commentsMapper.listComment(boardId);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.isAuthenticated() && !(authentication.getPrincipal() instanceof String)) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			int SessionUserId = userDetails.getUserid();
			for (CommentDto comment : commentList) {

				BoardDto boardDto = new BoardDto();
				boardDto.setMEMBERID(memberId);
				if (comment.getMEMBERID() == SessionUserId || boardDto.getMEMBERID() == SessionUserId) {

					comment.setWriter(true);
					comment.setUserNullcheck(true);

				} else {
					comment.setWriter(false);
					comment.setUserNullcheck(true);
				}
			}
		} else {
			CommentDto commentDto = new CommentDto();
			commentDto.setUserNullcheck(false);
			System.out.println("비로그인 사용자입니다.");
		}

		return commentList;
	}

	// 댓글 수정
	@Override
	public CommentDto modifyComment(int commentId, String content) {
		CommentDto commentDto = new CommentDto();
		commentDto.setCOMMENTID(commentId);
		commentDto.setCONTENT(content);
		commentsMapper.modifyComment(commentDto);
		return commentDto;
	}

	// 댓글 삭제
	@Override
	public CommentDto deleteComment(int commentId) {
		CommentDto commentDto = new CommentDto();
		commentDto.setCOMMENTID(commentId);
		commentsMapper.deleteComment(commentDto);
		return commentDto;
	}

	// 댓글 수 조회
	@Override
	public int countComment(int boardId) {
		// TODO Auto-generated method stub
		return commentsMapper.countComment(boardId);
	}

}
