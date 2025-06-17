package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.ShoesEntity;
import com.sist.web.vo.ShoesListVO;

@Repository
public interface ShoesRepository extends JpaRepository<ShoesEntity, Integer> {

    @Query(value = "SELECT goods_id,name_kor,name_eng,img,brand,sku,color,type,variance,release_date,release_price,num "
            + "FROM (SELECT goods_id,name_kor,name_eng,img,brand,sku,color,type,variance,release_date,release_price,rownum as num "
            + "FROM (SELECT goods_id,name_kor,name_eng,img,brand,sku,color,type,variance,release_date,release_price "
            + "FROM shoes ORDER BY goods_id ASC)) "
            + "WHERE num BETWEEN :start AND :end", nativeQuery = true)
    public List<ShoesListVO> shoesListData(@Param("start") int start, @Param("end") int end);

    public ShoesEntity findByGoodsId(int goodsId);
    
    @Query(value = "SELECT goods_id,name_kor,name_eng,img,brand,sku,color,type,variance,release_date,release_price,num "
            + "FROM (SELECT goods_id,name_kor,name_eng,img,brand,sku,color,type,variance,release_date,release_price,rownum as num "
            + "FROM (SELECT goods_id,name_kor,name_eng,img,brand,sku,color,type,variance,release_date,release_price "
            + "FROM shoes WHERE name_kor LIKE '%'||:fd||'%' OR brand LIKE '%'||:fd||'%' ORDER BY goods_id ASC)) "
            + "WHERE num BETWEEN :start AND :end", nativeQuery = true)
    public List<ShoesListVO> shoesFindData(@Param("fd") String fd, @Param("start") int start, @Param("end") int end);

    @Query(value = "SELECT COUNT(*) FROM shoes WHERE name_kor LIKE '%'||:fd||'%' OR brand LIKE '%'||:fd||'%'", nativeQuery = true)
    public int shoesFindCount(@Param("fd") String fd);

}
