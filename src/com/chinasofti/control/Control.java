package com.chinasofti.control;

import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.chinasofti.CssService.CssService;
import com.chinasofti.domain.Accounts;
import com.chinasofti.domain.Bill;
import com.chinasofti.domain.Clothes;
import com.chinasofti.domain.Customer;
import com.chinasofti.domain.Employee;
import com.chinasofti.util.UserInput;
import com.chinasofti.view.View;

public class Control {
	// 属性
	private View v;
	private UserInput ui;
	public static final String IP = "10.10.49.120";
	public static final int PORT = 6666;
	private CssService service;
	// 实体类
	Customer cus = null;
	Clothes clo = null;
	Bill bil = null;
	Employee emp = null;

	public Control() {

		this.v = new View();
		this.ui = new UserInput();
		// 创建代理对象
		service = ProxyClient.getClient(CssService.class, IP, PORT);
	}

	// 自定义方法-项目流程
	public void start() {
		while (true) {
			this.v.uiView();
			int select = this.ui.getInt("\n\t请选择:");
			if (select == 1) {
				this.loginc();
				while (true) {
					this.v.cusView();
					int i = this.ui.getInt("\n\t请选择:");
					if (i == 0) {
						System.out.println("\t*******系统已退出！**********");
						this.service.deleteBill();
						System.exit(0);
					} else if (i == 1) {
						this.selectAllClothes();
						this.selectClothes();
					} else if (i == 2) {
						this.selectLike();

					} else if (i == 3) {
						this.selectAllBill();
					} else if (i == 4) {
						this.updatePassword();
					} else if (i == 5) {
						this.selectSelf();
					}
				}
			} else if (select == 2) {
				this.loginE();
				if (emp.getDe().getDname().equals("普通员工")) {
					while (true) {
						this.v.saleView();
						int j = this.ui.getInt("\n\t请选择:");
						if (j == 0) {
							System.out.println("\t系统已退出！");
							System.exit(0);
						} else if (j == 1) {
							this.selectAllCustomer();
						} else if (j == 2) {
							this.updatePasswordE();
						} else if (j == 3) {
							this.addCustomer();
						} else if (j == 4) {
							this.addCusMoney();
						} else if (j == 5) {
							this.updateCusState();
						}
					}
				} else if (emp.getDe().getDname().equals("经理")) {
					while (true) {
						this.v.managerView();
						int k = this.ui.getInt("\n\t请选择：");
						if (k == 0) {
							System.out.println("\t系统已终止！");
							System.exit(0);
						} else if (k == 1) {
							this.addManClothes();
						} else if (k == 2) {
							this.updateCloPrice();
						} else if (k == 3) {
							this.removeClothes();
						} else if (k == 4) {
							this.addEmployee();
						} else if (k == 5) {
							this.removeEmployee();
						} else if (k == 6) {
							this.selectEmployee();
						} else if (k == 7) {
							this.selectAllClothes();
						} else if (k == 8) {
							this.selectAccounts();
						}
					}
				}

			}

		}
	}

	// 查询流水账
	public void selectAccounts() {
		while (true) {
			this.v.accountsView();
			int select = this.ui.getInt("请选择(0返回)：");
			if (select == 0) {
				break;
			} else if (select == 1) {
				while (true) {
					String s = null;
					String ss = null;
					
					int i = this.ui.getInt("请输入要查询的年份(0退出)：");
					if (i == 0) {
						break;
					} else if (i < 2010 || i > 2999) {
						System.out.println("您输入的年份不正确，请重新输入！");
						continue;
					} else {
						s = String.valueOf(i);
					}
					int j = this.ui.getInt("请输入要查询的月份：");
					if (j < 1 || j > 12) {
						System.out.println("您输入的月份不正确，请重新输入！");
						continue;
					} else if (j < 10 && j > 0) {
						ss = "0" + String.valueOf(j);
					} else {
						ss = String.valueOf(j);
					}
					List<Accounts> selectAllMonthAccounts = this.service
							.selectMonthAccounts(s, ss);
					if (selectAllMonthAccounts.size() == 0) {
						System.out.println("没有查到任何数据，请重新输入！");
					}else{
						System.out.println("       日期\t\t顾客\t消费金额");						
						for (Accounts accounts : selectAllMonthAccounts) {
							System.out.println(accounts);
						}
					}
				}

			} else if (select == 2) {
				System.out.println("       日期\t\t顾客\t消费金额");
				List<Accounts> selectAllAccounts = this.service
						.selectAllAccounts();
				for (Accounts accounts : selectAllAccounts) {
					System.out.println(accounts);
				}
			}
		}

	}

	// 查询所有员工
	private void selectEmployee() {
		System.out
				.println("___________________________________________________");
		System.out.println("\n\t账号\t姓名\t性别\t密码\t职位\t工作地点");
		List<Employee> selectAllEmployee = this.service.selectAllEmployee();
		for (Employee employee : selectAllEmployee) {
			System.out.println("\t" + employee);
		}

	}

	// 查看个人信息
	private void selectSelf() {
		System.out.println("\n>>>个人信息");
		System.out
				.println("___________________________________________________");
		System.out.println("\n\n\t账号:\t" + cus.getCtid() + "\n");
		System.out.println("\t姓名:\t" + cus.getCtname() + "\n");
		System.out.println("\t余额:\t￥" + cus.getBalance() + "\n");
		System.out.println("\t会员:\t" + (cus.getCvip() == 1 ? "是" : "否") + "\n");
		System.out
				.println("\n\n___________________________________________________");
	}

	// 选择衣服
	private void selectClothes() {
		haha: while (true) {
			int selectid = this.ui.getInt("请输入您心仪的衣服的id（0退出）:");
			if (selectid == 0) {
				break;
			}
			List<Bill> selectAllBill = this.service.selectAllBill();
			for (Bill bill : selectAllBill) {
				if (bill.getClid() == selectid) {
					System.out.println("\t您选择的衣服已存在，请前往购物车修改数量");
					break haha;
				}

			}
			clo = this.service.selectClothesById(selectid);

			if (clo.getClname() == null) {
				System.out.println("\t输入有误，请重新输入！");
				continue;
			}
			System.out.println("\t衣服信息：");
			System.out.println("\t" + clo);
			int bnum = this.ui.getInt("\t请输入数量：");
			String isCheng = this.service.addBillById(selectid, bnum);
			System.out.println(isCheng);

		}
		System.out
				.println("___________________________________________________");
	}

	// 顾客模糊查询
	public void selectLike() {
		System.out
				.println("___________________________________________________");
		while (true) {
			String s = this.ui.getString("\t请输入关键字(0退出)");
			if (s.equals("0")) {
				break;
			}
			List<Clothes> selectLikeClothes = this.service.selectLikeClothes(s);
			System.out.println(selectLikeClothes.size());
			if (selectLikeClothes.size() == 0) {
				System.out.println("\t此关键字无内容，请重新输入！");
			} else {
				System.out.println("\n\t衣服编号\t衣服名称\t价格\t季节");
				for (Clothes clothes : selectLikeClothes) {
					System.out.println("\t" + clothes);
				}
				this.selectClothes();
			}
		}
		System.out
				.println("\n\n___________________________________________________");
	}

	// 删除员工
	private void removeEmployee() {
		System.out
				.println("___________________________________________________");
		List<Employee> list = this.service.selectAllEmployee();
		for (Employee employee : list) {
			System.out.println(employee);
		}
		String s = this.service
				.deleteEmployee(this.ui.getInt("\t请输入要删除员工的id："));
		System.out.println(s);
		System.out
				.println("\t\t___________________________________________________");
	}

	// 添加员工
	public void addEmployee() {
		System.out
				.println("___________________________________________________");
		Employee e = new Employee();
		haha: while (true) {
			int i = this.ui.getInt("\t请输入员工id（长度6）:");
			if (i < 100000 || i > 999999) {
				System.out.println("\t账号长度不合格，请重新输入！");
				continue;
			}
			List<Employee> selectAllEmployee = this.service.selectAllEmployee();
			for (Employee employee : selectAllEmployee) {
				if (employee.getEmid() == i) {
					System.out.println("\t员工id已存在，请重新输入！");
					continue haha;
				}

			}
			e.setEmid(i);
			break;
		}
		e.setEname(this.ui.getString("\t请输入姓名："));

		while (true) {
			String s = this.ui.getString("\t请输入性别：");
			if (s.equals("男") || s.equals("女")) {
				e.setEmsex(s);
				break;
			} else {
				System.out.println("\t性别输入不正确，请重新输入！");
			}
		}
		while (true) {
			int i = this.ui.getInt("\t请输入部门编号：");

			if (i == 1 || i == 2) {
				e.setDeptno(i);
				break;
			} else {
				System.out.println("\t部门编号输入不正确，请重新输入！");
			}
		}

		while (true) {
			String s = this.ui.getString("\t请输入密码(6位)：");
			if (s.length() < 100000 || s.length() > 999999) {
				System.out.println("\t密码长度不合格，请重新输入！");
				continue;
			}
			String ss = this.ui.getString("\t请再次输入密码：");
			if (s.equals(ss)) {
				e.setEpassword(s);
				break;
			} else {
				System.out.println("\t两次密码不一致，请重新输入！");
			}

		}
		String addEmployee = this.service.addEmployee(e);
		System.out.println(addEmployee);
		System.out
				.println("___________________________________________________");
	}

	// 经理删除衣服
	private void removeClothes() {
		System.out
				.println("___________________________________________________");
		this.selectAllClothes();
		String s = this.service.deleteClothes(this.ui
				.getInt("\n\t请输入要删除的衣服id:"));
		System.out.println(s);
		System.out
				.println("\n\n___________________________________________________");
	}

	// 经理修改衣服价格
	private void updateCloPrice() {
		System.out
				.println("___________________________________________________");
		this.selectAllClothes();
		String s = this.service.updateClothes(this.ui.getInt("\t请输入要修改的衣服id:"),
				this.ui.getDouble("\t请输入修改后的单价："));
		System.out.println(s);
		System.out
				.println("\n\n___________________________________________________");
	}

	// 经理添加衣服信息
	public void addManClothes() {
		System.out
				.println("___________________________________________________");
		haha: while (true) {
			Clothes c = new Clothes();
			hehe: while (true) {
				int i = this.ui.getInt("\n\t请输入编号(0退出)：");
				if (i == 0) {
					break haha;
				}
				List<Clothes> selectAllClothes = this.service
						.selectAllClothes();
				for (Clothes clothes : selectAllClothes) {
					if (clothes.getClid() == i) {
						System.out.println("\t衣服已经存在");
						continue hehe;
					}
				}
				c.setClid(i);
				break;
			}
			c.setClname(this.ui.getString("\t请输入衣服名称："));
			c.setPrice(this.ui.getDouble("\t请输入衣服价格："));
			c.setSeaid(this.ui.getInt("\t请输入衣服适合季节id："));
			System.out.println("\t编号\t品名\t价格\t季节编号");
			System.out
					.println("\t添加的衣服信息如下：" + "\n" + c.getClid() + "\t"
							+ c.getClname() + "\t" + c.getPrice() + "\t"
							+ c.getSeaid());
			String s = this.ui.getString("\t确认添加？（y/n）");
			if (s.equals("y")) {
				String addClothes = this.service.addClothes(c);
				System.out.println(addClothes);
			} else {
				System.out.println("\t取消添加！");
			}
		}
		System.out
				.println("\n\n___________________________________________________");

	}

	// 客户冻结状态
	private void updateCusState() {
		System.out
				.println("___________________________________________________");
		System.out.println("\n>>>账户冻结状态");
		System.out.println("\n\t\t1、冻结");
		System.out.println("\n\t\t2、解冻");
		System.out.println("\n\t\t0、退出");
		System.out
				.println("\n\n___________________________________________________");
		int select = this.ui.getInt("\n\t请选择：");
		if (select == 0) {
			// break;
		} else if (select == 1) {
			System.out
					.println("___________________________________________________");
			List<Customer> selectAllCustomer = this.service.selectAllCustomer();
			System.out.println("账号\t姓名\t余额\t会员\t冻结");
			for (Customer customer : selectAllCustomer) {
				if (customer.getFrozen() == 0) {
					System.out.println(customer.getCtid() + "\t"
							+ customer.getCtname() + "\t"
							+ customer.getBalance() + "\t" + customer.getCvip()
							+ "\t" + customer.getFrozen());
				}
			}
			System.out
					.println("\n\n___________________________________________________");
			int i = this.ui.getInt("\n\t请输入要冻结的账号");
			for (Customer customer : selectAllCustomer) {
				if (customer.getFrozen() == 0) {
					if (customer.getCtid() == i) {
						String str = this.ui.getString("\t是否冻结顾客"
								+ customer.getCtname() + "（y/n）？");
						if (str.equals("y")) {
							String s = this.service.updateFrozenById(i, 1);
							System.out.println(s);
						}
					}
				}
			}
			System.out
					.println("\n\n___________________________________________________");
		} else if (select == 2) {
			System.out
					.println("___________________________________________________");
			List<Customer> selectAllCustomer = this.service.selectAllCustomer();
			for (Customer customer : selectAllCustomer) {
				if (customer.getFrozen() == 1) {
					System.out.println(customer.getCtid() + "\t"
							+ customer.getCtname() + "\t"
							+ customer.getBalance() + "\t" + customer.getCvip()
							+ "\t" + customer.getFrozen());
				}
			}
			int i = this.ui.getInt("\n\t\t请输入要解冻的账号");
			for (Customer customer : selectAllCustomer) {
				if (customer.getFrozen() == 1) {
					if (customer.getCtid() == i) {
						String str = this.ui.getString("\t\t是否解冻顾客"
								+ customer.getCtname() + "（y/n）？");
						if (str.equals("y")) {
							String s = this.service.updateFrozenById(i, 0);
							System.out.println(s);
						}
					}
				}
			}
			System.out
					.println("\n\n___________________________________________________");
		}
	}

	// 给客户开vip
	private void addVip() {
		System.out
				.println("___________________________________________________");
		String addVip = this.service.addVip(this.ui.getInt("\n\t请输入开会员的客户id:"));
		this.addCusMoney();
		System.out.println(addVip);
		System.out
				.println("\n\n___________________________________________________");

	}

	// 给客户充值开通vip
	private void addCusMoney() {
		System.out
				.println("___________________________________________________");
		haha: while (true) {
			int i = this.ui.getInt("\n\t请输入id(0退出):");
			if (i == 0) {
				break;
			}
			List<Customer> selectAllCustomer = this.service.selectAllCustomer();
			for (Customer customer : selectAllCustomer) {
				if (customer.getCtid() == i) {
					double d = this.ui.getDouble("\t请输入要充值的金额：");
					String addMoney = this.service.addMoney(i, d);
					if (d >= 500) {
						String addVip = this.service.addVip(i);
						System.out.println(addVip);
					}
					System.out.println(addMoney);
					break haha;
				}
			}
			System.out.println("\tid输入错误，请重新输入！");

		}
		System.out
				.println("\n\n___________________________________________________");
	}

	// 售货员给顾客开户
	public void addCustomer() {
		System.out
				.println("___________________________________________________");
		haha: while (true) {
			Customer cuss = new Customer();
			int i = this.ui.getInt("\n\t请输入账号（6位数,0退出）：");
			if (i == 0) {
				break;
			}
			if (i < 100000 || i > 999999) {
				System.out.println("\t账号长度不正确，请重新输入！！！");
				continue;
			}

			List<Customer> selectAllCustomer = this.service.selectAllCustomer();
			for (Customer customer : selectAllCustomer) {
				if (customer.getCtid() == i) {
					System.out.println("\t账号已存在!!!");
					break haha;
				}
			}
			cuss.setCtid(i);

			cuss.setBalance(0);
			cuss.setCtname(this.ui.getString("\n\t请输入姓名："));
			cuss.setCvip(0);
			cuss.setFrozen(0);
			while (true) {
				String s = this.ui.getString("\t请输入密码（长度6）：");
				if (s.length() < 100000 || s.length() > 999999) {
					System.out.println("\t密码长度不正确，请重新输入！！！");
					continue;
				}
				String s2 = this.ui.getString("\t请再次输入密码：");
				if (s.equals(s2)) {
					cuss.setCtpassword(s);
					break;
				} else {
					System.out.println("\t两次密码不一致！");
				}
			}

			String addCustomer = this.service.addCustomer(cuss);
			System.out.println(addCustomer);
		}
		System.out
				.println("___________________________________________________");
	}

	// 修改员工密码
	private void updatePasswordE() {
		System.out
				.println("___________________________________________________");
		while (true) {
			System.out.println("\n>>>修改密码");
			String oldPassword = this.ui.getString("\t请输入旧密码（n退出）：");
			if (oldPassword.equals("n")) {
				break;
			}
			if (emp.getEpassword().equals(oldPassword)) {
				while (true) {
					String newPassword = this.ui.getString("\t请输入新密码(6-15)：");
					if (newPassword.length() > 5 && newPassword.length() < 16) {
						String updatePasswordByEid = this.service
								.updatePasswordByEid(newPassword, emp.getEmid());
						System.out.println(updatePasswordByEid);
						break;
					} else {
						System.out.println("\t密码长度6-15");
					}
				}
				break;
			} else {
				System.out.println("\t密码不正确，无法修改！");
			}

		}
		System.out
				.println("___________________________________________________");
	}

	// 查询所有客户
	private void selectAllCustomer() {
		System.out
				.println("___________________________________________________");
		System.out.println("\n\t账号\t姓名\t余额\tvip\t冻结");
		List<Customer> sac = this.service.selectAllCustomer();
		for (Customer customer : sac) {
			System.out.println("\t" + customer.getCtid() + "\t"
					+ customer.getCtname() + "\t￥" + customer.getBalance()
					+ "\t" + (customer.getCvip() == 1 ? "是" : "否") + "\t"
					+ (customer.getFrozen() == 1 ? "是" : "否"));
		}

	}

	// 员工登录
	private void loginE() {
		System.out
				.println("___________________________________________________");
		while (true) {
			int id = this.ui.getInt("\n\t请输入id：");
			String password = this.ui.getString("\t请输入密码：");
			emp = this.service.loginE(id);
			if (emp.getEmid() == id && emp.getEpassword().equals(password)) {
				System.out.println("\t欢迎" + emp.getEname()
						+ emp.getDe().getDname() + "登录");
				break;
			} else {
				System.out.println("\t输入有误，请重新输入！");
			}
		}
		System.out
				.println("\n\n___________________________________________________");
	}

	// 顾客登录
	private void loginc() {
		System.out
				.println("___________________________________________________");
		while (true) {
			int account = this.ui.getInt("\n\t请输入账号(0退出)：");
			if (account == 0) {
				System.out.println("\t系统已退出，欢迎下次光临(*^_^*)");
				System.exit(0);
			}
			String password = this.ui.getString("\t请输入密码：");
			cus = this.service.loginC(account);
			if (cus.getCtid() == account
					&& cus.getCtpassword().equals(password)) {
				if (cus.getFrozen() == 0) {
					System.out.println("\t欢迎" + cus.getCtname() + "顾客光临品如服装店");
					break;
				} else {
					System.out.println("\t抱歉，您的账号已被冻结，请联系员工进行解冻！");
				}

			} else {
				System.out.println("\t输入有误，请重新输入！");
			}

		}
		System.out
				.println("\n\n___________________________________________________");
	}

	// 顾客修改密码
	private void updatePassword() {
		System.out
				.println("___________________________________________________");
		while (true) {
			System.out.println("\n\t>>>修改密码");
			String oldPassword = this.ui.getString("\t请输入旧密码（n退出）：");
			if (oldPassword.equals("n")) {
				break;
			}
			if (cus.getCtpassword().equals(oldPassword)) {
				while (true) {
					String newPassword = this.ui.getString("\t请输入新密码(6-15)：");
					if (newPassword.length() > 5 && newPassword.length() < 16) {
						String updatePasswordByCid = this.service
								.updatePasswordByCtid(newPassword,
										cus.getCtid());
						System.out.println(updatePasswordByCid);
						break;
					} else {
						System.out.println("\t密码长度6-15");
					}
				}
				break;
			} else {
				System.out.println("\t密码不正确，无法修改！");
			}

		}
		System.out
				.println("___________________________________________________");
	}

	// 查询所有服装
	private void selectAllClothes() {
		System.out
				.println("___________________________________________________");
		System.out.println("\n>>>品如服装展");
		List<Clothes> list = this.service.selectAllClothes();
		System.out.println("\t衣服编号\t衣服名称\t价格\t季节");
		for (Clothes clothes : list) {
			System.out.println("\t" + clothes);
		}
		System.out
				.println("\n\n___________________________________________________");
	}

	// 查看购物车所有信息
	private void selectAllBill() {
		System.out
				.println("___________________________________________________");
		while (true) {
			System.out.println("\n>>>购物车");
			System.out.println("\t购物车编号\t衣服编号\t品名\t单价\t数量\t总价");
			List<Bill> list = this.service.selectAllBill();
			for (Bill bill : list) {
				System.out.println("\t" + bill);
			}
			System.out
					.println("\n\n___________________________________________________");
			this.v.shopView();
			int select = this.ui.getInt("\n\n\t请选择：");
			if (select == 1) {
				System.out
						.println("___________________________________________________");
				int i = this.ui.getInt("\t请输入要修改的衣服编号：");
				int i2 = this.ui.getInt("\t请输入要修改的数量：");
				if (i2 > 0) {
					String s = this.service.updateBill(i, i2);
					System.out.println(s);
				} else if (i2 == 0) {
					String bclothes = this.service.deleteBillById(i);
					System.out.println(bclothes);
				}
				System.out
						.println("\n\n___________________________________________________");
			} else if (select == 2) {
				System.out
						.println("___________________________________________________");
				double d = this.jieZhang();
				System.out.println("您应付款" + "￥" + d);
				System.out.println("您的余额" + "￥" + cus.getBalance());
				if (cus.getBalance() >= d) {
					String s = this.ui.getString("是否确认结账（y/n）？");
					if (s.equals("y")) {
						double dd = cus.getBalance() - d;
						System.out
								.println("___________________________________________________");
						System.out.println("您余额为￥" + dd);
						String bal = this.service.updateBalance(cus.getCtid(),
								dd);
						System.out.println(bal);
						String str = this.ui.getString("是否打印小票（y/n）？");
						if (str.equals("y")) {
							this.jieZhang();
							this.service.deleteBill();
							System.out.println("已退出系统，欢迎下次光临！！！");
							System.exit(0);
						} else if (str.equals("n")) {
							System.out.println("已退出系统，欢迎下次光临！！！");
							System.exit(0);
						}
					} else if (s.equals("n")) {
						break;
					}
				} else {
					System.out.println("\t\t您的余额不足，请联系员工充值");
				}

				System.out
						.println("___________________________________________________");
				break;
			} else if (select == 0) {
				break;
			}
		}
	}

	// 结账
	private double jieZhang() {
		/*
		 * Calendar cal = Calendar.getInstance(); int y, m, d; y =
		 * cal.get(Calendar.YEAR); m = cal.get(Calendar.MONTH); d =
		 * cal.get(Calendar.DATE);
		 */
		SimpleDateFormat df = new SimpleDateFormat("HHmmss");// 设置日期格式
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		// System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		// String date = y + "-" + m + "-" + d;
		System.out
				.println("___________________________________________________");
		System.out.println("\n\t\t     品如服装店【结账单】\t\t   \n\t\t\t\t\t\t   ");
		System.out.println("\t  NO:" + cus.getCtid() + df.format(new Date())
				+ "\t\t\t\t\t   ");
		System.out.println("\t\t\t\t\t\t   \n\t  时间：" + df2.format(new Date())
				+ "\t\t\t\t   ");
		System.out.println("\t---------------------------------------"
				+ "\t   ");
		List<Bill> selectAllBill = this.service.selectAllBill();
		System.out.println("\t衣服名称\t\t数量\t衣服价格\t小计   \t   \n\t\t\t\t\t\t   ");
		double sum = 0;
		for (Bill bill : selectAllBill) {
			System.out.println("\t" + bill.getClo().getClname() + "\t\t"
					+ bill.getBnum() + "\t" + bill.getClo().getPrice() + "\t"
					+ bill.getBsum() + "\t   ");
			sum += bill.getBsum();
		}
		System.out.println("\t\t\t\t\t\t   \n\t\t\t\t总计：" + sum + "\t   ");
		System.out.println("\t\t\t\t折扣：" + sum * 0.1 + "\t   ");
		System.out.println("\t\t\t\t应收RMB：" + sum * 0.9 + "\t   ");
		System.out.println("\t---------------------------------------"
				+ "\t   ");
		System.out
				.println("\t\t\t\t\t\t   \n\t**************欢迎下次光临**************\t   ");
		System.out
				.println("__________________________________________________");
		double dou = sum * 0.9;

		this.service.addAccounts(cus.getCtname(), df2.format(new Date())
				.toString(), dou);
		return dou;
	}

}
