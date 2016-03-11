package com.juanpi.judis.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author zuqiang
 * @data 2016年3月8日  下午3:13:37
 */
public class JudisProperty {
	
	private static final Logger LOG = LoggerFactory.getLogger(JudisProperty.class);
	
	private String host = "localhost";
	
	private int port = 6379;
	
	private int maxTotal = 8;
	
	private int minIdle = 8;
	
	private int maxIdle = 5;
	
	private long maxWaitMillis = -1L;
	
	public JudisProperty(){
		this(true);
	}
	
	/**
	 * @param auto_load_config 是否自动查找resources下的judis.properties文件，默认自动查找 。
	 * 			当为false时，需要自己setValue。 
	 */
	public JudisProperty(boolean auto_load_config ){
		if(auto_load_config){
			try {
				InputStream in = this.getClass().getResourceAsStream("judis.properties");
				Properties props = new Properties();
				props.load(in);
				setProperties(props);  
			} catch (IOException e) {
				LOG.error("Unable to find or load  judis.properties !!! ",e);
				throw new RuntimeException("Unable to find or load  judis.properties !!!",e);
			}
		}
	}
	
	private void setProperties(Properties props){
		if(props.getProperty("host")!=null){
			host = props.getProperty("host");
		}
		if(props.getProperty("port")!=null){
			port = Integer.valueOf(props.getProperty("port"));
		}
		if(props.getProperty("maxTotal")!=null){
			maxTotal = Integer.valueOf(props.getProperty("maxTotal"));
		}
		if(props.getProperty("minIdle")!=null){
			minIdle = Integer.valueOf(props.getProperty("minIdle"));
		}
		if(props.getProperty("maxIdle")!=null){
			maxIdle = Integer.valueOf(props.getProperty("maxIdle"));
		}
		if(props.getProperty("maxWaitMillis")!=null){
			maxWaitMillis = Long.valueOf(props.getProperty("maxWaitMillis"));
		}
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public long getMaxWaitMillis() {
		return maxWaitMillis;
	}

	public void setMaxWaitMillis(long maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
}
