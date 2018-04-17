package com.lovnx.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Configuration
@RestController
public class ComputeController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private RestTemplate restTemplate;

    // 要通过服务名方式调用服务，必须加@LoadBalanced注解，否则只能通过url方式调用
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(value = "/**", method = RequestMethod.GET)
    public String add(@RequestParam Integer a, @RequestParam Integer b, HttpServletRequest request) {
        System.out.println(request.getRequestURL());
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
//        try {
//            Thread.sleep(50000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "From Service-B2, Result is " + r + "\nPort:" + instance.getPort();
    }

    //B服务调用A服务
    @RequestMapping(value = "testServiceA", method = RequestMethod.GET)
    public String testServiceB(@RequestParam Integer a, @RequestParam Integer b) {
        //RestTemplate restTemplate=new RestTemplate();
        return restTemplate.getForObject("http://service-A/add?a=" + a + "&b=" + b, String.class);
    }
}