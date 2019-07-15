package com.jiappo.open.api.dao.mapper.openapi;

import com.jiappo.open.api.support.model.po.InMessagePo;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/15 10:18
 **/
public interface InMessagePoMapper {
    /**
     * save InMessagePo
     *
     * @param inMessagePo po
     * @return int
     * @author liguo
     * @date 2019/7/15 10:19
     * @since 1.0.0
     **/
    int save(@Param("inMessagePo") InMessagePo inMessagePo);
}
