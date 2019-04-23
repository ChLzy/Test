package com.chinasofti.domain;

import java.io.Serializable;

public class Season implements Serializable{//季节表
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int seaid;//季节编号
	private String seaname;//季节属性
	public int getSeaid() {
		return seaid;
	}
	public void setSeaid(int seaid) {
		this.seaid = seaid;
	}
	public String getSeaname() {
		return seaname;
	}
	public void setSeaname(String seaname) {
		this.seaname = seaname;
	}
	public Season() {
		super();
	}
	@Override
	public String toString() {
		return seaname;
	}
	
	
}
