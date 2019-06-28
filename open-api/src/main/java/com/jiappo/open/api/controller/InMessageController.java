package com.jiappo.open.api.controller;

import com.hummer.rest.model.ResourceResponse;
import com.hummer.rest.utils.ParameterAssertUtil;
import com.jiappo.open.api.application.facade.InMessageFacade;
import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;


/**
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/27 14:17
 **/
@RestController
@RequestMapping(value = "/v1")
public class InMessageController {
    @Autowired
    private InMessageFacade inMessageFacade;

    @PostMapping(value = "/in_message/{platform}")
    public ResourceResponse createNewUser(@PathVariable("platform")
                                          @NotEmpty(message = "platform can not null.") String platform
        , @RequestBody @Validated InMessageReq req
        , @RequestHeader(name = "secretKey") @NotEmpty(message = "secret key can not null.") String secretKey
        , Errors errors) {
        //verify request body
        ParameterAssertUtil.assertRequestValidated(errors);
        //settings  platform name
        req.setPlatformName(platform);
        req.setSecretKey(secretKey);
        //call business
        return ResourceResponse.ok(inMessageFacade.inMessage(req));
    }
}
