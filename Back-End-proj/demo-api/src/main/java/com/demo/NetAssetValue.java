package com.demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class NetAssetValue {
    public String getPrdct_id() {
        return prdct_id;
    }

    public String getDatetime() {
        return mrkt_time;
    }

    public void setPrdct_id(String prdct_id) {
        this.prdct_id = prdct_id;
    }

    public void setDatetime(String datetime) {
        this.mrkt_time = datetime;
    }

    public BigDecimal getPrdct_val() {
        return prdct_val;
    }

    public void setPrdct_val(BigDecimal prdct_val) {
        this.prdct_val = prdct_val;
    }

    public String prdct_id;
    public String mrkt_time;
    public BigDecimal prdct_val;

}
