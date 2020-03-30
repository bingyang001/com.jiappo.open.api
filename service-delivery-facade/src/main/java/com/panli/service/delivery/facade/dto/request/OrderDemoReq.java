package com.panli.service.delivery.facade.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author bingy
 */
@Data
public class OrderDemoReq extends BaseReq implements Serializable {
    private static final long serialVersionUID = -5160012366301986546L;
    private int orderId;
    @NotEmpty(message = "title can't empty.")
    private String orderTitle;
    private int userId;
}
