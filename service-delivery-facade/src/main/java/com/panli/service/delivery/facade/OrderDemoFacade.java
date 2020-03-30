package com.panli.service.delivery.facade;


import com.panli.service.delivery.facade.dto.request.OrderDemoReq;

/**
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/28 15:19
 **/
public interface OrderDemoFacade {
    /**
     * save new order to db
     * @param req
     */
    void saveNewOrder(OrderDemoReq req);
}
