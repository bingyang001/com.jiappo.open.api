package com.jiappo.open.api.domain.eventbus;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/10 14:55
 **/
@Component
public class InMessageEventHandle {
    private static final Logger LOGGER = LoggerFactory.getLogger(InMessageEventHandle.class);

    @Subscribe
    public void handle(VerifiedSignEvent event) {
        LOGGER.info("begin handle verified event {}", event);
    }
}
