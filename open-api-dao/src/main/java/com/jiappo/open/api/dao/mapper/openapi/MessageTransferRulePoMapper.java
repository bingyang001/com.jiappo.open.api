package com.jiappo.open.api.dao.mapper.openapi;


import com.jiappo.open.api.support.model.po.MessageRulePo;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/27 17:30
 **/
public interface MessageTransferRulePoMapper {
    /**
     * query message route
     *
     * @param messageSource   platform name
     * @param messageType      message type
     * @param messageRouteType route type . 0: in ;1: out
     * @return com.jiappo.open.openapi.support.model.po.PlatformForwarderRoutePo
     * @author liguo
     * @date 2019/6/27 18:28
     * @version 1.0.0
     **/
    MessageRulePo queryRouteSingleBy(@Param("messageSource") String messageSource
            , @Param("messageType") String messageType
            , @Param("messageRouteType") Integer messageRouteType);
}
