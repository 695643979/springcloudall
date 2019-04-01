package com.ping.eureka.client.orderservice.fallback;

import com.ping.eureka.client.orderservice.domain.UserVo;
import com.ping.eureka.client.orderservice.service.ProductClient;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/12/12.
 * @author: wenhuaping
 */
@Component
public class ProductClientFallback implements ProductClient {
    @Override
    public String findById(int id) {
        System.out.println("调用product-service服务异常！方法：findById(int id)");
        return null;
    }

    @Override
    public String findByUserVo(UserVo user) {
        System.out.println("product-service服务异常，进行熔断降级处理！方法：findByUserVo(UserVo user)");
        return null;
    }
}
