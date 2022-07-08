package com.demo.server;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.*;
import com.demo.UserService;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;

@CloudComponent
public class UserServiceImpl implements UserService {

    public static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object obj) {
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

    private Map<Integer, User> userMap = new HashMap<Integer, User>();

    @Autowired(required = false)
    private UserDAOMapper userDao;

    public String addUser(User user) {


//		int userId = user.cstmr_id;
//		user.cstmr_id = t;
//		if(getUser(userId) != null) {
////
//			userDao.updateUser(user);
//			return "更新用户成功";
//		}else {
//
//			userDao.addUser(userId+"",user.getUser_name(),user.getAddress());
//			System.out.println(user);
        userDao.addUser(user);
        System.out.println(user);
        return "添加用户成功，用户id是：" + user.cstmr_id
                + "\n用户姓名是：" + user.cstmr_name;
//		}
    }

    public String addCard(User user, Card card, int op_type) {
        if(op_type==1){
            userDao.addCard(user, card);
            return "绑定银行卡成功\n银行卡号：" + card.credit_id;
        } else if(op_type==2){
            userDao.deleteCard(user, card);
            return "银行卡解绑成功\n银行卡号：" + card.credit_id;
        }
        return "false";
    }


    public String userInfo(String cstmr_id) {
        //userDao.userInfo(user);
//        System.out.println(cstmr_id);
//        System.out.println(toJson(userDao.userInfo(cstmr_id)));
        return toJson(userDao.userInfo(cstmr_id));
    }

    public String userSearch(String keyWord) {
        System.out.println(keyWord);
        List<User> userList = userDao.userSearch(keyWord);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("n", userList.size());
        map.put("user_info", userList);
//        System.out.println(map);
        return toJson(map);
    }

    public String userFund(String cstmr_id) {
        User user = userDao.userInfo(cstmr_id);
        List<Card> cardList = userDao.userFund(cstmr_id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("account_balance", user.getAccount_balance());
        map.put("n", cardList.size());
        map.put("card_info", cardList);
//        System.out.println(map);
        return toJson(map);
    }

    public String userOwn(String cstmr_id) {
        User user = userDao.userInfo(cstmr_id);
        List<Share> shareList = userDao.userOwn(cstmr_id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("n", shareList.size());
        map.put("product_info", shareList);
//        System.out.println(map);
        return toJson(map);
    }

    public String userRisk(String cstmr_id, int risk_rating) {
        userDao.userRisk(cstmr_id, risk_rating);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("error_code", "200");
        map.put("result", "success");
//        System.out.println(map);
        return toJson(map);
    }

    public String bankTrade(String credit_id, String cstmr_id, int trade_type, BigDecimal trade_value) {
        List<Card> cardList = userDao.userFund(cstmr_id);
        User user = userDao.userInfo(cstmr_id);
        boolean flag = false;
        boolean success = true;
        BigDecimal credit_balance = null;
        for (Card card : cardList) {
            System.out.println(card.credit_id);
            if (card.credit_id.equals(credit_id)) {
                flag = true;
                credit_balance = new BigDecimal(card.credit_balance);
                break;
            }
        }
        System.out.println(credit_balance);
        if (flag) {
            if (trade_type == 1) {//充值
                if (trade_value.compareTo(credit_balance) != 1) {
                    //trade_value小于等于credit_balance
                    userDao.addAccountBalance(cstmr_id, trade_value, 1);
                    userDao.addCreditBalance(credit_id, trade_value, -1);
                } else {
                    success = false;
                }
            } else if (trade_type == 2) {//提现
                if (trade_value.compareTo(user.account_balance) != 1) {
                    //trade_value小于等于account_balance
                    userDao.addAccountBalance(cstmr_id, trade_value, -1);
                    userDao.addCreditBalance(credit_id, trade_value, 1);
                } else {
                    success = false;
                }
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if(success && flag){
            map.put("error_code", "200");
            map.put("result", "success");
        }else{
            map.put("error_code", "500");
            map.put("result", "failed");
        }
//        System.out.println(map);
        return toJson(map);
    }

    @Override
    public String userUpdate(User user) {
        userDao.userUpdate(user);
        return "用户信息修改成功";
    }


    public String adminLogin(String name, String passwd){
        String tempPassWD = userDao.checkPasswd(name);
        if(tempPassWD.equals(passwd)){
            return "true";
        }
        return "false";
    }
//	public User getUser(int user_id) {
//		return null;
//	}

//	public User getUser(int user_id) {
//		return userDao.getUser(user_id);
//	}

}
