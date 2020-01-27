package org.ct.seckill.dao;
import org.ct.seckill.vo.GoodsVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author K
 * @Date 2020/1/26 22:07
 * @Version 1.0
 */
@Repository("goodsDao")
public interface GoodsDao {
    /**
     * 通过miaosha_goods和goods的id联查
     *
     * @return 返回一个GoodsVo对象
     */
    List<GoodsVo> getListGoodsVo();
}
