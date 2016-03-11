package com.juanpi.judis.connection;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.juanpi.judis.config.JudisProperty;
import org.apache.log4j.Logger;

/**
 *
 *
 *
 * @author zuqiang
 * @data 2016年2月2日  上午10:20:34
 */
public class ConnectionPool {
	
	private final Logger log = Logger.getLogger(ConnectionPool.class);
	
	private LinkedBlockingDeque<Connection> idleConnection;
	
	private final AtomicLong createCount = new AtomicLong(0);
	
	private String host;
	
	private int port;
	
	private int maxTotal;
	
	private int minIdle;
	
	private int maxIdle;
	
	private long maxWaitMillis;
	
	
	public ConnectionPool(JudisProperty property){
		
		//初始化一些参数
		this.maxTotal = property.getMaxTotal();
		this.minIdle = property.getMinIdle();
		this.maxIdle = property.getMaxIdle();
		this.maxWaitMillis = property.getMaxWaitMillis();
		this.host = property.getHost();
		this.port = property.getPort();
		
		//
		idleConnection = new LinkedBlockingDeque<Connection>();
		
		//确保小空闲
		ensureIdle(minIdle);
		
		log.info("init connectPool finish !!!");
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
		Connection connection = new Connection(host,port);
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
	
}
