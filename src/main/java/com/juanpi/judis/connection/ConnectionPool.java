package com.juanpi.judis.connection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;

import com.juanpi.judis.config.JudisProperty;

/**
 *
 *
 *
 * @author zuqiang
 * @data 2016年2月2日  上午10:20:34
 */
public class ConnectionPool {
	
	private final Logger log = Logger.getLogger(ConnectionPool.class);
	
	private LinkedBlockingDeque<ConnectionProxy> idleConnection;
	
	private LinkedBlockingDeque<ConnectionProxy> useConnection;
	
	private final AtomicLong createCount = new AtomicLong(0);
	
	private CountDownLatch count = new CountDownLatch(0);
	
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
		idleConnection = new LinkedBlockingDeque<ConnectionProxy>();
		useConnection = new LinkedBlockingDeque<ConnectionProxy>();
		
		//确保小空闲
		ensureIdle(minIdle);
		
		log.info("init connectPool finish !!!");
	}
	
	
	public Connection getConnection(){
		Connection connection = null;
		ConnectionProxy  connProxy = idleConnection.pollFirst();
		if(connection == null){
			connProxy = create();
		}
		if(connProxy == null){
			if(maxWaitMillis < 0){
				connProxy = idleConnection.pollFirst();
			}else{
				try {
					connProxy = idleConnection.pollFirst(maxWaitMillis, TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					throw new RuntimeException("Queue exception",e);
				}
			}
		}
		if(connProxy == null){
			throw new RuntimeException("Timeout waiting for idle object");
		}
		connection = connProxy.getConnection();
		connProxy.setInUse(true);
		return connection;
	}
	
	private ConnectionProxy create(){
		long newCreateCount = createCount.incrementAndGet();
		if(newCreateCount>maxTotal){
			createCount.decrementAndGet();
			return null;
		}
		ConnectionProxy connProxy = new ConnectionProxy(new Connection(host,port),false);
		idleConnection.add(connProxy);
		useConnection.add(connProxy);
		return connProxy;
	}
	
	private void ensureIdle(int number){
		if(number<1){
			return;
		}
		while(idleConnection.size()<minIdle){
			ConnectionProxy connProxy = create();
			if(connProxy == null){
				break;
			}
		}
	}
	
	class CheckIdle implements Runnable{

		@Override
		public void run() {
			while(true){
				try {
					count.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
}
