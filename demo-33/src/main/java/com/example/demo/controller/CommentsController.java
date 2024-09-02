package com.example.demo.controller;

import java.util.List;

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

	@GetMapping("/board/detail/comment")
	@ResponseBody
	public List<CommentDto> ListComment(int boardId, int memberId) {

		System.out.println("boardId 확인" + boardId);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.isAuthenticated());
		List<CommentDto> commentList = commentsService.listComment(boardId);

		if (authentication.isAuthenticated() && !(authentication.getPrincipal() instanceof String)) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			int SessionUserId = userDetails.getUserid();
			System.out.println("현재 접속중인 Username : " + userDetails.getUsername() + userDetails.getUserid());
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

	@PostMapping("/comment/post")
	public ResponseEntity<CommentDto> postComments(@RequestParam String content, @RequestParam int boardId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		CommentDto commentDto = new CommentDto();
		commentDto.setBOARDID(boardId);
		commentDto.setMEMBERID(userDetails.getUserid());
		commentDto.setCONTENT(content);

		System.out.println("댓글 post 매핑 확인용 : " + boardId);
		System.out.println("commentDto 상태 확인 " + commentDto);
		commentsService.registerComment(commentDto);

		return ResponseEntity.ok(commentDto);
	}

	@PostMapping("/reply/post")
	public ResponseEntity<CommentDto> postReplys(@RequestParam String content, @RequestParam int boardId,
			@RequestParam(value = "parentsId") int parentsId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		CommentDto commentDto = new CommentDto();
		commentDto.setBOARDID(boardId);
		commentDto.setMEMBERID(userDetails.getUserid());
		commentDto.setCONTENT(content);
		commentDto.setPARENTSID(parentsId);

		System.out.println("대댓글 post 매핑 확인용 : " + boardId);
		System.out.println("commentDto 상태 확인 " + commentDto);
		commentsService.registerComment(commentDto);

		return ResponseEntity.ok(commentDto);
	}

	@PostMapping("/comment/modify")
	public ResponseEntity<CommentDto> modifyComment(@RequestParam(value = "commentId") int commentId,
			@RequestParam(value = "content") String content, Pageable pageable) {
		CommentDto commentDto = new CommentDto();
		commentDto.setCOMMENTID(commentId);
		commentDto.setCONTENT(content);
		commentsService.modifyComment(commentDto);

		return ResponseEntity.ok(commentDto);
	}

	@PostMapping("/comment/delete")
	public ResponseEntity<CommentDto> deleteComment(@RequestParam(value = "commentId") int commentId) {

		CommentDto commentDto = new CommentDto();
		commentDto.setCOMMENTID(commentId);
		commentsService.deleteComment(commentDto);

		return ResponseEntity.ok(commentDto);
	}

}
