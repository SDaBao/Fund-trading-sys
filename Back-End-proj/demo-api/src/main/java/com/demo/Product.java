package com.demo;


public class Product {
    public String getPrdct_id() {
        return prdct_id;
    }

    public String getPrdct_name() {
        return prdct_name;
    }

    public String getPrdct_comp() {
        return prdct_comp;
    }

    public String getPrdct_prfl() {
        return prdct_prfl;
    }

    public int getPrdct_risk() {
        return prdct_risk;
    }

    public boolean isPrdct_sale() {
        return prdct_sale;
    }

    public void setPrdct_id(String prdct_id) {
        this.prdct_id = prdct_id;
    }

    public void setPrdct_name(String prdct_name) {
        this.prdct_name = prdct_name;
    }

    public void setPrdct_comp(String prdct_comp) {
        this.prdct_comp = prdct_comp;
    }

    public void setPrdct_prfl(String prdct_prfl) {
        this.prdct_prfl = prdct_prfl;
    }

    public void setPrdct_risk(int prdct_risk) {
        this.prdct_risk = prdct_risk;
    }

    public void setPrdct_sale(boolean prdct_sale) {
        this.prdct_sale = prdct_sale;
    }

    public String prdct_id;
    public String prdct_name;
    public String prdct_comp;
    public String prdct_prfl;
    public int prdct_risk;
    public boolean prdct_sale;

}
