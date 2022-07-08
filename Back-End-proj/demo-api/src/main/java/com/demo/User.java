package com.demo;

import lombok.Data;

import java.math.BigDecimal;
import java.text.DecimalFormat;
@Data
public class User {
	public String getCstmr_id() {
		return this.cstmr_id;
	}

	public int getCstmr_type() {
		return cstmr_type;
	}

	public String getCstmr_name() {
		return cstmr_name;
	}

	public void setCstmr_id(String cstmr_id) {
		this.cstmr_id = cstmr_id;
	}

	public void setCstmr_type(int cstmr_type) {
		this.cstmr_type = cstmr_type;
	}

	public void setCstmr_name(String cstmr_name) {
		this.cstmr_name = cstmr_name;
	}

	public void setDocument_type(int document_type) {
		this.document_type = document_type;
	}

	public void setDocument_num(String document_num) {
		this.document_num = document_num;
	}

	public void setRisk_rating(int risk_rating) {
		this.risk_rating = risk_rating;
	}

	public void setAccount_balance(BigDecimal account_balance) {
		this.account_balance = account_balance;
	}

	public int getDocument_type() {
		return document_type;
	}

	public String getDocument_num() {
		return document_num;
	}

	public int getRisk_rating() {
		return risk_rating;
	}

	public BigDecimal getAccount_balance() {
		return account_balance;
	}

	public String cstmr_id;
	public int cstmr_type;
	public String cstmr_name;
	public int document_type;
	public String document_num;
	public int risk_rating;
	public BigDecimal account_balance;
}
