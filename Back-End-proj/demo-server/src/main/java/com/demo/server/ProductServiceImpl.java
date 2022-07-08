package com.demo.server;

import com.demo.*;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CloudComponent
public class ProductServiceImpl implements ProductService {

    @Autowired(required = false)
    private ProductDAOMapper productDAO;
//    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static ObjectMapper mapper = new ObjectMapper();
    private Map<Integer, User> userMap = new HashMap<Integer, User>();

    public static String toJson(Object obj){
        String json = null;
        try {
            StringWriter writer = new StringWriter();
            JsonGenerator generator = mapper.getFactory().createGenerator(writer);
            mapper.writeValue(generator, obj);
            json = writer.toString();
            generator.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

    public String addProduct(Product product) {
        try {
            productDAO.addProduct(product);
        } catch (Exception e){
            e.printStackTrace();
            return "DataBase ERR";
        }
        return product.getPrdct_id() + "产品添加成功";
    }
//    public String addCard(User user, Card card) {
//        userDao.addCard(user,card);
//        return "绑定银行卡成功，用户id是：" + user.cstmr_id
//                + "\n银行卡号是：" + card.credit_id;
//    }

    public String updateProduct(Product product) {
        try {
            productDAO.updateProduct(product);
        } catch (Exception e){
            e.printStackTrace();
            return "DataBase ERR";
        }
        return product.getPrdct_id() + "产品修改成功";
    }

    public String searchProduct(String keyWord, int search_type) {
        String esc = "";
        if (search_type == 1){
            try {
                System.out.println(keyWord);
                List<Product> productList = productDAO.searchProduct(keyWord);
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("n", productList.size());
                map.put("product_info", productList);
//        System.out.println(map);
                return toJson(map);
            } catch (Exception e){
                e.printStackTrace();
            }
        } else if(search_type == 2) {
            try {
                System.out.println(keyWord);
                List<Product> productList = productDAO.searchProductByName(keyWord);
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("n", productList.size());
                map.put("product_info", productList);
//        System.out.println(map);
                return toJson(map);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("n", 0);
        map.put("product_info", null);
        return toJson(map);
    }

    public String updateValue(NetAssetValue value) {
        try {
            productDAO.updateNAT(value);
        } catch (Exception e){
            e.printStackTrace();
            return "DataBase ERR";
        }
        return "val updated";
    }

    public String searchValue(String prdct_id, String date, int search_type) {
//        String date = fmt.format(date);
        if(!date.matches("^\\d{4}-\\d{2}-\\d{2}")){
            return "err datetime";
        }
        if (search_type == 1){
            System.out.println(date.substring(0,7));
            try {
                List<NetAssetValue> natList = productDAO.searchValueMonth(prdct_id,date.substring(0,7));
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("n", natList.size());
                map.put("valueInfo", natList);
                System.out.println(map);
                return toJson(map);
            } catch (Exception e){
                e.printStackTrace();
            }
        } else if(search_type == 2) {
            try {
                List<NetAssetValue> natList = productDAO.searchValue7Days(prdct_id);
                System.out.println(natList);
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("n", natList.size());
                map.put("valueInfo", natList);
                System.out.println(map);
                return toJson(map);
            } catch (Exception e){
                e.printStackTrace();
            }
        } else if(search_type == 3) {
            try {
                System.out.println(date.substring(0,4));
                List<NetAssetValue> natList = productDAO.searchValue30Days(prdct_id);
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("n", natList.size());
                map.put("valueInfo", natList);
                System.out.println(map);
                return toJson(map);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("n", 0);
        map.put("valueInfo", null);
        System.out.println(map);
        return toJson(map);
    }


}
