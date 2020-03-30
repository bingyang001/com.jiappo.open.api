package com.panli.service.delivery.dao;

import com.hummer.dao.annotation.DaoAnnotation;
import com.panli.service.delivery.support.model.po.OrderPo;
import org.apache.ibatis.annotations.Param;

/**
 * @author bingy
 */
@DaoAnnotation
public interface OrderDao {
    /**
     * save order po to order table.
     *
     * @param orderPo po
     * @return
     */
    int save(@Param("orderPo") OrderPo orderPo);
}
