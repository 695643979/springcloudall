package com.ping.eureka.client.orderservice.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.ping.eureka.client.orderservice.domain.ProductOrder;
import com.ping.eureka.client.orderservice.domain.UserVo;
import com.ping.eureka.client.orderservice.service.ProductClient;
import com.ping.eureka.client.orderservice.service.ProductOrderService;
import com.ping.eureka.client.orderservice.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Date;
import java.util.UUID;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;


    @Override
    public ProductOrder save(int userId, int productId) {

        // ribbon 调用成功
//        Object obj = restTemplate.getForObject("http://product-service/api/v1/product/findById?id="+productId, Object.class);
//        System.out.println(obj);

//        //feign  单个参数get方式调用成功
//        String response =  productClient.findById(productId);
//        System.out.println("response: " + response.toString());
//        JsonNode jsonNode = JsonUtils.str2JsonNode(response);


//        //feign 对象
        UserVo user = new UserVo();
        user.setId(1);
        user.setName("李白");
        user.setAge(18);
        String response = productClient.findByUserVo(user);
        System.out.println(response);
        JsonNode jsonNode = JsonUtils.str2JsonNode(response);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());

        productOrder.setProductName(jsonNode.get("name").toString());
        productOrder.setPrice(Integer.parseInt(jsonNode.get("price").toString()));

        return productOrder;
    }
}
