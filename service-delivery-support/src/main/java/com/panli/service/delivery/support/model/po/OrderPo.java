package com.panli.service.delivery.support.model.po;

import lombok.Data;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/11/1 17:53
 **/
@Data
public class OrderPo extends BasePo {
    private int orderId;
    private String orderTitle;
    private int userId;
}
