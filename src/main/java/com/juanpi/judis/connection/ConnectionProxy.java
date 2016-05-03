package com.juanpi.judis.connection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *	Connection的代理类
 *
 * @author zuqiang
 * @data 2016年3月15日  下午8:54:55
 */
@SuppressWarnings("unused")
public class ConnectionProxy implements InvocationHandler{
	private final static Logger LOG = LoggerFactory.getLogger(ConnectionProxy.class);
	
	private Connection connection;
	private boolean inUse = false;
	
	
	public ConnectionProxy(Connection connection,boolean inUse){
		this.connection = connection;
		this.inUse = inUse;
	}
	
	public Connection getConnection(){
		Connection conn = (Connection) Proxy.newProxyInstance(this.getClass().getClassLoader(), Connection.class.getInterfaces(), this);
		return conn;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if(method.getName().equals("close")){
			inUse = false;
			return null;
		}else{
			return method.invoke(connection, args);
		}
	}
	
	 public boolean isInUse() { 
         return inUse; 
     }

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	} 
	 
}
