package com.sist.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.ShoesRepository;
import com.sist.web.entity.ShoesEntity;
import com.sist.web.vo.ShoesListVO;

@Service
public class ShoesServiceImpl implements ShoesService{
	
	@Autowired
	private ShoesRepository sDao;
	
	@Override
	public List<ShoesListVO> shoesListData(int start,int end) {
		return sDao.shoesListData(start, end);
	}

	@Override
	public ShoesEntity shoesDetailData(int goodsId) {
		return sDao.findByGoodsId(goodsId);
	}

	@Override
	public int shoesTotalpage() {
		int count = (int)sDao.count();
		return (int)Math.ceil(count / 12.0);
	}

	@Override
	public List<ShoesListVO> shoesFindData(String fd, int start, int end) {
	    return sDao.shoesFindData(fd, start, end);
	}

	@Override
	public int shoesFindTotalPage(String fd) {
	    int count = sDao.shoesFindCount(fd);
	    return (int)Math.ceil(count / 12.0);
	}

}
