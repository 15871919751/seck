package org.ct.seckill.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author K
 * @Date 2020/1/26 21:42
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MiaoshaGoods implements Serializable {
    private Long id;
    private Long goodsId;
    private Double seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
