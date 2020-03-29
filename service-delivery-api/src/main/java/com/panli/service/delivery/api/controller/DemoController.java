package com.panli.service.delivery.api.controller;

import com.hummer.rest.annotations.BindRestParameterSimpleModel;
import com.hummer.rest.model.ResourceResponse;
import com.hummer.rest.utils.ParameterAssertUtil;
import com.panli.service.delivery.facade.dto.request.BindSimpleModelDemoReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * demo api
 *
 * @author bingy
 */
@RestController
@RequestMapping("/v1")
@Api(value = "com.panli rest api demo")
@Validated
public class DemoController {

    @GetMapping("/demo/bind-simple-model")
    @ApiOperation(value = "demo bind simple model")
    public ResourceResponse<BindSimpleModelDemoReq> bindSimpleModelDemo(@BindRestParameterSimpleModel @Valid
                                                                            BindSimpleModelDemoReq req
        , Errors errors) {
        ParameterAssertUtil.assertRequestFristValidated(errors);
        return ResourceResponse.ok(req);
    }

    @GetMapping(value = "/demo/query-valid")
    @ApiOperation(value = "with query param valid")
    public ResourceResponse<Map<Object, Object>> queryWithValid(@RequestParam
                                                                @NotEmpty(message = "uuId can't null")
                                                                    String uuId,
                                                                @RequestParam
                                                                @NotNull(message = "class id can't null")
                                                                    Integer classId,
                                                                @RequestParam
                                                                @NotNull(message = "class id can't null")
                                                                    Collection<String> users
        , Errors errors
    ) {
        ParameterAssertUtil.assertRequestFristValidated(errors);
        Map<Object, Object> map = new HashMap<>(3);
        map.put("uuId", uuId);
        map.put("classId", classId);
        map.put("users", users);
        return ResourceResponse.ok(map);
    }

}
