package com.chinasofti.domain;

import java.io.Serializable;
import java.util.Date;

public class Accounts implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date adate;
	private String aname;
	private double asum;
	public Accounts() {
		super();
	}
	public double getAsum() {
		return asum;
	}
	public void setAsum(double asum) {
		this.asum = asum;
	}
	
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	
	public Date getAdate() {
		return adate;
	}
	public void setAdate(Date adate) {
		this.adate = adate;
	}
	@Override
	public String toString() {
		return adate+"\t"+aname+"\t"+asum;
	}
}
