package com.chinasofti.domain;

import java.io.Serializable;

public class Clothes implements Serializable{//服装表
	private int clid;//服装编号
	private String clname;//服装名称
	private double price;//服装价格
	private int seaid;//季节编号
	private Season sea;//季节类 
	public Clothes() {
		super();
	}
	public int getClid() {
		return clid;
	}
	public void setClid(int clid) {
		this.clid = clid;
	}
	public String getClname() {
		return clname;
	}
	public void setClname(String clname) {
		this.clname = clname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Season getSea() {
		return sea;
	}
	public void setSea(Season sea) {
		this.sea = sea;
	}
	public int getSeaid() {
		return seaid;
	}
	public void setSeaid(int seaid) {
		this.seaid = seaid;
	}
	@Override
	public String toString() {
		return clid +"\t"+ clname +"\t"+price +"\t"+sea;
	}
	
}
