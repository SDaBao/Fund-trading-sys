package com.demo;

import com.hundsun.jrescloud.rpc.annotation.CloudService;
import org.apache.kafka.common.protocol.types.Field;

@CloudService
public interface TradeService {
    public String tradeBuy(Trade trade);
    public String tradeSale(Trade trade);
    public String tradeCancel(String deal_id);
    public String tradeHistory(String cstmr_id);
    public String tradeAllHistory();
    public String tradeSearch(String keyword);
}
