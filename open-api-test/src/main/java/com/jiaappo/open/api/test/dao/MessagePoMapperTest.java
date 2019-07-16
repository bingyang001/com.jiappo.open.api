package com.jiaappo.open.api.test.dao;

import com.hummer.common.security.Md5;
import com.jiaappo.open.api.test.BaseTest;
import com.jiappo.open.api.dao.mapper.openapi.MessageTicketPoMapper;
import com.jiappo.open.api.dao.mapper.openapi.MessageTransferRulePoMapper;
import com.jiappo.open.api.support.model.po.MessageRulePo;
import com.jiappo.open.api.support.model.po.MessageTicketRecordPo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/28 15:02
 **/
public class MessagePoMapperTest extends BaseTest {
    @Autowired
    private MessageTransferRulePoMapper routeMapper;
    @Autowired
    private MessageTicketPoMapper ticketPoMapper;

    @Test
    public void queryRoute() {
        MessageRulePo po = routeMapper.queryRouteSingleBy("platform_A"
                , "create_user", 0);
        Assert.assertEquals("platform_A", po.getMessageSource());
    }


    @Test
    public void queryTicket(){
        MessageTicketRecordPo recordPo = ticketPoMapper
                .querySingleTicket("edbdd0de1d591699283e332549ea08c8");
        System.out.println(recordPo.getDataBody().size());
    }
}
