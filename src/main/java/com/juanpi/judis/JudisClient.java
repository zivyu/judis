package com.juanpi.judis;




/**
 * 客户端的客户端操作接口，其中定义了本组件支持的redis操作
 *
 * @author zuqiang
 * @data 2016年2月1日  下午2:47:45
 */
public interface JudisClient {

	/* ------------ String commands ------------ */
	/*
	 * append,bitcount,bitop,decr,decrby,get,getbit,getrange,getset,incr,incrby
	 * ,
	 * incrbyfloat,mget,mset,msetnx,psetex,set,setbit,setex,setnx,setrange,strlen
	 */
	
	public String get(String key);

	public boolean set(String key, Object value);



}
