package com.demo.server;

import com.demo.Trade;
import com.demo.TradeService;
import com.demo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.demo.server.UserServiceImpl.toJson;


@CloudComponent
public class TradeServiceImpl implements TradeService {

    @Autowired(required = false)
    private TradeDAOMapper tradeDao;
    @Autowired(required = false)
    private UserDAOMapper userDao;
    public static ObjectMapper mapper = new ObjectMapper();

    public String tradeBuy(Trade trade) {
        User user = userDao.userInfo(trade.getCstmr_id());
        BigDecimal accoutBalance = user.account_balance;
        if(trade.getTrans_val() > accoutBalance.doubleValue())
            return "value false";
        try{
            tradeDao.addBuyHistory(trade);
            tradeDao.updateAccountBalance(trade);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("�깺ʧ��");
            return "false";
        }
        return "�ύ�깺�����ɹ�\n�µ�ʱ����\t" + trade.deal_time
                + "\n����id��\t" + trade.deal_id;
    }

    public String tradeSale(Trade trade) {
        if(tradeDao.getShares(trade) < trade.getTrans_share())
            return "value false";
        try{
            tradeDao.addSaleHistory(trade);
            tradeDao.updateUserOwn(trade);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("���ʧ��");
            return "false";
        }
//        if(tradeDao.getShares(trade) == 0)
//            tradeDao.deleteShares(trade);
        return "�ύ��ض����ɹ�\n�µ�ʱ����\t" + trade.deal_time
                + "\n����id��\t" + trade.deal_id;
    }

    public String tradeCancel(String deal_id) {

        Trade trade = tradeDao.getTradeInfo(deal_id);

        if(trade.getDeal_status() == 1)
            return "������ȷ�ϣ��޷�ȡ��\n";
        if(trade.getDeal_status() == 2)
            return "������ȡ���������ظ�����\n";
        try{
            tradeDao.tradeCancel(deal_id);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("����ȡ��ʧ��");
            return "false";
        }


        if(trade.getDeal_type() == 0)
        {
            try{
                tradeDao.buyCancel(trade);
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("�깺����ȡ��ʧ��");
                return "false";
            }
        }
        else if(trade.getDeal_type() == 1) {
//            List<Double> stringList = tradeDao.confirmShares(trade);
//            if(tradeDao.getShares(trade) != 0)
//            {
            try{
                tradeDao.saleCancel(trade);
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("��ض���ȡ��ʧ��");
                return "false";
            }
//            }
//            else
//            {
//                tradeDao.insertShares(trade);
//            }
        }
        return "����idΪ" + deal_id + "�Ľ��׶���ȡ���ɹ�";
    }

    public String tradeHistory(String cstmr_id) {
        try{
            List<Trade> tradeList = tradeDao.tradeHistory(cstmr_id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("n", tradeList.size());
            map.put("trade_info", tradeList);
            return toJson(map);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("��ѯ������ˮʧ��");
            return "false";
        }
    }

    public String tradeAllHistory() {
        try{
            List<Trade> tradeList = tradeDao.tradeAllHistory();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("n", tradeList.size());
            map.put("trade_info", tradeList);
            return toJson(map);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("��ѯ������ˮʧ��");
            return "false";
        }
    }

    public String tradeSearch(String keyword) {
        try{
            List<Trade> tradeList = tradeDao.tradeSearch(keyword);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("n", tradeList.size());
            map.put("trade_info", tradeList);
            return toJson(map);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("��ѯ������ˮʧ��");
            return "false";
        }
    }

}
