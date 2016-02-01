package com.juanpi.judis.connection;

import com.juanpi.judis.io.RedisInputStream;
import com.juanpi.judis.io.RedisOutputStream;


/**
 *
 * @author zuqiang
 * @data 2016年2月1日  上午10:57:52
 */
public interface Connection {

	public RedisOutputStream getOutputStream();

	public RedisInputStream getInputStream();

	public boolean isClosed();

	public void close();

}