package com.chinasofti.view;

import com.chinasofti.util.UserInput;

public class View {
	private UserInput ui;
	
	public View() {
		super();
		this.ui = new UserInput();
	}
	//创建初始界面
	public void uiView(){
		for(int i=0;i<5;i++){
			for (int j = 0; j < 5-i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}	
		System.out.println("欢迎来到品如服装店");
		for(int i=0;i<3;i++){
			System.out.println("******************");
		}	
		System.out.println("1、顾客");
		System.out.println("2、员工");
	}
	//创建销售员使用菜单
	public void saleView(){
		System.out.println("欢迎使用品如购物系统");
		System.out.println("----------------------------");
		System.out.println("1、查询所有顾客信息");
		System.out.println("2、修改密码");
		System.out.println("3、开户办卡");
		System.out.println("4、充值");
		System.out.println("5、开会员");
		System.out.println("6、账户冻结");
		System.out.println("0、退出系统");
		System.out.println("----------------------------");
	}
	//创建顾客界面
	public void cusView(){
		System.out.println("欢迎使用品如购物系统");
		System.out.println("----------------------------");
		System.out.println("1、查看所有商品");
		System.out.println("2、模糊查询");
		System.out.println("3、查看购物车");
		System.out.println("4、修改密码");
		System.out.println("5、打印账单");
		System.out.println("0、退出系统");
		System.out.println("----------------------------");
	}
	//购物车界面
	public void shopView(){
		System.out.println("-------------------------");
		System.out.println("1、更改数量");
		System.out.println("2、结账");
		System.out.println("0、返回上一层");		
	}
	//经理界面
	public void managerView(){
		System.out.println("欢迎使用品如购物系统");
		System.out.println("----------------------------");
		System.out.println("1、添加衣服信息");
		System.out.println("2、修改衣服价格");
		System.out.println("3、删除衣服信息");
		System.out.println("4、添加员工");
		System.out.println("5、删除员工");
		System.out.println("0、退出系统");
		System.out.println("----------------------------");
	}
	
}
