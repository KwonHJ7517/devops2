package com.sist.web.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.entity.BoardEntity;
import com.sist.web.vo.BoardVO;
import com.sist.web.entity.*;
import com.sist.web.vo.*;
import com.sist.web.dao.*;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardRepository bDao;
	
	@Override
	public BoardEntity boardDetailData(int no) {
		// TODO Auto-generated method stub
		// 조회수 증가 
		BoardEntity vo=bDao.findByNo(no);
		vo.setHit(vo.getHit()+1);
		bDao.save(vo);
		// 증가된 데이터 읽기 
		vo=bDao.findByNo(no);
		return vo;
	}

	@Override
	public Map boardListData(int page) {
		// TODO Auto-generated method stub
		Map map=new HashMap();
		int rowSize=10;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		List<BoardVO> list=bDao.boardListData(start, end);
		int count=(int)bDao.count();
		int totalpage=(int)(Math.ceil(count/10.0));
		map.put("list", list);
		map.put("totalpage", totalpage);
		map.put("curpage", page);
		return map;
	}

	@Override
	public void boardInsert(BoardEntity vo) {
		// TODO Auto-generated method stub
		vo.setHit(0);
		vo.setRegdate(new Date());
		vo.setNo(maxNo());
		
		bDao.save(vo);
		
	}
	
	public int maxNo()
	{
		return bDao.maxNo();
	}

}