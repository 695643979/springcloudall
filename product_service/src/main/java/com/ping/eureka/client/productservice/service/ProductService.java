package com.ping.eureka.client.productservice.service;


/**
 * Created by Administrator on 2018/12/10.
 * @author: wenhuaping
 */
public interface ProductService {

    Object listProduct();

    Object findById(int id);
}
