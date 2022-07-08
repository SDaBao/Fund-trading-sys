package com.client;

import com.demo.Trade;
import com.demo.TradeService;
import com.google.common.collect.BoundType;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

@RestController
public class TradeClientController
{
    @CloudReference
    private TradeService tradeService;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    public static synchronized String generateUniqueKey(){
        Random random = new Random();
        // 随机数的量 自由定制，这是6位随机数
        Integer r = random.nextInt(900000) + 100000;

        // 返回  13位时间
        Long timeMillis = System.currentTimeMillis();

        // 返回  8位时间
        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String timeStr = sdf.format(new Date());

        return  timeStr + r;
    }

    @RequestMapping(value = "/trade/buy", method = RequestMethod.GET)
    public String tradeBuy(@RequestParam("prdct_id")String prdct_id,
                        @RequestParam("cstmr_id")String cstmr_id,
                        @RequestParam("trans_val")Double trans_val)
    {
        Trade trade = new Trade();
        trade.setCstmr_id(cstmr_id);
        trade.setPrdct_id(prdct_id);
        trade.setDeal_type(0);
        trade.setTrans_val(trans_val);
        trade.setDeal_time(dtf.format(LocalDateTime.now()));
        trade.setDeal_id(generateUniqueKey());
            return tradeService.tradeBuy(trade);
    }
    @RequestMapping(value = "/trade/sale", method = RequestMethod.GET)
    public String tradeSale(@RequestParam("prdct_id")String prdct_id,
                           @RequestParam("cstmr_id")String cstmr_id,
                           @RequestParam("trans_share")Double trans_share)
    {
        Trade trade = new Trade();
        trade.setCstmr_id(cstmr_id);
        trade.setPrdct_id(prdct_id);
        trade.setDeal_type(1);
        trade.setTrans_share(trans_share);
        trade.setDeal_time(dtf.format(LocalDateTime.now()));
        trade.setDeal_id(generateUniqueKey());
        return tradeService.tradeSale(trade);
    }

    @RequestMapping(value = "/trade/cancel", method = RequestMethod.GET)
    public String tradeCancel(@RequestParam("deal_id")String deal_id){
        return tradeService.tradeCancel(deal_id);
    }
    @RequestMapping(value = "/trade/history", method = RequestMethod.GET)
    public String tradeHistory(@RequestParam("cstmr_id")String cstmr_id){
        return tradeService.tradeHistory(cstmr_id);
    }

    @RequestMapping(value = "/trade/allHistory", method = RequestMethod.GET)
    public String tradeAllHistory(){
        return tradeService.tradeAllHistory();
    }

    @RequestMapping(value = "/trade/search", method = RequestMethod.GET)
    public String tradeSearch(@RequestParam("keyword")String keyword){
        return tradeService.tradeSearch(keyword);
    }
}
