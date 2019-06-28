package com.jiaappo.open.api.test.dao;

import com.jiaappo.open.api.test.BaseTest;
import com.jiappo.open.api.dao.mapper.openapi.PlatformForwarderRouteMapper;
import com.jiappo.open.api.support.model.po.PlatformForwarderRoutePo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/28 15:02
 **/
public class PlatformForwarderRouteMapperTest extends BaseTest {
    @Autowired
    private PlatformForwarderRouteMapper routeMapper;

    @Test
    public void queryRoute() {
        PlatformForwarderRoutePo po = routeMapper.queryRouteSingleBy("platform_A"
                , "create_user", 0);
        Assert.assertEquals("platform_A", po.getPlatformName());
    }
}
