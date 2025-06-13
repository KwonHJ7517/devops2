package com.sist.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.entity.ShoesEntity;
import com.sist.web.service.ShoesService;
import com.sist.web.vo.ShoesListVO;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShoesController {
    @Autowired
    private ShoesService sService;
    
    @GetMapping("/")
    public String shoes_main(@RequestParam(name = "page", required = false) String page, Model model) {
        if (page == null)
            page = "1";
        int curpage = Integer.parseInt(page);
        int rowSize = 12;
        int start = (rowSize * curpage) - (rowSize - 1);
        int end = rowSize * curpage;
        
        List<ShoesListVO> list = sService.shoesListData(start, end);
        int totalpage = sService.shoesTotalpage();
        
        final int BLOCK = 10;
        int startPage = ((curpage - 1) / BLOCK * BLOCK) + 1;
        int endPage = ((curpage - 1) / BLOCK * BLOCK) + BLOCK;
        
        if (endPage > totalpage)
            endPage = totalpage;

        model.addAttribute("list", list);
        model.addAttribute("curpage", curpage);
        model.addAttribute("totalpage", totalpage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("main_html", "main/home");
        return "index";
    }
    
    @GetMapping("/detail")
    public String shoes_detail(@RequestParam("goods_id") int goodsId, Model model) {
        ShoesEntity vo = sService.shoesDetailData(goodsId);
        model.addAttribute("vo", vo);
        model.addAttribute("main_html", "shoes/detail");
        return "index";
    }
    
    @GetMapping("/proxy")
    public ResponseEntity<byte[]> proxy(@RequestParam("url") String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0"); // 필수: 일부 서버는 user-agent 없으면 차단
            conn.setRequestMethod("GET");

            InputStream in = conn.getInputStream();
            byte[] imageBytes = in.readAllBytes();

            // Content-Type 설정 (대부분 PNG이지만 URL에 따라 다를 수 있음)
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG); // 필요시 MediaType.IMAGE_JPEG 등으로 변경

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
