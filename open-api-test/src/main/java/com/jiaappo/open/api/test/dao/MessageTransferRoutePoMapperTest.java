package com.jiaappo.open.api.test.dao;

import com.jiaappo.open.api.test.BaseTest;
import com.jiappo.open.api.dao.mapper.openapi.MessageTransferRoutePoMapper;
import com.jiappo.open.api.support.model.po.MessageTransferRoutePo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/28 15:02
 **/
public class MessageTransferRoutePoMapperTest extends BaseTest {
    @Autowired
    private MessageTransferRoutePoMapper routeMapper;

    @Test
    public void queryRoute() {
        MessageTransferRoutePo po = routeMapper.queryRouteSingleBy("platform_A"
                , "create_user", 0);
        Assert.assertEquals("platform_A", po.getPlatformName());
    }
}
