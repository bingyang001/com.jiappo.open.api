package com.jiappo.open.api.domain.event.handle;

import com.google.common.eventbus.Subscribe;
import com.hummer.common.utils.ObjectCopyUtils;
import com.jiappo.open.api.domain.event.OutMessageEvent;
import com.jiappo.open.api.domain.service.OutMessageService;
import com.jiappo.open.api.support.model.po.OutMessagePo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/15 14:21
 **/
@Component
@Slf4j
public class OutMessageEventHandle {
    @Autowired
    private OutMessageService outMessageService;
    @Subscribe
    public void appendOutMessageLog(final OutMessageEvent event) {
        outMessageService.save(ObjectCopyUtils.copy(event, OutMessagePo.class));
        log.info("out message event handle done,message id is {}",event);
    }
}
