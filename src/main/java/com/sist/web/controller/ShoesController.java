package com.sist.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    
    @GetMapping("/find_result")
    public String shoes_find_result(
            @RequestParam(name = "fd", required = false, defaultValue = "") String fd,
            @RequestParam(name = "page", required = false, defaultValue = "1") String page,
            Model model) {

        int curpage = Integer.parseInt(page);
        int rowSize = 12;
        int start = (rowSize * curpage) - (rowSize - 1);
        int end = rowSize * curpage;

        List<ShoesListVO> list;
        int totalpage;

        if (fd.isEmpty()) {
            list = sService.shoesListData(start, end);
            totalpage = sService.shoesTotalpage();
        } else {
            list = sService.shoesFindData(fd, start, end);
            totalpage = sService.shoesFindTotalPage(fd);
        }

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
        model.addAttribute("fd", fd);

        model.addAttribute("main_html", "shoes/find_result");
        return "index";
    }
    
    @GetMapping("/proxy")
    public ResponseEntity<byte[]> proxy(@RequestParam(name = "url", required = false) String urlStr) {
        if (urlStr == null || urlStr.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return ResponseEntity.status(responseCode).build();
            }

            String contentType = conn.getContentType();

            try (InputStream in = conn.getInputStream()) {
                byte[] imageBytes = in.readAllBytes();

                HttpHeaders headers = new HttpHeaders();
                if (contentType != null) {
                    headers.setContentType(MediaType.parseMediaType(contentType));
                } else {
                    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                }

                return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
