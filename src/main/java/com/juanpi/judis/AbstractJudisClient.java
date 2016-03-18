package com.juanpi.judis;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.juanpi.judis.cache.CommandCache;
import com.juanpi.judis.command.Command;
import com.juanpi.judis.command.Commands;
import com.juanpi.judis.connection.ConnectionPool;
import com.juanpi.judis.util.SlotMap;


/**
 *
 * @author zuqiang
 * @data 2016年2月1日  下午2:50:32
 */
public abstract class AbstractJudisClient implements JudisClient{

	private static final Logger LOG = LoggerFactory.getLogger(AbstractJudisClient.class); 
	
	private CommandCache commandCache;
	private SlotMap slotMap;
	
	
	//根据集群模式初始化连接池等
	
	public AbstractJudisClient(){
		commandCache = CommandCache.getInstance();
		slotMap = SlotMap.getInstance();
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	private <T> T executeCommand(Class<? extends Command<T>> commandClass,Commands command,Object... arguments){
		Command<?> commandStance = commandCache.getCommand(commandClass);
		String key = (String) arguments[0];
		ConnectionPool pool = slotMap.getPool(key);
		T result = (T) commandStance.execute(pool.getConnection(), command, arguments);
		return result;
	}
 	
	
	
	@Override
	public String get(String key) {
		return null;
	}

	@Override
	public boolean set(String key, Object value) {
		return false;
	}

}
