package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.config.CustomUserDetails;
import com.example.demo.model.BoardDto;
import com.example.demo.model.CommentDto;
import com.example.demo.service.CommentsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommentsController {

	private final CommentsService commentsService;

	@Autowired
	public CommentsController(CommentsService commentsService) {
		this.commentsService = commentsService;
	}
	// 댓글 불러오기 .
	@GetMapping("/board/detail/comment")
	@ResponseBody
	public List<CommentDto> ListComment(int boardId, int memberId) {

		List<CommentDto> commentList = commentsService.listComment(boardId, memberId);

		return commentList;
	}

	// 등록
	@PostMapping("/comment/post")
	public ResponseEntity<CommentDto> postComments(@RequestParam @Valid String content, @RequestParam int boardId,
			@RequestParam(value = "parentsId", required = false) int parentsId) {

		CommentDto commentDto = commentsService.registerComment(boardId, parentsId, content);

		return ResponseEntity.ok(commentDto);
	}

	//수정
	@PostMapping("/comment/modify")
	public ResponseEntity<CommentDto> modifyComment(@RequestParam(value = "commentId") int commentId,
			@RequestParam(value = "content") @Valid String content, Pageable pageable) {
		
		CommentDto commentDto =	commentsService.modifyComment(commentId,content);

		return ResponseEntity.ok(commentDto);
	}

	
	//삭제
	@PostMapping("/comment/delete")
	public ResponseEntity<CommentDto> deleteComment(@RequestParam(value = "commentId") int commentId) {
				
		CommentDto commentDto = commentsService.deleteComment(commentId);

		return ResponseEntity.ok(commentDto);
	}

}
