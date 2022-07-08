package com.demo;

import com.hundsun.jrescloud.rpc.annotation.CloudService;
import org.apache.kafka.common.protocol.types.Field;

import java.math.BigDecimal;

@CloudService
public interface UserService {
	
	String addUser(User user);
	String addCard(User user, Card card, int op_type);
	String userInfo(String cstmr_id);
	String userSearch(String keyWord);
	String userFund(String cstmr_id);
	String userOwn(String cstmr_id);
	String userRisk(String cstmr_id, int risk_rating);
	String bankTrade(String credit_id, String cstmr_id, int trade_type, BigDecimal trade_value);
	String userUpdate(User user);
	String adminLogin(String name, String passwd);
//	User getUser(int user_id);

}
