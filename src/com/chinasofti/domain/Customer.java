package com.chinasofti.domain;

import java.io.Serializable;

public class Customer implements Serializable{//顾客表
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ctid;//顾客卡号
	private String ctpassword;//顾客密码
	private int cvip;//是否为会员
	private double balance;//余额
	private int frozen;//冻结
	private String ctname;//顾客姓名

	public Customer() {
		super();
	}

	public int getCtid() {
		return ctid;
	}

	public void setCtid(int ctid) {
		this.ctid = ctid;
	}

	public String getCtpassword() {
		return ctpassword;
	}

	public void setCtpassword(String ctpassword) {
		this.ctpassword = ctpassword;
	}

	public int getCvip() {
		return cvip;
	}

	public void setCvip(int cvip) {
		this.cvip = cvip;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getFrozen() {
		return frozen;
	}

	public void setFrozen(int frozen) {
		this.frozen = frozen;
	}

	public String getCtname() {
		return ctname;
	}

	public void setCtname(String ctname) {
		this.ctname = ctname;
	}

	@Override
	public String toString() {
		return ctid+"\t"+ctpassword+"\t"+ctname+"\t"+balance+"\t"+cvip+"\t"+frozen;
	}
	
}
