package com.demo;

import lombok.Data;

import java.util.Date;

@Data
public class Trade {
    public String getDeal_id() {
        return deal_id;
    }

    public int getDeal_type() {
        return deal_type;
    }

    public String getDeal_time() {
        return deal_time;
    }

    public String getAc_time() {
        return ac_time;
    }

    public int getDeal_status() {
        return deal_status;
    }

    public String getPrdct_id() {
        return prdct_id;
    }

    public String getCstmr_id() {
        return cstmr_id;
    }

    public Double getTrans_val() {
        return trans_val;
    }

    public Double getTrans_share() {
        return trans_share;
    }

    public void setDeal_id(String deal_id) {
        this.deal_id = deal_id;
    }

    public void setDeal_type(int deal_type) {
        this.deal_type = deal_type;
    }

    public void setDeal_time(String deal_time) {
        this.deal_time = deal_time;
    }

    public void setAc_time(String ac_time) {
        this.ac_time = ac_time;
    }

    public void setDeal_status(int deal_status) {
        this.deal_status = deal_status;
    }

    public void setPrdct_id(String prdct_id) {
        this.prdct_id = prdct_id;
    }

    public void setCstmr_id(String cstmr_id) {
        this.cstmr_id = cstmr_id;
    }

    public void setTrans_val(Double trans_val) {
        this.trans_val = trans_val;
    }

    public void setTrans_share(Double trans_share) {
        this.trans_share = trans_share;
    }

    public String deal_id;
    public int deal_type;
    public String deal_time;
    public String ac_time;
    public int deal_status;
    public String prdct_id;
    public String cstmr_id;
    public Double trans_val;
    public Double trans_share;
    public String cstmr_name;
    public String prdct_name;
}
