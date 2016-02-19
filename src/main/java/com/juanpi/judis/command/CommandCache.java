package com.juanpi.judis.command;

import java.util.HashMap;
import java.util.Map;


/**
 * 缓存命令实例
 *
 * @author zuqiang
 * @data 2016年2月16日  上午10:37:42
 */
public class CommandCache {
	
	private Map<Class<? extends Command<?>>,Command<?>> cache = new HashMap<Class<? extends Command<?>>,Command<?>>();
	
	@SuppressWarnings("unchecked")
	public <T> Command<T> getCommand(Class<? extends Command<T>> clazz){
		
		Command<?> commandInstance = cache.get(clazz);
		if(commandInstance != null){
			return (Command<T>) commandInstance;
		}
		synchronized (this) {
			try {
				if(commandInstance == null){
					commandInstance = clazz.getConstructor(new Class<?>[]{}).newInstance();
					cache.put(clazz, commandInstance);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return (Command<T>) commandInstance;
	}
	
}
