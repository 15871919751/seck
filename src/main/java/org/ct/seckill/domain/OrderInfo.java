package org.ct.seckill.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author K
 * @Date 2020/1/26 21:49
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo implements Serializable {
    private Long id;
    private Long userId;
    private Long goodsId;
    private Long deliveryddrId;
    private String goodsName;
    private Integer goodsCount;
    private Double goodsPrice;
    private Integer orderChannel;
    private Integer status;
    private Date createDate;
    private Date payDate;
}
