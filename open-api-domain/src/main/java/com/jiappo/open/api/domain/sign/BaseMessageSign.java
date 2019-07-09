package com.jiappo.open.api.domain.sign;

import com.google.common.collect.Maps;
import com.hummer.common.utils.DateUtil;
import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import com.jiappo.open.api.support.model.po.SignFieldPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * impl sign field builder
 *
 * @author bingy
 * @since 1.0.0
 */
public abstract class BaseMessageSign implements InMessageSign, OutMessageSign {

    private static final Logger LOGGER= LoggerFactory.getLogger(BaseMessageSign.class);

    protected void appendToLog(InMessageReq req
            , SignFieldPo po
            , String originValue) {
        LOGGER.info("");
    }

    /**
     * builder will sign field map
     *
     * @param inMessageReq          message
     * @param po                    file route
     * @param appendUUIDField       if true then append uuid to map
     * @param appendSignExpiredTime if true append sign expired time to map
     * @return
     */
    protected Map<String, Object> buildSignField(InMessageReq inMessageReq
            , SignFieldPo po
            , boolean appendUUIDField
            , boolean appendSignExpiredTime) {
        Map<String, Object> fieldMap = Maps.newHashMapWithExpectedSize(16);
        if (appendUUIDField) {
            fieldMap.put("_uuid_", UUID.randomUUID().toString().replaceAll("-", ""));
        }
        if (appendSignExpiredTime) {
            // TODO: 2019/7/4 add to minute  'po.getExpiredMinute()'
            fieldMap.put("_expired_", DateUtil.formatNowData("yyyy-MM-dd HH:mm:ss"));
        }
        Set<String> keys = po.getFieldMap().keySet();
        Collection<Map<String, Object>> data = inMessageReq.getData();
        for (Map<String, Object> dataInfo : data) {
            for (String key : keys) {
                Object value = dataInfo.getOrDefault(key, null);
                if (value != null) {
                    fieldMap.put(key, value);
                }
            }
        }

        return fieldMap;
    }
}
