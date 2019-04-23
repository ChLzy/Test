package com.chinasofti.domain;

import java.io.Serializable;
import java.sql.Date;

public class Bill implements Serializable {// 账单
	private int bid;// 账单编号
	private int clid;// 衣服编号
	private int bnum;// 服装总数
	private double bsum;// 金额
	private Clothes clo;// 服装类

	public Bill() {
		super();
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getClid() {
		return clid;
	}

	public void setClid(int clid) {
		this.clid = clid;
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public double getBsum() {
		return bsum;
	}

	public void setBsum(double bsum) {
		this.bsum = bsum;
	}

	public Clothes getClo() {
		return clo;
	}

	public void setClo(Clothes clo) {
		this.clo = clo;
	}

	@Override
	public String toString() {
		return bid + "\t" + clo + "\t" + bnum + "\t" + bsum;
	}

}
