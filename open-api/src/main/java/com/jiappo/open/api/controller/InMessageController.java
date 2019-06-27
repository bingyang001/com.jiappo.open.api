package com.jiappo.open.api.controller;

import com.hummer.rest.model.ResourceResponse;
import com.jiappo.open.api.support.model.dto.in.InNewUserMessageReq;
import com.jiappo.open.api.support.utils.AssertBizUtil;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/27 14:17
 **/
@RestController
@RequestMapping(value = "/v1")
public class InMessageController {

    @PostMapping(value = "/new_user_message")
    public ResourceResponse createNewUser(@RequestBody @Validated InNewUserMessageReq req
        , Errors errors ){
        AssertBizUtil.assertRequestValidated(errors);

        return ResourceResponse.ok();
    }
}
