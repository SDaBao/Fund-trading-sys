package com.demo.server;

import com.demo.Trade;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface TradeDAOMapper {
    @Insert("INSERT INTO deal_info(deal_id,deal_type,deal_time,deal_status,prdct_id,cstmr_id,trans_val) VALUES (#{trade.deal_id},0,#{trade.deal_time},0,#{trade.prdct_id},#{trade.cstmr_id},#{trade.trans_val})")
    void addBuyHistory(@Param("trade") Trade trade);//�깺��deal_info�м���

    @Insert("INSERT INTO deal_info(deal_id,deal_type,deal_time,deal_status,prdct_id,cstmr_id,trans_share) VALUES (#{trade.deal_id},1,#{trade.deal_time},0,#{trade.prdct_id},#{trade.cstmr_id},#{trade.trans_share})")
    void addSaleHistory(@Param("trade") Trade trade);//�����deal_info�м���

    @Update("UPDATE cstmr_info SET account_balance = account_balance - #{trade.trans_val} WHERE cstmr_id=#{trade.cstmr_id}")
    void updateAccountBalance(@Param("trade") Trade trade);//�깺Ҫȥ�û��˻�����п۳���Ӧ��Ǯ

    @Update("UPDATE shares_info SET prdct_shares = prdct_shares - #{trade.trans_share} WHERE cstmr_id = #{trade.cstmr_id} AND prdct_id = #{trade.prdct_id}")
    void updateUserOwn(@Param("trade") Trade trade);//�û��ֲ��޸�

    @Select("SELECT prdct_shares FROM shares_info WHERE cstmr_id = #{trade.cstmr_id} AND prdct_id = #{trade.prdct_id}")
    Double getShares(@Param("trade") Trade trade);//�����޸ĺ���зݶ�

    @Delete("DELETE FROM shares_info WHERE cstmr_id = #{trade.cstmr_id} AND prdct_id = #{trade.prdct_id}")
    void deleteShares(@Param("trade") Trade trade);//���ݶ�Ϊ0��ִ�д�ɾ������

    @Insert("INSERT INTO shares_info(cstmr_id,prdct_id,prdct_shares) VALUES (#{trade.cstmr_id},#{trade.prdct_id},#{trade.trans_share})")
    void insertShares(@Param("trade") Trade trade);//ȡ�����ʱ���û��ֲ��޸�

    @Update("UPDATE deal_info SET deal_status = 2 WHERE deal_id = #{deal_id}")
    void tradeCancel(@Param("deal_id")String deal_id);//ȡ�������޸Ľ�����ˮ

    @Select("SELECT prdct_shares FROM shares_info WHERE cstmr_id = #{trade.cstmr_id} AND prdct_id = #{trade.prdct_id}")
    String confirmShares(@Param("trade") Trade trade);//�����޸ĺ���зݶ�

    @Select("SELECT deal_type FROM deal_info WHERE deal_id = #{deal_id}")
    int getDealType(@Param("deal_id")String deal_id);//ͨ�������Ż�ȡ��������

    @Select("SELECT deal_status FROM deal_info WHERE deal_id = #{deal_id}")
    int getDealStatus(@Param("deal_id")String deal_id);//ͨ�������Ż�ȡ����״̬

    @Update("UPDATE cstmr_info SET account_balance = account_balance + #{trade.trans_val} WHERE cstmr_id=#{trade.cstmr_id}")
    void buyCancel(@Param("trade") Trade trade);//ȡ���깺�˻�Ǯ

    @Update("UPDATE shares_info SET prdct_shares = prdct_shares + #{trade.trans_share} WHERE cstmr_id = #{trade.cstmr_id} AND prdct_id = #{trade.prdct_id}")
    void saleCancel(@Param("trade") Trade trade);//���ȡ���޸ĳֲ�

    @Select("SELECT d.*, p.prdct_name, c.cstmr_name FROM deal_info AS d, prdct_info AS p, cstmr_info AS c WHERE d.cstmr_id = c.cstmr_id AND d.prdct_id = p.prdct_id AND d.cstmr_id = #{cstmr_id} ")
    List<Trade> tradeHistory(@Param("cstmr_id")String cstmr_id);//��ѯ�û�������ˮ

    @Select("SELECT * FROM deal_info WHERE deal_id = #{deal_id}")
    Trade getTradeInfo(@Param("deal_id")String deal_id);

    @Select("SELECT d.*, p.prdct_name, c.cstmr_name FROM deal_info AS d, prdct_info AS p, cstmr_info AS c WHERE d.cstmr_id = c.cstmr_id AND d.prdct_id = p.prdct_id ")
    List<Trade> tradeAllHistory();//��ѯ������ˮ

    @Select("SELECT * FROM deal_info WHERE deal_id LIKE '%' #{keyword} '%'")
    List<Trade> tradeSearch(@Param("keyword")String keyword);
}
