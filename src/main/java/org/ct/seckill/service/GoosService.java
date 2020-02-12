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

    /**
     * 根据商品Id查询一个商品详细对象
     * @param goodsId 商品Id
     * @return 返回一个GoodsVo对象
     * */
    GoodsVo getGoodsVoByGoodsId(Long goodsId);

    /**
     * 减少库存
     * @param goodsVo 商品信息
     * @return 减库存操作是否成功
     * */
    boolean reduceStock(GoodsVo goodsVo);
}
