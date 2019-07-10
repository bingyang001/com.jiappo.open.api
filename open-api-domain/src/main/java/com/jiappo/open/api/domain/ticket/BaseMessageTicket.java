package com.jiappo.open.api.domain.ticket;

import com.google.common.collect.Maps;
import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import com.jiappo.open.api.support.model.bo.SignFieldBo;
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
public abstract class BaseMessageTicket implements InMessageSign, OutMessageSign {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseMessageTicket.class);

    /**
     * builder will sign field map
     *
     * @param inMessageReq          message
     * @param po                    file route
     * @param appendUUIDField       if true then append uuid to map
     * @param appendSignExpiredTime if true then append sign expired time to map
     * @return
     */
    protected Map<String, Object> buildSignField(InMessageReq inMessageReq
            , SignFieldBo po
            , boolean appendUUIDField
            , boolean appendSignExpiredTime) {
        Map<String, Object> fieldMap = Maps.newHashMapWithExpectedSize(16);
        if (appendUUIDField) {
            fieldMap.put("_uuid_", UUID.randomUUID().toString().replaceAll("-", ""));
        }

        if (appendSignExpiredTime) {
            fieldMap.put("_expired_", po.getExpiredMinute());
            LOGGER.info("message {} ,now time is {} after {} expired"
                    , inMessageReq.getMessageType()
                    , po.getExpiredMinute());
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
