package com.jiappo.open.api.dao.mapper.openapi;


import com.jiappo.open.api.support.model.po.MessageTicketRecordPo;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/27 17:30
 **/
public interface MessageTicketPoMapper {
    /**
     * query message route
     *
     * @param ticketRecordPo ticket po
     * @return com.jiappo.open.openapi.support.model.po.PlatformForwarderRoutePo
     * @author liguo
     * @date 2019/6/27 18:28
     * @version 1.0.0
     **/
    int saveTicket(@Param("ticketRecordPo") MessageTicketRecordPo ticketRecordPo);

    /**
     * save verified result
     *
     * @param ticketRecordPo po
     * @return int
     * @author liguo
     * @date 2019/7/11 15:19
     * @since 1.0.0
     **/
    int saveNewTicketVerifiedResult(@Param("ticketRecordPo") MessageTicketRecordPo ticketRecordPo);

    /**
     * query db ticket
     *
     * @param ticketMd5Value ticket md5 value
     * @return com.jiappo.open.api.support.model.po.MessageTicketRecordPo
     * @author liguo
     * @date 2019/7/11 9:50
     * @since 1.0.0
     **/
    MessageTicketRecordPo querySingleTicket(@Param("ticketMd5Value") String ticketMd5Value);

    /**
     * update  ticket verified result
     *
     * @param ticketRecordPo po
     * @return int
     * @author liguo
     * @date 2019/7/11 10:10
     * @since 1.0.0
     **/
    int setTicketVerifiedResult(@Param("ticketRecordPo") MessageTicketRecordPo ticketRecordPo);
}
