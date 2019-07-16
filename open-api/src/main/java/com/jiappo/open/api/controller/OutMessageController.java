package com.jiappo.open.api.controller;

import com.hummer.rest.model.ResourceResponse;
import com.hummer.rest.utils.ParameterAssertUtil;
import com.jiappo.open.api.facade.MessageFacade;
import com.jiappo.open.api.support.model.dto.outmessage.OutMessageReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

/**
 * send message to other platform name
 *
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/27 14:17
 **/
@RestController
@RequestMapping("/v1")
public class OutMessageController {
    @Autowired
    private MessageFacade messageFacade;

    @PostMapping(value = "/out_message/platform/{platform}/message_type/{messageType}")
    public ResourceResponse outMessage(@PathVariable("platform")
                                       @NotEmpty(message = "platform can not null.") String platform
        , @RequestBody @Validated OutMessageReq req
        , @PathVariable("messageType") @NotEmpty(message = "messageType can not null.") String messageType
        , Errors errors) {
        //verify request body
        ParameterAssertUtil.assertRequestValidated(errors);
        req.setMessageSource(platform);
        req.setMessageType(messageType);
        return ResourceResponse.ok(messageFacade.outMessage(req));
    }
}
