package com.juanpi.judis.connection;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


/**
 *
 *
 *
 * @author zuqiang
 * @data 2016年2月2日  上午10:20:34
 */
public class ConnectionPool {
	
	private LinkedBlockingDeque<Connection> idleConnection; 
	
	private final AtomicLong createCount = new AtomicLong(0);
	
	private int maxTotal = 8;
	
	private int minIdle = 0;
	
	private int maxIdle = 8;
	
	private long maxWaitMillis = -1L;
	
	public ConnectionPool(String host,int port){
		idleConnection = new LinkedBlockingDeque<Connection>();
		ensureIdle(minIdle);
	}
	
	public ConnectionPool(String host,int port,int maxTotal,int minIdle,int maxIdle,long maxWaitMillis){
		idleConnection = new LinkedBlockingDeque<Connection>();
		this.maxTotal = maxTotal;
		this.minIdle = minIdle;
		this.maxIdle = maxIdle;
		this.maxWaitMillis = maxWaitMillis;
		ensureIdle(minIdle);
	}
	
	
	public Connection getConnection(){
		Connection connection = null;
		connection = idleConnection.pollFirst();
		if(connection == null){
			connection = create();
		}
		if(connection == null){
			if(maxWaitMillis < 0){
				connection = idleConnection.pollFirst();
			}else{
				try {
					connection = idleConnection.pollFirst(maxWaitMillis, TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					throw new RuntimeException("Queue exception",e);
				}
			}
		}
		if(connection == null){
			throw new RuntimeException("Timeout waiting for idle object");
		}
		return connection;
	}
	
	private Connection create(){
		long newCreateCount = createCount.incrementAndGet();
		if(newCreateCount>maxTotal){
			createCount.decrementAndGet();
			return null;
		}
		Connection connection = new Connection();
		return connection;
	}
	
	
	private void ensureIdle(int number){
		if(number<1){
			return;
		}
		while(idleConnection.size()<minIdle){
			Connection connection = create();
			if(connection == null){
				break;
			}
			idleConnection.add(connection);
		}
	}
	
	
	public void returnConnection(){
		
	}
	
	
}
