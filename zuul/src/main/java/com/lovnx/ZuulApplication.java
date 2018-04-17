package com.lovnx;

import com.lovnx.filter.ErrorFilter;
import com.lovnx.filter.FirstFilter;
import com.lovnx.filter.ResultFilter;
import com.lovnx.filter.SecondFilter;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringCloudApplication
public class ZuulApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulApplication.class).web(true).run(args);
    }

    @Bean
    public FirstFilter firstFilter() {
        return new FirstFilter();
    }

    //@Bean
    public SecondFilter secondFilter() {
        return new SecondFilter();
    }

    @Bean
    public ResultFilter resultFilter() {
        return new ResultFilter();
    }

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }

}
