package com.sist.web.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.sist.web.entity.ItemEntity;
import com.sist.web.vo.ItemListVO;

public interface ItemService {
	public List<ItemListVO> itemListData(int start,int end);
	public ItemEntity itemDetailData(int ino);
	public int itemTotalpage();
}
