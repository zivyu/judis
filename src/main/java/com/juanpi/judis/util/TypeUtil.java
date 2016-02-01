package com.juanpi.judis.util;

import java.io.UnsupportedEncodingException;

/**
 *	
 * 工具类，封装了一些类型转换的操作。
 * 
 * @author zuqiang
 * @data 2016年2月1日  下午5:03:16
 */
public abstract class TypeUtil {
	
	private static final String CHARSET = "UTF-8";

	public static byte[] stringToBytes(String s){
		try {
			return s.getBytes(CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String bytesToString(byte[] b){
		try {
			return new String(b,CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
}