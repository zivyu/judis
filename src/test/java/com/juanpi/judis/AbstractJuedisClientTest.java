package com.juanpi.judis;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.Before;

import com.juanpi.judis.config.JudisProperty;
import com.juanpi.judis.connection.Connection;
import com.juanpi.judis.connection.ConnectionPool;

/**
 *
 * @author zuqiang
 * @data 2016年2月1日  下午5:50:13
 */
public class AbstractJuedisClientTest {
	
	
	protected ConnectionPool pool;
	
	@Before
	public void before() throws UnknownHostException, IOException{
		
		JudisProperty property = new JudisProperty();
		
		pool = new ConnectionPool(property);
		
	}
}
