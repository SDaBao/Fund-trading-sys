package com.demo.server;

import com.demo.Trade;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface TradeDAOMapper {
    @Insert("INSERT INTO deal_info(deal_id,deal_type,deal_time,deal_status,prdct_id,cstmr_id,trans_val) VALUES (#{trade.deal_id},0,#{trade.deal_time},0,#{trade.prdct_id},#{trade.cstmr_id},#{trade.trans_val})")
    void addBuyHistory(@Param("trade") Trade trade);//申购向deal_info中加入

    @Insert("INSERT INTO deal_info(deal_id,deal_type,deal_time,deal_status,prdct_id,cstmr_id,trans_share) VALUES (#{trade.deal_id},1,#{trade.deal_time},0,#{trade.prdct_id},#{trade.cstmr_id},#{trade.trans_share})")
    void addSaleHistory(@Param("trade") Trade trade);//赎回向deal_info中加入

    @Update("UPDATE cstmr_info SET account_balance = account_balance - #{trade.trans_val} WHERE cstmr_id=#{trade.cstmr_id}")
    void updateAccountBalance(@Param("trade") Trade trade);//申购要去用户账户余额中扣除相应的钱

    @Update("UPDATE shares_info SET prdct_shares = prdct_shares - #{trade.trans_share} WHERE cstmr_id = #{trade.cstmr_id} AND prdct_id = #{trade.prdct_id}")
    void updateUserOwn(@Param("trade") Trade trade);//用户持仓修改

    @Select("SELECT prdct_shares FROM shares_info WHERE cstmr_id = #{trade.cstmr_id} AND prdct_id = #{trade.prdct_id}")
    Double getShares(@Param("trade") Trade trade);//查找修改后持有份额

    @Delete("DELETE FROM shares_info WHERE cstmr_id = #{trade.cstmr_id} AND prdct_id = #{trade.prdct_id}")
    void deleteShares(@Param("trade") Trade trade);//若份额为0，执行此删除操作

    @Insert("INSERT INTO shares_info(cstmr_id,prdct_id,prdct_shares) VALUES (#{trade.cstmr_id},#{trade.prdct_id},#{trade.trans_share})")
    void insertShares(@Param("trade") Trade trade);//取消赎回时，用户持仓修改

    @Update("UPDATE deal_info SET deal_status = 2 WHERE deal_id = #{deal_id}")
    void tradeCancel(@Param("deal_id")String deal_id);//取消订单修改交易流水

    @Select("SELECT prdct_shares FROM shares_info WHERE cstmr_id = #{trade.cstmr_id} AND prdct_id = #{trade.prdct_id}")
    String confirmShares(@Param("trade") Trade trade);//查找修改后持有份额

    @Select("SELECT deal_type FROM deal_info WHERE deal_id = #{deal_id}")
    int getDealType(@Param("deal_id")String deal_id);//通过订单号获取交易类型

    @Select("SELECT deal_status FROM deal_info WHERE deal_id = #{deal_id}")
    int getDealStatus(@Param("deal_id")String deal_id);//通过订单号获取交易状态

    @Update("UPDATE cstmr_info SET account_balance = account_balance + #{trade.trans_val} WHERE cstmr_id=#{trade.cstmr_id}")
    void buyCancel(@Param("trade") Trade trade);//取消申购退还钱

    @Update("UPDATE shares_info SET prdct_shares = prdct_shares + #{trade.trans_share} WHERE cstmr_id = #{trade.cstmr_id} AND prdct_id = #{trade.prdct_id}")
    void saleCancel(@Param("trade") Trade trade);//赎回取消修改持仓

    @Select("SELECT d.*, p.prdct_name, c.cstmr_name FROM deal_info AS d, prdct_info AS p, cstmr_info AS c WHERE d.cstmr_id = c.cstmr_id AND d.prdct_id = p.prdct_id AND d.cstmr_id = #{cstmr_id} ")
    List<Trade> tradeHistory(@Param("cstmr_id")String cstmr_id);//查询用户交易流水

    @Select("SELECT * FROM deal_info WHERE deal_id = #{deal_id}")
    Trade getTradeInfo(@Param("deal_id")String deal_id);

    @Select("SELECT d.*, p.prdct_name, c.cstmr_name FROM deal_info AS d, prdct_info AS p, cstmr_info AS c WHERE d.cstmr_id = c.cstmr_id AND d.prdct_id = p.prdct_id ")
    List<Trade> tradeAllHistory();//查询交易流水

    @Select("SELECT * FROM deal_info WHERE deal_id LIKE '%' #{keyword} '%'")
    List<Trade> tradeSearch(@Param("keyword")String keyword);
}
