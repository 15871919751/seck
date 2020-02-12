package org.ct.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ct.seckill.domain.OrderInfo;

/**
 * @Author K
 * @Date 2020/2/2 12:55
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoVo {
    private OrderInfo orderInfo;
    private GoodsVo goodsVo;
}
