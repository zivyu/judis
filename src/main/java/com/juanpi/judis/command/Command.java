package com.juanpi.judis.command;

import com.juanpi.judis.connection.Connection;

/**
 *
 * 命令接口，这是命令模式中的命令接口
 *
 * @author zuqiang
 * @data 2016年2月1日  下午4:52:56
 */
public interface Command<T> {
	
	public T execute(Connection connection,Commands command,Object... arguments);
	
}
