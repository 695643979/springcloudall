package com.ping.eureka.client.orderservice.service;

import com.ping.eureka.client.orderservice.domain.UserVo;
import com.ping.eureka.client.orderservice.fallback.ProductClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2018/12/11.
 * @author: wenhuaping
 */
@FeignClient(name = "product-service", fallback = ProductClientFallback.class)
public interface ProductClient {

    @GetMapping("/api/v1/product/findById")
    String findById(@RequestParam("id") int id);

    /**
     *
     * @PostMapping("/api/v1/product/findByUser")
     * @RequestMapping(value = "/user", method = RequestMethod.GET,consumer="application/json")
     * @param user
     * @return
     */
//    @GetMapping("/api/v1/product/findByUserVo")
    @PostMapping("/api/v1/product/findByUserVo")
    String findByUserVo(@RequestBody UserVo user);

}
