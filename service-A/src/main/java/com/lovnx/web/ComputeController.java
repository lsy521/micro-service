package com.lovnx.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Api("计算")
public class ComputeController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @ApiOperation(value = "加法", response = String.class)
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@ApiParam("参数a") @RequestParam Integer a, @ApiParam("参数b") @RequestParam Integer b) {
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return "From Service-A, Result is " + r;
    }

    //A服务调用B服务
    @ApiOperation(value = "b->a测试", response = String.class)
    @RequestMapping(value = "testServiceB", method = RequestMethod.GET)
    public String testServiceB(@RequestParam Integer a, @RequestParam Integer b) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:7076/add?a=" + a + "&b=" + b, String.class);
    }

}