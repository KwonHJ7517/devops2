package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.ItemEntity;
import com.sist.web.vo.ItemListVO;
@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer>{
	@Query(value = "SELECT ino,name,poster,type,num "
			  +"FROM (SELECT ino,name,poster,type,rownum as num "
			  +"FROM (SELECT ino,name,poster,type "
			  +"FROM item ORDER BY ino ASC)) "
			  +"WHERE num BETWEEN :start AND :end",nativeQuery = true)
		public List<ItemListVO> itemListData(@Param("start")int start,@Param("end")int end);
		
		public ItemEntity findByIno(int ino);
}
