package com.panli.service.delivery.domain.facade;

import com.hummer.common.utils.ObjectCopyUtils;
import com.panli.service.delivery.dao.OrderDao;
import com.panli.service.delivery.facade.OrderDemoFacade;
import com.panli.service.delivery.facade.dto.request.OrderDemoReq;
import com.panli.service.delivery.support.model.po.OrderPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderDemoFacadeImpl implements OrderDemoFacade {
    @Autowired
    private OrderDao orderDao;

    /**
     * save new order to db
     *
     * @param req
     */
    @Override
    public void saveNewOrder(OrderDemoReq req) {
        orderDao.save(ObjectCopyUtils.copy(req, OrderPo.class));
        log.debug("order save to db done,{}", req);
    }
}
