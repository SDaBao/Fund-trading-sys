package com.client;

import com.demo.NetAssetValue;
import com.demo.Product;
import com.demo.ProductService;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.apache.el.parser.BooleanNode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class ProductClientController {

//    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @CloudReference
    private ProductService productService;

    @RequestMapping(value = "/fund/add", method = RequestMethod.GET)
    public String addProduct(@RequestParam("prdct_id")String prdct_id,
                             @RequestParam("prdct_name")String prdct_name,
                             @RequestParam(value = "prdct_comp", required = false)String prdct_comp,
                             @RequestParam(value = "prdct_risk", defaultValue = "0")String prdct_risk,
                             @RequestParam(value = "prdct_prfl", required = false)String prdct_prfl,
                             @RequestParam(value = "prdct_sale", defaultValue = "false")String prdct_sale
                             ){
        Product product = new Product();
        product.setPrdct_id(prdct_id);
        product.setPrdct_name(prdct_name);
        product.setPrdct_comp(prdct_comp);
        product.setPrdct_prfl(prdct_prfl);
        product.setPrdct_risk(Integer.valueOf(prdct_risk));
        product.setPrdct_sale(Boolean.valueOf(prdct_sale));
        return productService.addProduct(product);
    }


    @RequestMapping(value = "/fund/update", method = RequestMethod.GET)
    public String updateProduct(@RequestParam("prdct_id")String prdct_id,
                             @RequestParam(value = "prdct_name")String prdct_name,
                             @RequestParam(value = "prdct_comp")String prdct_comp,
                             @RequestParam(value = "prdct_risk")String prdct_risk,
                             @RequestParam(value = "prdct_prfl")String prdct_prfl,
                             @RequestParam(value = "prdct_sale")String prdct_sale
    ){
        Product product = new Product();
        product.setPrdct_id(prdct_id);
        product.setPrdct_name(prdct_name);
        product.setPrdct_comp(prdct_comp);
        product.setPrdct_prfl(prdct_prfl);
        product.setPrdct_risk(Integer.valueOf(prdct_risk));
        product.setPrdct_sale(Boolean.valueOf(prdct_sale));
        return productService.updateProduct(product);
    }


    @RequestMapping(value = "/fund/search", method = RequestMethod.GET)
    public String searchProduct(@RequestParam("keyWord")String keyWord,
                             @RequestParam(value = "op_type", defaultValue = "1")String search_type
    ){
        return productService.searchProduct(keyWord, Integer.valueOf(search_type));
    }


    @RequestMapping(value = "/fund/value_update", method = RequestMethod.GET)
    public String updateValue(@RequestParam("prdct_id")String prdct_id,
                              @RequestParam("prdct_val")String prdct_val,
                              @RequestParam("datetime")String datetime
    ){
        NetAssetValue value = new NetAssetValue();
        value.setPrdct_id(prdct_id);
        BigDecimal bString = new BigDecimal(prdct_val);
        value.setPrdct_val(bString);
        value.setDatetime(datetime);
        return productService.updateValue(value);
    }


    @RequestMapping(value = "/fund/value_search", method = RequestMethod.GET)
    public String searchValue(@RequestParam("prdct_id")String prdct_id,
                              @RequestParam("datetime")String datetime,
                              @RequestParam(value = "search_type", defaultValue = "1")String search_type
    ){
        return productService.searchValue(prdct_id, datetime, Integer.valueOf(search_type));
    }


}
