package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.PageDto;
import com.example.demo.service.BoardService;

@Controller
public class MainController {

	private final BoardService boardService;

	@Autowired
	public MainController(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping("/main")
	public String boardPageList(PageDto pageDto, Model model,
			@RequestParam(value = "nowPage", required = false) String nowPage) {

		if (nowPage == null) {
			nowPage = "1";
		}

		int total = boardService.countBoard();

		pageDto = new PageDto(total, Integer.parseInt(nowPage), 10);

		model.addAttribute("paging", pageDto);
		model.addAttribute("list", boardService.pageBoardList(pageDto));

		return "main";
	}

}
