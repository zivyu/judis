package com.juanpi.judis.connection;

/**
 *
 * @author zuqiang
 * @data 2016年2月2日  上午10:20:34
 */
public interface ConnectionPool {
	
	public Connection getConnection();
	public void returnConnection();
	
	
}
