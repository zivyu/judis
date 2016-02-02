package com.juanpi.xuanyuan.judis;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.Before;

import com.juanpi.judis.connection.Connection;
import com.juanpi.judis.io.RedisInputStream;
import com.juanpi.judis.io.RedisOutputStream;

/**
 *
 * @author zuqiang
 * @data 2016年2月1日  下午5:50:13
 */
public class AbstractJuedisClientTest {
	
	
	protected Connection connect;
	
	@Before
	public void before() throws UnknownHostException, IOException{
		
		connect = new Connection("192.168.143.31", 6379);
		connect.connect();
		
	}
}
