package org.ct.seckill.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ct.seckill.domain.SecKillUser;
import org.ct.seckill.vo.GoodsVo;

/**
 * @Author K
 * @Date 2020/2/3 21:49
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeckillMessage {
    private SecKillUser user;
    private long goodsId;

}
