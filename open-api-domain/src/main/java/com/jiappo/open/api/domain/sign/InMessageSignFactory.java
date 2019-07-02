package com.jiappo.open.api.domain.sign;

import com.google.common.collect.Sets;
import com.hummer.common.exceptions.AppException;

import java.util.Set;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/2 19:04
 **/
public class InMessageSignFactory {
    private static final Set<String> SING_HASH_SET = Sets.newHashSetWithExpectedSize(3);

    private InMessageSignFactory() {

    }

    public static InMessageSign factory(String signType) {
        if (!SING_HASH_SET.contains(signType)) {
            throw new AppException(50001, String.format("sign type %s invalid", signType));
        }
    }
}
