package com.chinasofti.CssService;


import java.util.Date;
import java.util.List;

import com.chinasofti.domain.Accounts;
import com.chinasofti.domain.Bill;
import com.chinasofti.domain.Clothes;
import com.chinasofti.domain.Customer;
import com.chinasofti.domain.Employee;

public interface CssService {
	//客户登录验证
	public Customer loginC(int caccount);
	
	//员工登录验证
	public Employee loginE(int eaccount);
	
	//查询所有衣服
	public List<Clothes> selectAllClothes();
	
	//客户查询自己的购物车
	public List<Bill> selectAllBill();
	
	//经理通过衣服id查询衣服
	public Clothes selectClothesById(int clid);
	
	//添加衣服到购物车
	public String addBillById(int clid,int bnum);
	
	//修改衣服购物车数量
	public String updateBill(int clid,int bnum);
	
	//客户通过衣服id删除购物车中衣服的方法
	public String deleteBillById(int clid);
	
	//员工查询所有客户信息
	public List<Customer> selectAllCustomer();
	
	//员工修改自己密码
	public String updatePasswordByEid(String str,int emid);
	
	//客户修改自己密码
	public String updatePasswordByCtid(String str,int ctid);
	
	//员工给客户开户
	public String addCustomer(Customer cus);
	
	//员工给客户卡充值
	public String addMoney(int ctid,double money);
	
	//员工给客户开会员
	public String addVip(int ctid);
	
	//给客户卡挂失(冻结)
	public String updateFrozenById(int ctid,int i);
	
	//经理添加衣服信息
	public String addClothes(Clothes clo);
	
	//经理修改衣服价格
	public String updateClothes(int clid,double price);
	
	//经理删除信息衣服
	public String deleteClothes(int clid);
	
	//经理添加员工信息
	public String addEmployee(Employee e);
	
	//经理删除员工信息
	public String deleteEmployee(int emid);
	
	//查询所有员工信息
	public List<Employee> selectAllEmployee();
	
	//模糊查询衣服
	public List<Clothes> selectLikeClothes(String str);
	
	//清空购物车
	public void deleteBill();

	//结账
	public String updateBalance(int ctid,double dd);
	
	//流水账
	public void addAccounts(String aname,String date,double money);

	//根据日期查询流水账
	public List<Accounts> selectMonthAccounts(String yy,String mm);
	
	//查询总账
	public List<Accounts> selectAllAccounts();
}
