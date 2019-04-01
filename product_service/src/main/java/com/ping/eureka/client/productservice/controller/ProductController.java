package com.ping.eureka.client.productservice.controller;

import com.ping.eureka.client.productservice.domain.UserVo;
import com.ping.eureka.client.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wenhuaping on 2018/12/10.
 * @author: wenhuaping
 */
@RefreshScope
@RestController
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductController {

    /**
     * 获取对应服务的端口
     */
    @Value("${server.port}")
    private String port;

    @Value("${env}")
    private String env;

//    /**
//     * 测试变量
//     */
//    @Value("${lisi}")
//    private String lisi;

    @Autowired
    private ProductService productService;

    /**
     * 获取所有商品列表
     * http://localhost:8771/api/v1/product/list
     * http://product-service/api/v1/product/list
     * @return
     */
    @RequestMapping("/list")
    public Object list(){
        return productService.listProduct();
    }

    /**
     * 根据id查找商品详情
     * http://localhost:8771/api/v1/product/findById?id=1
     * http://product-service/api/v1/product/findById?id=1
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public Object findById(@RequestParam("id") int id){
        log.info("调用产品服务product_service中端口为{}的服务！对应的环境env:{}", port, env);
        return productService.findById(id);
    }

    /**
     * 通过对象去查找
     * http://localhost:8771/api/v1/product/findByUserVo
     * http://product-service/api/v1/product/findByUserVo
     *
     * 参考博客：https://blog.csdn.net/u014281502/article/details/72896182
     *           https://www.jianshu.com/p/a9a7978f7bb8
     * @return
     */
    @RequestMapping("/findByUserVo")
    public Object findByUserVo(@RequestBody UserVo user){
        log.info("调用产品服务product_service中端口为{}的服务！测试变量为：{}", port);

        //测试Zipkin + Sleuth 超时情况，使用postman去测试： http://localhost:9000/apigateway/order/api/v1/order/save?userId=1&productId=1&token=sdfslkdklsd
//        try {
//            Thread.sleep(2500);
//        } catch (Exception e) {
//
//        }

//        //测试异常情况
//        int i = 1/0;

        return productService.findById(user.getId());
    }
}
