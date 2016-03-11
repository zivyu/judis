package com.juanpi.judis;

import org.apache.log4j.Logger;

import com.juanpi.judis.cache.CommandCache;
import com.juanpi.judis.command.Command;
import com.juanpi.judis.command.Commands;
import com.juanpi.judis.util.SlotMap;


/**
 *
 * @author zuqiang
 * @data 2016年2月1日  下午2:50:32
 */
public abstract class AbstractJudisClient implements JudisClient{

	private final Logger log = Logger.getLogger(AbstractJudisClient.class); 
	
	private CommandCache commandCache;
	private SlotMap slotMap;
	
	
	//根据集群模式初始化连接池等
	
	public AbstractJudisClient(){
		commandCache = CommandCache.getInstance();
		slotMap = SlotMap.getInstance();
	}
	
	private <T> T executeCommand(Class<? extends Command<T>> commandClass,Commands command,Object... arguments){
		
		Command commandStance = commandCache.getCommand(commandClass);
		
		
		
		
		
		return null;
		
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
