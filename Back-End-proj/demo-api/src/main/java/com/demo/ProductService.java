package com.demo;

import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.time.LocalDate;

@CloudService
public interface ProductService {
    String addProduct(Product product);
    String updateProduct(Product product);
    String searchProduct(String data, int search_type);
    String updateValue(NetAssetValue value);
    String searchValue(String prdct_id, String date, int search_type);

}