package com.chinasofti.domain;

import java.io.Serializable;

public class Employee implements Serializable {// 员工表
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int emid;// 员工编号
	private String ename;// 员工姓名
	private String emsex;// 员工性别
	private int deptno;// 部门编号
	private Dept de;// 部门类
	private String epassword;// 员工密码

	public Employee() {
		super();
	}

	public int getEmid() {
		return emid;
	}

	public void setEmid(int emid) {
		this.emid = emid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEmsex() {
		return emsex;
	}

	public void setEmsex(String emsex) {
		this.emsex = emsex;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public Dept getDe() {
		return de;
	}

	public void setDe(Dept de) {
		this.de = de;
	}

	public String getEpassword() {
		return epassword;
	}

	public void setEpassword(String epassword) {
		this.epassword = epassword;
	}

	@Override
	public String toString() {
		return emid+"\t"+ename+"\t"+emsex+"\t"+epassword+"\t"+de;
	}

}
