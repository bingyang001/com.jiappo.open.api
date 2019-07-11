package com.jiappo.open.api.dao.mapper.openapi;


import com.jiappo.open.api.support.model.po.TicketVerifiedRecordPo;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/27 17:30
 **/
public interface MessageTicketVerifiedResultPoMapper {
    /**
     * save verified result
     *
     * @param ticketRecordPo ticket po
     * @return com.jiappo.open.openapi.support.model.po.PlatformForwarderRoutePo
     * @author liguo
     * @date 2019/6/27 18:28
     * @version 1.0.0
     **/
    int saveVerifiedResult(@Param("ticketRecordPo") TicketVerifiedRecordPo ticketRecordPo);
}
