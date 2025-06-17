package com.sist.web.service;

import java.util.List;

import com.sist.web.entity.ShoesEntity;
import com.sist.web.vo.ShoesListVO;

public interface ShoesService {
    public List<ShoesListVO> shoesListData(int start, int end);

    public ShoesEntity shoesDetailData(int goodsId);
    
    public int shoesTotalpage();
    
    public List<ShoesListVO> shoesFindData(String fd, int start, int end);
    
    public int shoesFindTotalPage(String fd);
}