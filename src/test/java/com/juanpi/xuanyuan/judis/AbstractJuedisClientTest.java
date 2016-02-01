package com.juanpi.xuanyuan.judis;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import com.juanpi.judis.io.RedisInputStream;
import com.juanpi.judis.io.RedisOutputStream;

/**
 *
 * @author zuqiang
 * @data 2016年2月1日  下午5:50:13
 */
public class AbstractJuedisClientTest {
	
	
	protected RedisOutputStream outputStream;
	protected RedisInputStream inputStream;
	
	@Before
	public void before() throws UnknownHostException, IOException{
		Socket socket = new Socket("192.168.143.31", 6379);
		outputStream = new RedisOutputStream(socket.getOutputStream());
		inputStream = new RedisInputStream(socket.getInputStream());
	}
}
