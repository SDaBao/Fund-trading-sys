package com.demo.server;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.demo.*;

import java.util.List;

@Mapper
public interface ProductDAOMapper {
    @Insert("INSERT INTO prdct_info(prdct_id,prdct_name,prdct_risk,prdct_comp,prdct_prfl,prdct_sale) " +
            "VALUES (#{prdct.prdct_id},#{prdct.prdct_name},#{prdct.prdct_risk},#{prdct.prdct_comp},#{prdct.prdct_prfl},#{prdct.prdct_sale})")
    void addProduct(@Param("prdct") Product prdct);

    @Update("update prdct_info " +
            "set prdct_name=#{product.prdct_name},prdct_comp=#{product.prdct_comp},prdct_risk=#{product.prdct_risk},prdct_prfl=#{product.prdct_prfl},prdct_sale=#{product.prdct_sale} " +
            "where prdct_id = #{product.prdct_id}")
	void updateProduct(@Param("product") Product product);

    @Update("update mrkt_info " +
            "set mrkt_time=#{nat.datetime},prdct_id=#{nat.prdct_id},prdct_val=#{nat.prdct_val}" +
            "where prdct_id = #{nat.prdct_id}")
    void updateNAT(@Param("nat") NetAssetValue nat);

    @Select("SELECT * from mrkt_info where prdct_id=#{prdct_id} order by mrkt_time desc limit 7")
    List<NetAssetValue> searchValue7Days(@Param("prdct_id")String prdct_id);

    @Select("SELECT * from mrkt_info where prdct_id=#{prdct_id} and mrkt_time LIKE '%' #{month} '%' order by mrkt_time desc" )
    List<NetAssetValue> searchValueMonth(@Param("prdct_id")String prdct_id, @Param("month")String monthTime);

    @Select("SELECT * from mrkt_info where prdct_id=#{prdct_id} order by mrkt_time desc limit 30")
    List<NetAssetValue> searchValue30Days(@Param("prdct_id")String prdct_id);

    @Select("SELECT * FROM prdct_info WHERE prdct_id LIKE '%' #{keyword} '%' OR prdct_name LIKE '%' #{keyword} '%'")
    List<Product> searchProduct(@Param("keyword")String keyword);

    @Select("SELECT * FROM prdct_info WHERE prdct_name LIKE '%' #{keyword} '%' OR prdct_comp LIKE '%' #{keyword} '%'")
    List<Product> searchProductByName(@Param("keyword")String keyword);
//	@Insert("INSERT INTO test.test_user(user_id,user_name,address) VALUES (#{user_id},#{user_name},#{address})")
//	void addUserByField(@Param("user_id")String user_id,@Param("user_name")String user_name,@Param("address")String address);
//
//    @Insert("INSERT INTO cstmr_info(cstmr_id,cstmr_name,cstmr_type,document_type,document_num) VALUES (#{user.cstmr_id},#{user.cstmr_name},#{user.cstmr_type},#{user.document_type},#{user.document_num})")
//    void addUser(@Param("user") User user);
//
//    @Insert("INSERT INTO card_info(cstmr_id,credit_id) VALUES (#{user.cstmr_id},#{card.credit_id})")
//    void addCard(@Param("user")User user, @Param("card") Card card);
//
//    @Select("SELECT * from cstmr_info where cstmr_id = #{cstmr_id}" )
//    User userInfo(@Param("cstmr_id")String cstmr_id);
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

