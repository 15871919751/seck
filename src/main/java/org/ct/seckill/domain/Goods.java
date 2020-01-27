package org.ct.seckill.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @Author K
 * @Date 2020/1/26 21:35
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods implements Serializable {
    private Long id;
    /** 商品名称*/
    private String goodsName;
    /** 商品标题*/
    private String goodsTitle;
    /** 商品图片*/
    private String goodsImg;
    /** 商品详情*/
    private String goodsDetail;
    /** 商品价格*/
    private Double goodsPrice;
    /** 商品库存*/
    private Integer goodsStock;

}
