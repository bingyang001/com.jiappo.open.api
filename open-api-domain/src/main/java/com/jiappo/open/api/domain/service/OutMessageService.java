package com.jiappo.open.api.domain.service;

import com.jiappo.open.api.dao.mapper.openapi.OutMessagePoMapper;
import com.jiappo.open.api.support.model.po.OutMessagePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/15 14:14
 **/
@Service
public class OutMessageService {
    @Autowired
    private OutMessagePoMapper outMessagePoMapper;

    public void save(OutMessagePo po) {
        outMessagePoMapper.save(po);
    }
}
