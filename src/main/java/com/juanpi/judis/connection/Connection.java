package com.juanpi.judis.connection;

import java.net.InetSocketAddress;
import java.net.Socket;



import com.juanpi.judis.io.RedisInputStream;
import com.juanpi.judis.io.RedisOutputStream;
import com.juanpi.judis.util.ProtocolUtil;


/**
 *
 * @author zuqiang
 * @data 2016年2月1日  上午10:57:52
 */
public class Connection {
	
	private String host = ProtocolUtil.DEFAULT_HOST;
	private int port = ProtocolUtil.DEFAULT_PORT;
	private Socket socket;
	private RedisInputStream redisInputStream;
	private RedisOutputStream redisOutputStream;
	private int connectionTimeout = ProtocolUtil.DEFAULT_TIMEOUT;
	private int soTimeout = ProtocolUtil.DEFAULT_TIMEOUT;
	
	public Connection(){
	}
	
	public Connection(final String host){
		this.host = host;
	}
	
	public Connection(final String host, final int port){
		this.host = host;
		this.port = port;
	}
	
	
	public void connect(){
	 if (!isConnected()) {
		try{
			socket = new Socket();
			socket.setReuseAddress(true);
			socket.setKeepAlive(true);
			socket.setTcpNoDelay(true);
			socket.setSoLinger(true, 0); 
			socket.connect(new InetSocketAddress(host, port), connectionTimeout);
				socket.setSoTimeout(soTimeout);
				redisOutputStream = new RedisOutputStream(
						socket.getOutputStream());
				redisInputStream = new RedisInputStream(socket.getInputStream());
			} catch (Exception e) {
				throw new RuntimeException("socket initialize failed !", e);
			}
		}
	}
	
	public void close(){
		if (isConnected()) {
			try{
				redisOutputStream.flush();
		        socket.close();
			}catch(Exception e){
				 throw new RuntimeException("socket close failed !",e);
			}
		}
	}
	
	public boolean isConnected() {
	    return socket != null && socket.isBound() && !socket.isClosed() && socket.isConnected()
	        && !socket.isInputShutdown() && !socket.isOutputShutdown();
	  }
	
	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getSoTimeout() {
		return soTimeout;
	}

	public void setSoTimeout(int soTimeout) {
		this.soTimeout = soTimeout;
	}

	public Socket getSocket() {
		return socket;
	}

	public RedisInputStream getRedisInputStream() {
		return redisInputStream;
	}

	public RedisOutputStream getRedisOutputStream() {
		return redisOutputStream;
	}
	
	
	
}