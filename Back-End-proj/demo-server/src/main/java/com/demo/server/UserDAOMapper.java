package com.demo.server;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.demo.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface UserDAOMapper {
	
//	@Insert("INSERT INTO test.test_user(user_id,user_name,address) VALUES (#{user_id},#{user_name},#{address})")
//	void addUserByField(@Param("user_id")String user_id,@Param("user_name")String user_name,@Param("address")String address);

	@Insert("INSERT INTO cstmr_info(cstmr_id,cstmr_name,cstmr_type,document_type,document_num) VALUES (#{user.cstmr_id},#{user.cstmr_name},#{user.cstmr_type},#{user.document_type},#{user.document_num})")
	void addUser(@Param("user")User user);

	@Insert("INSERT INTO card_info(cstmr_id,credit_id) VALUES (#{user.cstmr_id},#{card.credit_id})")
	void addCard(@Param("user")User user, @Param("card")Card card);

	@Delete("delete from card_info where cstmr_id=#{user.cstmr_id} and credit_id=#{card.credit_id}")
	void deleteCard(@Param("user")User user, @Param("card")Card card);

	@Select("SELECT * from cstmr_info where cstmr_id = #{cstmr_id}" )
	User userInfo(@Param("cstmr_id")String cstmr_id);

	@Select("SELECT password from login where name = #{name}" )
	String checkPasswd(@Param("name")String name);

	@Select("SELECT * FROM cstmr_info WHERE cstmr_name LIKE '%' #{keyword} '%' OR document_num LIKE '%' #{keyword} '%'")
	List<User> userSearch(@Param("keyword")String keyword);

	@Select("SELECT * from card_info WHERE cstmr_id=#{cstmr_id}")
	List<Card> userFund(@Param("cstmr_id")String cstmr_id);

	@Select("SELECT pi.prdct_id as prdct_id,prdct_shares,prdct_name "
					+"FROM shares_info LEFT JOIN prdct_info pi on shares_info.prdct_id = pi.prdct_id "
					+"WHERE cstmr_id=#{cstmr_id}")
	List<Share> userOwn(@Param("cstmr_id")String cstmr_id);

	@Update("UPDATE cstmr_info SET risk_rating=#{risk_rating} where cstmr_id=#{cstmr_id}")
	void userRisk(@Param("cstmr_id")String cstmr_id, @Param("risk_rating")int risk_rating);

	@Update("UPDATE cstmr_info SET account_balance=account_balance+#{trade_value}*(#{trade_type}) WHERE cstmr_id=#{cstmr_id}")
	void addAccountBalance(@Param("cstmr_id")String cstmr_id,
						   @Param("trade_value")BigDecimal trade_value,
						   @Param("trade_type")int sign);

	@Update("UPDATE card_info SET credit_balance=credit_balance+#{trade_value}*(#{trade_type}) WHERE credit_id=#{credit_id}")
	void addCreditBalance(@Param("credit_id")String credit_id,
						  @Param("trade_value")BigDecimal trade_value,
						  @Param("trade_type")int sign);

	@Update("UPDATE cstmr_info SET cstmr_name = #{user.cstmr_name} , cstmr_type = #{user.cstmr_type} , document_type = #{user.document_type} , document_num = #{user.document_num} , risk_rating = #{user.risk_rating} WHERE cstmr_id = #{user.cstmr_id}")
	void userUpdate(@Param("user")User user);
	//	@Select("select user_id as user_id, "
//			+ "user_name as user_name,"
//			+ "address from test_user where user_id = #{user_id}")
//	User getUser(@Param("user_id")int userId);
//
//	@Delete("delete from test_user where where user_id = #{user_id}")
//	void deleteUser(@Param("user_id")int user_id);
//
//	@Update("update test_user set user_name = #{user.user_name}, address = #{user.address} where user_id = #{user.user_id}")
//	void updateUser(@Param("user")User user);
}
