package com.chinasofti.view;


public class View {
	
	public View() {
		super();
	}
	//创建初始界面
	public void uiView(){
	
		System.out.println("___________________________________________________");
		System.out.println("\n\n\t\t     欢迎来到品如服装店\n");
		System.out.println("\n\t\t1、顾客");
		System.out.println("\n\t\t2、员工");
		System.out.println("\n\n___________________________________________________");
	}
	//创建销售员使用菜单
	public void saleView(){
		System.out.println("___________________________________________________");
		System.out.println("\n\n\t\t     欢迎使用品如购物系统\n");
		System.out.println("\n\t\t1、查询所有顾客信息");
		System.out.println("\n\t\t2、修改密码");
		System.out.println("\n\t\t3、开户办卡");
		System.out.println("\n\t\t4、充值");
		System.out.println("\n\t\t5、账户冻结");
		System.out.println("\n\t\t0、退出系统");
		System.out.println("\n\n___________________________________________________");
	}
	//创建顾客界面
	public void cusView(){
		System.out.println("___________________________________________________");
		System.out.println("\n\n\t\t     欢迎使用品如购物系统\n");
		System.out.println("\n\t\t1、查看所有商品");
		System.out.println("\n\t\t2、模糊查询");
		System.out.println("\n\t\t3、查看购物车");
		System.out.println("\n\t\t4、修改密码");
		System.out.println("\n\t\t5、查看个人信息");
		System.out.println("\n\t\t0、退出系统");
		System.out.println("\n\n___________________________________________________");
	}
	//购物车界面
	public void shopView(){
		System.out.println("___________________________________________________");
		System.out.println("\n\t\t1、更改数量");
		System.out.println("\n\t\t2、结账");
		System.out.println("\n\t\t0、返回上一层");
		System.out.println("\n___________________________________________________");
	}
	//经理界面
	public void managerView(){
		System.out.println("___________________________________________________");
		System.out.println("\n\n\t\t     欢迎使用品如购物系统\n");
		System.out.println("\n\t\t1、添加衣服信息");
		System.out.println("\n\t\t2、修改衣服价格");
		System.out.println("\n\t\t3、删除衣服信息");
		System.out.println("\n\t\t4、添加员工");
		System.out.println("\n\t\t5、删除员工");
		System.out.println("\n\t\t6、查询所有员工");
		System.out.println("\n\t\t7、查看所有衣服");
		System.out.println("\n\t\t8、查看账单");
		System.out.println("\n\t\t0、退出系统");
		System.out.println("\n\n___________________________________________________");
	}
	
	//账单界面
	public void accountsView(){
		System.out.println("___________________________________________________");
		System.out.println("\n\t\t1、查询月账单");
		System.out.println("\n\t\t2、查询总账单");
		System.out.println("\n\t\t0、返回");
		System.out.println("\n___________________________________________________");
	  
	}
}
