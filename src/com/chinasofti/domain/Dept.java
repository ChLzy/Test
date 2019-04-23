package com.chinasofti.domain;

import java.io.Serializable;

public class Dept implements Serializable{//部门表
	private int deptno;//部门编号
	private String dname;//部门名称
	private String loc;//部门位置
	
	public Dept() {
		super();
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	@Override
	public String toString() {
		return deptno+"\t"+loc;
	}
	
}
