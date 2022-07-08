package com.demo;

import java.math.BigDecimal;

public class Share {
//    public String getCstmr_id() {
//        return cstmr_id;
//    }
//
//    public void setCstmr_id(String cstmr_id) {
//        this.cstmr_id = cstmr_id;
//    }

    public String getPrdct_id() {
        return prdct_id;
    }

    public void setPrdct_id(String prdct_id) {
        this.prdct_id = prdct_id;
    }

    public BigDecimal getPrdct_shares() {
        return prdct_shares;
    }

    public void setPrdct_shares(BigDecimal prdct_shares) {
        this.prdct_shares = prdct_shares;
    }

    public String getPrdct_name() {
        return prdct_name;
    }

    public void setPrdct_name(String prdct_name) {
        this.prdct_name = prdct_name;
    }
    String cstmr_id;
    String prdct_id;
    BigDecimal prdct_shares;
    String prdct_name;
}
