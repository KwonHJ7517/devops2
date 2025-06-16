package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;
import com.sist.web.service.*;
import com.sist.web.entity.*;
import com.sist.web.vo.*;

@Controller
public class BoardController {
    
	@Autowired
	private BoardService bService;
	
	
	@GetMapping("/board/list")
	public String board_list(@RequestParam(name = "page",required = false) String page, Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		
		Map map=bService.boardListData(curpage);
		
		List<BoardVO> list=(List<BoardVO>)map.get("list");
		int totalpage=(int)map.get("totalpage");
		
		
		model.addAttribute("list", list);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("curpage", curpage);
		model.addAttribute("main_html", "board/list");
		return "index";
	}
	@GetMapping("/board/insert")
	public String board_insert(Model model)
	{
		model.addAttribute("main_html", "board/insert");
		return "index";
	}
	@GetMapping("/board/detail")
	public String board_detail(Model model)
	{
		model.addAttribute("main_html", "board/detail");
		return "index";
	}
}