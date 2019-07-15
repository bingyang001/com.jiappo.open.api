package com.jiappo.open.api.controller;

import com.hummer.rest.model.ResourceResponse;
import com.hummer.rest.utils.ParameterAssertUtil;
import com.jiappo.open.api.facade.MessageFacade;
import com.jiappo.open.api.support.model.dto.in.InMessageReq;
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
 * ticket
 *
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/10 16:45
 **/
@RestController
@RequestMapping(value = "/v1")
public class TicketController {
    @Autowired
    private MessageFacade inMessageFacade;

    @PostMapping(value = "/new_ticket/platform/{platform}/message_type/{messageType}")
    public ResourceResponse<String> newTicket(@PathVariable("platform")
                                              @NotEmpty(message = "platform can not null.") String platform
        , @PathVariable("messageType") String messageType
        , @RequestBody @Validated InMessageReq req
        , Errors errors) {
        //verify request body
        ParameterAssertUtil.assertRequestValidated(errors);
        //settings  platform name
        req.setPlatformName(platform);
        req.setMessageType(messageType);
        return ResourceResponse.ok(inMessageFacade.newTicket(req));
    }
}
