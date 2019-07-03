package com.jiappo.open.api.domain.sign.impl;

import com.alibaba.fastjson.JSON;
import com.hummer.common.exceptions.AppException;
import com.hummer.common.security.Md5;
import com.jiappo.open.api.domain.sign.InMessageSign;
import com.jiappo.open.api.domain.sign.OutMessageSign;
import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import com.jiappo.open.api.support.model.po.MessageTransferRoutePo;
import com.jiappo.open.api.support.model.po.SignFieldPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * this class is default impl message verified md5 strategy
 *
 * @author bingy
 * @since 1.0.0
 */
public class DefaultMd5MessageSign implements InMessageSign, OutMessageSign {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMd5MessageSign.class);

    @Override
    public void verified(InMessageReq inMessageReq, SignFieldPo po) {

    }

    @Override
    public void decrypt(InMessageReq inMessageReq, SignFieldPo po) {

    }

    @Override
    public String createSign(InMessageReq inMessageReq, SignFieldPo po) {
        po.getFieldMap().putIfAbsent("_uuid", UUID.randomUUID().toString().replaceAll("-", ""));
        Set<String> keys = po.getFieldMap().keySet();
        Collection<Map<String, Object>> data = inMessageReq.getData();
        for (Map<String, Object> dataInfo : data) {
            for (String key : keys) {
                Object value = dataInfo.getOrDefault(key, null);
                if (value != null) {
                    po.getFieldMap().put(key, value);
                }
            }
        }
        String signJson = JSON.toJSONString(po.getFieldMap());
        String md5String = Md5.encryptMd5(signJson);
        LOGGER.info("create sign done ,md5 string is :{}", md5String);
        return md5String;
    }

    @Override
    public String encryption(InMessageReq inMessageReq, SignFieldPo po) {
        return null;
    }
}
