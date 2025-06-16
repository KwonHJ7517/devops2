package com.sist.web.service;

import java.util.*;

import org.springframework.data.repository.query.Param;

import com.sist.web.entity.BoardEntity;
import com.sist.web.vo.BoardVO;

public interface BoardService {
	public BoardEntity boardDetailData(int no);
	public Map boardListData(int page);
	public void boardInsert(BoardEntity vo);
	public int maxNo();
}