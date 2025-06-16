package com.sist.web.entity;

import java.util.Date;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Entity(name="cjw_board")
@Data
@DynamicUpdate
public class BoardEntity {
   @Id
   private int no;
   private String name;
   private String subject;
   private String content;
   @Column(insertable = true,updatable = false)
   private String pwd;
   @Column(insertable = true,updatable = false)
   private Date regdate;
   private int hit;
}