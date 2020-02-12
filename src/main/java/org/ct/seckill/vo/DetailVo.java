package org.ct.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ct.seckill.domain.SecKillUser;

/**
 * @Author K
 * @Date 2020/2/1 16:53
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailVo {
    private int secKillStatus;
    private int remainSeconds;
    private GoodsVo goodsVo;
    private SecKillUser user;
}
