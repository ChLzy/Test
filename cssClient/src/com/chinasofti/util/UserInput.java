package com.chinasofti.util;

import java.util.Scanner;

public class UserInput {
	//接收用户输入的内容String
		public String getString(String msg){
			System.out.println(msg);
			Scanner sc=new Scanner(System.in);
			return sc.next(); 
		}
		//接收整数
		public int getInt(String msg){
			while(true){
				try {
					System.out.println(msg);
					Scanner sc=new Scanner(System.in);
					return sc.nextInt();
				} catch (Exception e) {
					System.out.println("输入内容格式不正确，请输入整数类型！");
				}
			}
		}
		//接收创建账号
		public int getVip(String msg){
			while(true){
				try {
					System.out.println(msg);
					Scanner sc=new Scanner(System.in);
					int i=sc.nextInt();
					if(i>999 && i<10000){
						return sc.nextInt();
					}else{
						System.out.println("输入内容格式不正确，请输入四位整数！");
					}
					
				} catch (Exception e) {
					System.out.println("输入内容格式不正确，请输入整数类型！");
				}
			}
		}
		//验证密码
		public String getPass(String msg){
			while(true){
				System.out.println(msg);
				Scanner sc=new Scanner(System.in);
				String s=sc.next();
				if(s.length()<8 || s.length()>15){
					System.out.println("密码长度8—15");
				}else{
					return sc.next();
				}
				
			}
			
			
		}
		//接收浮点数
		public double getDouble(String msg){
			while(true){
				try {
					System.out.println(msg);
					Scanner sc=new Scanner(System.in);
					return sc.nextDouble();
				} catch (Exception e) {
					System.out.println("输入内容格式不正确，请输入小数类型！");
				}
			}
		}
}
