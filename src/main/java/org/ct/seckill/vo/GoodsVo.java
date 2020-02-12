package org.ct.seckill.vo;

import lombok.*;
import org.ct.seckill.domain.Goods;

import java.util.Date;

/**
 * @Author K
 * @Date 2020/1/27 1:02
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GoodsVo extends Goods {
    private Double secKillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
