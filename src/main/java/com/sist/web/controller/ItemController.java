package com.sist.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.entity.ItemEntity;
import com.sist.web.service.ItemService;
import com.sist.web.vo.ItemListVO;

@Controller
public class ItemController {
	@Autowired
	private ItemService iService;
	
	@GetMapping("/")
	   public String item_main(@RequestParam(name="page",required = false) String page,Model model)
	   {
		   if(page==null)
			   page="1";
		   int curpage=Integer.parseInt(page);
		   int rowSize=12;
		   int start=(rowSize*curpage)-(rowSize-1);
		   int end=rowSize*curpage;
		   List<ItemListVO> list=iService.itemListData(start, end);
		   int totalpage=iService.itemTotalpage();
		   
		   final int BLOCK=10;
		   int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		   
		   if(endPage>totalpage)
			   endPage=totalpage;

		   model.addAttribute("list", list);
		   model.addAttribute("curpage", curpage);
		   model.addAttribute("totalpage", totalpage);
		   model.addAttribute("startPage", startPage);
		   model.addAttribute("endPage", endPage);
		   model.addAttribute("main_html", "main/home");
		   return "index";
	   }
	
	   @GetMapping("/detail")
	   public String item_detail(@RequestParam("ino") int ino,Model model)
	   {
		   ItemEntity vo=iService.itemDetailData(ino);
		   model.addAttribute("vo", vo);
		   model.addAttribute("main_html", "item/detail");
		   return "index";
	   }
}
