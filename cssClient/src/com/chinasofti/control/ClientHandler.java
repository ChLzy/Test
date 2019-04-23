package com.chinasofti.control;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class ClientHandler implements InvocationHandler {
	//创建ip和端口号
	private String ip;
	private int port;
	
	public ClientHandler(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 创建套接字对象
		Socket client=new Socket(ip,port);
		//获取输出流
		ObjectOutputStream oos=new ObjectOutputStream(client.getOutputStream());
		//具体要使用反射实现代理操作的传输
		//将代理的方法名称发送给服务器
		oos.writeUTF(method.getName());
		oos.flush();
		//将代理的参数类型发送给服务器
		oos.writeObject(method.getParameterTypes());
		oos.flush();
		//将方法的此参数发送给服务器
		oos.writeObject(args);
		oos.flush();
		//服务器返回结果 进行接收
		ObjectInputStream ois=new ObjectInputStream(client.getInputStream());
		return ois.readObject() ;
	}
	
}
