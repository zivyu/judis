package com.juanpi.judis.cache;

import java.util.HashMap;
import java.util.Map;


import com.juanpi.judis.command.Command;

/**
 * 缓存命令实例对象
 *
 * @author zuqiang
 * @data 2016年3月10日  下午2:28:05
 */
public class CommandCache {
	
	private Map<Class<? extends Command<?>>,Command<?>> cacheMap;
	
	
	private CommandCache(){
		cacheMap = new HashMap<>();
	}
	
	private static class Holder{
		private static CommandCache singleton = new CommandCache();
	}
	
	public static CommandCache getInstance(){
		return Holder.singleton;
	}
	
	public Command<?> getCommand(Class<? extends Command<?>> clazz){
		Command<?> commandInstance = cacheMap.get(clazz);
		if(commandInstance != null){
			return commandInstance;
		}
		synchronized (this) {
			commandInstance = cacheMap.get(clazz);
			if(commandInstance != null){
				return commandInstance;
			}
			try {
				commandInstance = clazz.getConstructor(new Class<?>[]{}).newInstance();
			} catch (Exception e) {
				throw new RuntimeException("build command failed !!!");
			}
			return commandInstance;
		}
	}
	
}
