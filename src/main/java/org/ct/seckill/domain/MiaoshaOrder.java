package org.ct.seckill.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @Author K
 * @Date 2020/1/26 21:56
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MiaoshaOrder implements Serializable {
    private Long id;
    private Long userId;
    private Long orderId;
    private Long goodsId;
}
