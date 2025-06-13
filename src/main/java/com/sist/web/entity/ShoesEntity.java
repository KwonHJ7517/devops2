package com.sist.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "shoes")
@Data
public class ShoesEntity {
    @Id
    @Column(name = "goods_id")
    private int goodsId;

    @Column(name = "name_kor")
    private String nameKor;

    @Column(name = "name_eng")
    private String nameEng;

    @Column(name = "img")
    private String img;

    @Column(name = "brand")
    private String brand;

    @Column(name = "sku")
    private String sku;

    @Column(name = "color")
    private String color;

    @Column(name = "type")
    private String type;

    @Column(name = "variance")
    private String variance;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "release_price")
    private String releasePrice;

    @Column(name = "rt_price")
    private int rtPrice;

    @Column(name = "im_sell")
    private int imSell;

    @Column(name = "im_buy")
    private int imBuy;

    @Column(name = "bookmark")
    private int bookmark;

    @Column(name = "category_id") // ← 오타 주의
    private int categoryId;
}

