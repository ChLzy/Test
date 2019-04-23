package com.chinasofti.control;

import java.sql.SQLException;
import java.util.List;

import com.chinasofti.CssService.CssService;
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
	public void start() throws SQLException {
		while (true) {
			this.v.uiView();
			int select = this.ui.getInt("请选择:");
			if (select == 1) {
				this.loginc();
				while (true) {
					this.v.cusView();
					int i = this.ui.getInt("请选择:");
					if (i == 0) {
						System.out.println("系统已退出！");
						System.exit(0);
					} else if (i == 1) {
						this.selectAllClothes();
						this.selectClothes();

					} else if (i == 2) {
						this.selectLike();
						while (true) {
							int selectid = this.ui.getInt("请输入您心仪的衣服的id（0退出）:");
							if (selectid == 0) {
								break;
							}
							clo = this.service.selectClothesById(selectid);
							if (clo == null) {
								System.out.println("没有此衣服的信息，请重新输入!");
								continue;
							}
							System.out.println("衣服信息：");
							System.out.println(clo);
							int bnum = this.ui.getInt("请输入数量：");
							String isCheng = this.service.addBillById(selectid,
									bnum);
							System.out.println(isCheng);
						}
					} else if (i == 3) {
						this.selectAllBill();
					} else if (i == 4) {
						this.updatePassword();
					} else if (i == 5) {

					}
				}
			} else if (select == 2) {
				this.loginE();
				if (emp.getDe().getDname().equals("普通员工")) {
					while (true) {
						this.v.saleView();
						int j = this.ui.getInt("请选择:");
						if (j == 0) {
							System.out.println("系统已退出！");
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
							this.addVip();
						} else if (j == 6) {
							this.updateCusState();
						}
					}
				} else if (emp.getDe().getDname().equals("经理")) {
					while (true) {
						this.v.managerView();
						int k = this.ui.getInt("请选择：");
						if (k == 0) {
							System.out.println("系统已终止！");
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
						}
					}
				}

			}

		}
	}

	// 选择衣服
	private void selectClothes() throws SQLException {
		while (true) {
			int selectid = this.ui.getInt("请输入您心仪的衣服的id（0退出）:");
			if (selectid == 0) {
				break;
			}
			List<Bill> selectAllBill = this.service.selectAllBill();
			for (Bill bill : selectAllBill) {
				if (bill.getClid() == selectid) {
					String s = this.ui.getString("您选择的衣服已存在，是否继续添加（y/n）?");
					if (s.equals("y")) {
						this.service.updateBill(selectid,
								this.ui.getInt("请输入要增加的数量："));
					}
					break;
				}
			}

			clo = this.service.selectClothesById(selectid);

			if (clo.getClname() == null) {
				System.out.println("输入有误，请重新输入！");
				continue;
			}
			System.out.println("衣服信息：");
			System.out.println(clo);
			int bnum = this.ui.getInt("请输入数量：");
			String isCheng = this.service.addBillById(selectid, bnum);
			System.out.println(isCheng);

		}

	}

	// 顾客模糊查询
	private void selectLike() throws SQLException {
		List<Clothes> list = this.service.selectLikeClothes(this.ui
				.getString("请输入关键字："));
		for (Clothes clothes : list) {
			System.out.println(clothes);
		}
	}

	// 删除员工
	private void removeEmployee() throws SQLException {
		List<Employee> list = this.service.selectAllEmployee();
		for (Employee employee : list) {
			System.out.println(employee);
		}
		String s = this.service.deleteEmployee(this.ui.getInt("请输入要删除员工的id："));
		System.out.println(s);
	}

	// 添加员工
	private void addEmployee() throws SQLException {
		Employee e = new Employee();
		while (true) {
			int i = this.ui.getInt("请输入员工id（长度8）:");
			Employee e2 = this.service.loginE(i);
			if (e2 == null) {
				e.setEmid(i);
				break;
			} else {
				System.out.println("员工id已存在，请重新输入！");
			}
		}
		e.setEname(this.ui.getString("请输入姓名："));
		e.setEmsex(this.ui.getString("请输入性别："));
		e.setDeptno(this.ui.getInt("请输入部门编号："));
		while (true) {
			String s = this.ui.getString("请输入密码：");
			String ss = this.ui.getString("请再次输入密码：");
			if (s.equals(ss)) {
				e.setEpassword(s);
				break;
			} else {
				System.out.println("两次密码不一致，请重新输入！");
			}

		}
		this.service.addEmployee(e);

	}

	// 经理删除衣服
	private void removeClothes() throws SQLException {
		this.selectAllClothes();
		String s = this.service.deleteClothes(this.ui.getInt("请输入要删除的衣服id:"));
		System.out.println(s);
	}

	// 经理修改衣服价格
	private void updateCloPrice() throws SQLException {
		this.selectAllClothes();
		String s = this.service.updateClothes(this.ui.getInt("请输入要修改的衣服id:"),
				this.ui.getDouble("请输入修改后的单价："));
		System.out.println(s);
	}

	// 经理根据id查询衣服
	private int selectClothesByid() throws SQLException {

		Clothes c = this.service.selectClothesById(this.ui.getInt("请输入衣服id："));
		if (c != null) {
			return 1;
		} else {
			return 0;
		}
	}

	// 经理添加衣服信息
	private void addManClothes() throws SQLException {
		while (true) {
			while (true) {
				int cloid = this.ui.getInt("请输入衣服编号：");
				if (selectClothesByid() == 1) {
					System.out.println("编号已存在，请重新输入！");
				} else if (selectClothesByid() == 0) {
					clo.setClid(cloid);
					break;
				}
			}
			clo.setClname(this.ui.getString("请输入衣服名称："));
			clo.setPrice(this.ui.getDouble("请输入衣服价格："));
			clo.getSea().setSeaid(this.ui.getInt("请输入衣服适合季节id："));
			System.out.println("添加的衣服信息如下：" + "\n" + clo.toString());
			String s = this.ui.getString("确认添加？（y/n）");
			if (s.equals("y")) {
				String addClothes = this.service.addClothes(clo);
				System.out.println(addClothes);
			} else {
				System.out.println("取消添加！");
			}
		}

	}

	// 客户冻结状态
	private void updateCusState() throws SQLException {
		System.out.println(">>>账户冻结状态");
		System.out.println("1、冻结");
		System.out.println("2、解冻");
		System.out.println("0、退出");
		int select = this.ui.getInt("请选择：");
		if (select == 0) {

		} else if (select == 1) {
			String s = this.service.updateFrozenById(1);
			System.out.println(s);
		} else if (select == 2) {
			String s = this.service.updateFrozenById(0);
			System.out.println(s);
		}

	}

	// 给客户开vip
	private void addVip() throws SQLException {
		String addVip = this.service.addVip(this.ui.getInt("请输入开会员的客户id:"));
		System.out.println(addVip);

	}

	// 给客户充值
	private void addCusMoney() throws SQLException {
		String addMoney = this.service.addMoney(this.ui.getInt("请输入id"),
				this.ui.getDouble("请输入要充值的金额："));
		System.out.println(addMoney);

	}

	// 售货员给顾客开户
	private void addCustomer() throws SQLException {
		cus.setCtid(this.ui.getInt("请输入账号（4位）："));
		cus.setBalance(0);
		cus.setCtname(this.ui.getString("请输入顾客姓名："));
		cus.setCvip(0);
		cus.setFrozen(0);
		while (true) {
			String s = this.ui.getString("请输入密码：");
			String s2 = this.ui.getString("请再次输入密码：");
			if (s.equals(s2)) {
				cus.setCtpassword(s);
				break;
			} else {
				System.out.println("两次密码不一致！");
			}
		}
		String addCustomer = this.service.addCustomer(cus);
		System.out.println(addCustomer);
	}

	// 修改员工密码
	private void updatePasswordE() throws SQLException {
		while (true) {
			System.out.println(">>>修改密码");
			String oldPassword = this.ui.getString("请输入旧密码（n退出）：");
			if (oldPassword.equals("n")) {
				break;
			}
			if (emp.getEpassword().equals(oldPassword)) {
				while (true) {
					String newPassword = this.ui.getString("请输入新密码(6-15)：");
					if (newPassword.length() > 5 && newPassword.length() < 16) {
						String updatePasswordByEid = this.service
								.updatePasswordByEid(newPassword, emp.getEmid());
						System.out.println(updatePasswordByEid);
						break;
					} else {
						System.out.println("密码长度6-15");
					}
				}
				break;
			} else {
				System.out.println("密码不正确，无法修改！");
			}

		}

	}

	// 查询所有客户
	private void selectAllCustomer() throws SQLException {
		List<Customer> sac = this.service.selectAllCustomer();
		for (Customer customer : sac) {
			System.out.println(customer);
		}

	}

	// 员工登录
	private void loginE() throws SQLException {
		while (true) {
			int id = this.ui.getInt("请输入id：");
			String password = this.ui.getString("请输入密码：");
			emp = this.service.loginE(id);
			if (emp.getEmid() == id && emp.getEpassword().equals(password)) {
				System.out.println("欢迎" + emp.getEname() + "登录");
				break;
			} else {
				System.out.println("输入有误，请重新输入！");
			}
		}

	}

	// 顾客登录
	private void loginc() throws SQLException {
		while (true) {
			int account = this.ui.getInt("请输入账号：");
			String password = this.ui.getString("请输入密码：");
			cus = this.service.loginC(account);
			if (cus.getCtid() == account
					&& cus.getCtpassword().equals(password)) {
				System.out.println("欢迎" + cus.getCtname() + "光临品如服装店");
				break;
			}
			System.out.println("输入有误，请重新输入！");
		}

	}

	// 顾客修改密码
	private void updatePassword() throws SQLException {
		while (true) {
			System.out.println(">>>修改密码");
			String oldPassword = this.ui.getString("请输入旧密码（n退出）：");
			if (oldPassword.equals("n")) {
				break;
			}
			if (cus.getCtpassword().equals(oldPassword)) {
				while (true) {
					String newPassword = this.ui.getString("请输入新密码(6-15)：");
					if (newPassword.length() > 5 && newPassword.length() < 16) {
						String updatePasswordByCid = this.service
								.updatePasswordByCtid(newPassword,
										cus.getCtid());
						System.out.println(updatePasswordByCid);
						break;
					} else {
						System.out.println("密码长度6-15");
					}
				}
				break;
			} else {
				System.out.println("密码不正确，无法修改！");
			}

		}

	}

	// 查询所有服装
	private void selectAllClothes() throws SQLException {
		System.out.println(">>>品如服装展");
		List<Clothes> list = this.service.selectAllClothes();
		for (Clothes clothes : list) {
			System.out.println(clothes);
		}
	}

	// 查看购物车所有信息
	private void selectAllBill() throws SQLException {
		while (true) {
			System.out.println(">>>购物车");
			List<Bill> list = this.service.selectAllBill();
			for (Bill bill : list) {
				System.out.println(bill);
			}
			this.v.shopView();
			int select = this.ui.getInt("请选择：");
			if (select == 1) {
				int i = this.ui.getInt("请输入要修改的id");
				int i2 = this.ui.getInt("请输入要修改的数量");
				if (i2 > 0) {
					String s = this.service.updateBill(i, i2);
					System.out.println(s);
				} else if (i2 == 0) {
					String bclothes = this.service.addBillById(i, i2);
					System.out.println(bclothes);
				}
			} else if (select == 2) {
				bil.toString();
				System.out.println("感谢使用！");
				System.exit(0);
			} else if (select == 0) {
				break;
			}
		}

	}

}
