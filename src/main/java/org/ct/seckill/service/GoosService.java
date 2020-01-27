package org.ct.seckill.service;

import org.ct.seckill.vo.GoodsVo;

import java.util.List;

/**
 * @Author K
 * @Date 2020/1/26 22:05
 * @Version 1.0
 */
public interface GoosService {
    /**
     *  返回GoodsVo对象
     * @return 返回GoodsVo对象
     * */
    List<GoodsVo> getListGoodsVo();
}
