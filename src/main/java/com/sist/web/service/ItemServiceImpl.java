package com.sist.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.ItemRepository;
import com.sist.web.entity.ItemEntity;
import com.sist.web.vo.ItemListVO;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemRepository iDao;
	
	@Override
	public List<ItemListVO> itemListData(int start,int end) {
		// TODO Auto-generated method stub
		return iDao.itemListData(start, end);
	}

	@Override
	public ItemEntity itemDetailData(int ino) {
		// TODO Auto-generated method stub
		return iDao.findByIno(ino);
	}

	@Override
	public int itemTotalpage() {
		// TODO Auto-generated method stub
		int count=(int)iDao.count();
		return (int)(Math.ceil(count/12.0));
	}

}
