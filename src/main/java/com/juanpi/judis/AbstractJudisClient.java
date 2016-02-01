package com.juanpi.judis;

/**
 *
 * @author zuqiang
 * @data 2016年2月1日  下午2:50:32
 */
public abstract class AbstractJudisClient implements JudisClient{

	@Override
	public String get(String key) {
		return null;
	}

	@Override
	public boolean set(String key, Object value) {
		return false;
	}

}
