package com.juanpi.judis.command;

import com.juanpi.judis.io.RedisInputStream;
import com.juanpi.judis.util.ProtocolUtil;

/**
 * 返回结果为boolean类型的命令的实现类，如set
 *
 * @author zuqiang
 * @data 2016年2月1日  下午5:16:37
 */
public class BooleanCommand extends AbstractCommand<Boolean>{

	@Override
	protected Boolean receive(RedisInputStream inputStream, Commands command,
			Object... arguments) throws Exception {
		String response = inputStream.readLine();
		if (ProtocolUtil.isOk(response)) {
			return true;
		} else {
			throw new RuntimeException(ProtocolUtil.extractResult(response));
		}
	}
	
}
