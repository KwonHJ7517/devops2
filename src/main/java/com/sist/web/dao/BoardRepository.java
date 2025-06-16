package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.BoardEntity;
import com.sist.web.vo.BoardVO;

import java.util.*;
@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{
   // 상세보기 
   public BoardEntity findByNo(int no);
   // 목록 
   @Query(value="SELECT no,name,subject,TO_CHAR(content) as content,"
		 +"TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,num "
		 +"FROM (SELECT no,name,subject,content,regdate,hit,rownum as num "
		 +"FROM (SELECT no,name,subject,content,regdate,hit "
		 +"FROM cjw_board ORDER BY no DESC)) "
		 +"WHERE num BETWEEN :start AND :end",nativeQuery = true)
   public List<BoardVO> boardListData(@Param("start") int start,@Param("end") int end);
   
   // Sequence 
   @Query(value="SELECT NVL(MAX(no)+1,1) FROM cjw_board",nativeQuery = true)
   public int maxNo();
   // save , count , delete 
}