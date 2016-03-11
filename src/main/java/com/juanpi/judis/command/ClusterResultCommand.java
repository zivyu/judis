package com.juanpi.judis.command;

import java.io.IOException;
import java.util.Map;

import com.juanpi.judis.io.RedisInputStream;
import com.juanpi.judis.util.ProtocolUtil;

/**
 * 
 * @author zuqiang
 * @data 2016年3月9日  上午10:32:52
 */
public class ClusterResultCommand extends AbstractCommand<Map<String,String>>{

	@Override
	protected Map<String,String> receive(RedisInputStream inputStream, Commands command,
			Object... arguments) throws Exception {
		String response = inputStream.readLine();
		if (ProtocolUtil.isArrayLengthResultOk(response)) {
			int length = Integer.valueOf(ProtocolUtil.extractResult(response)); 
			for(int i = 0;i < length;i++){
				String line = inputStream.readLine();
				if(ProtocolUtil.isArrayLengthResultOk(response)){
				}
			}
		}
		return null;
	}

}
