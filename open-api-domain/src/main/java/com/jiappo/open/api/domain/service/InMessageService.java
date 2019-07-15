package com.jiappo.open.api.domain.service;

import com.jiappo.open.api.dao.mapper.openapi.InMessagePoMapper;
import com.jiappo.open.api.support.model.po.InMessagePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/15 11:00
 **/
@Service
public class InMessageService {
    @Autowired
    private InMessagePoMapper inMessagePoMapper;

    public void save(InMessagePo inMessagePo) {
        inMessagePoMapper.save(inMessagePo);
    }
}
