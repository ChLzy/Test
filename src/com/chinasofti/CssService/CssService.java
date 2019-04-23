package com.chinasofti.CssService;

import java.sql.SQLException;
import java.util.List;

import com.chinasofti.domain.Bill;
import com.chinasofti.domain.Clothes;
import com.chinasofti.domain.Customer;
import com.chinasofti.domain.Employee;

public interface CssService {
	//客户登录验证
	public Customer loginC(int caccount) throws SQLException;
	
	//员工登录验证
	public Employee loginE(int emid) throws SQLException;
	
	//查询所有衣服
	public List<Clothes> selectAllClothes() throws SQLException;
	
	//客户查询自己的购物车
	public List<Bill> selectAllBill() throws SQLException;
	
	//经理通过衣服id查询衣服
	public Clothes selectClothesById(int clid) throws SQLException;
	
	//添加衣服到购物车
	public String addBillById(int clid,int bnum) throws SQLException;
	
	//修改衣服购物车数量
	public String updateBill(int clid,int bnum) throws SQLException;
	
	//客户通过衣服id删除购物车中衣服的方法
	public String deleteBillById(int clid) throws SQLException;
	
	//员工查询所有客户信息
	public List<Customer> selectAllCustomer() throws SQLException;
	
	//员工修改自己密码
	public String updatePasswordByEid(String str,int emid)throws SQLException;
	
	//客户修改自己密码
	public String updatePasswordByCtid(String str,int ctid) throws SQLException;
	
	//员工给客户开户
	public String addCustomer(Customer cus) throws SQLException;
	
	//员工给客户卡充值
	public String addMoney(int ctid,double money) throws SQLException;
	
	//员工给客户开会员
	public String addVip(int ctid) throws SQLException;
	
	//给客户卡挂失(冻结)
	public String updateFrozenById(int ctid) throws SQLException;
	
	//经理添加衣服信息
	public String addClothes(Clothes clo) throws SQLException;
	
	//经理修改衣服价格
	public String updateClothes(int clid,double price) throws SQLException;
	
	//经理删除信息衣服
	public String deleteClothes(int clid) throws SQLException;
	
	//经理添加员工信息
	public String addEmployee(Employee e) throws SQLException;
	
	//经理删除员工信息
	public String deleteEmployee(int emid) throws SQLException;
	
	//查询所有员工信息
	public List<Employee> selectAllEmployee() throws SQLException;
	
	//模糊查询衣服
	public List<Clothes> selectLikeClothes(String str) throws SQLException;
}
