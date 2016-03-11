package com.juanpi.judis.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.juanpi.judis.connection.ConnectionPool;

/**
 * 映射每个哈希槽所属的Pool
 *
 * @author zuqiang
 * @data 2016年3月11日  上午10:59:12
 */
public class SlotMap {
	
	private Map<String,ConnectionPool> slotMap;
	
	private SlotMap(){
		slotMap = new ConcurrentHashMap<String, ConnectionPool>();
	}
	
	static class Holder{
		public static SlotMap singleton = new SlotMap();
	}
	
	public static SlotMap getInstance(){
		return Holder.singleton;
	}
	
	public void putPool(String slot,ConnectionPool pool){
		slotMap.put(slot, pool);
	}
	
	public ConnectionPool getPool(String key){
		int crc = CRC16.getCRC16(key);
		int slot = crc % 16384;
		return slotMap.get(Integer.toString(slot));
	}
	
	
}
