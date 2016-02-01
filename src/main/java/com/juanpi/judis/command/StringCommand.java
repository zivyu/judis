package com.juanpi.judis.command;

import com.juanpi.judis.io.RedisInputStream;
import com.juanpi.judis.util.ProtocolUtil;
import com.juanpi.judis.util.TypeUtil;

/**
 *
 * @author zuqiang
 * @data 2016年2月1日  下午5:33:42
 */
public class StringCommand extends AbstractCommand<String>{

	@Override
	protected Object receive(RedisInputStream inputStream, Commands command,
			Object... arguments) throws Exception {
		String response = inputStream.readLine();
		String result = null;
		if (ProtocolUtil.isStringLengthResultOk(response)) {
			if (Integer.valueOf(ProtocolUtil.extractResult(response)) > 0) {
				result = TypeUtil.bytesToString(inputStream.readLineBytes());
			}
		} else {
			throw new RuntimeException(ProtocolUtil.extractResult(response));
		}
		return result;
	}

}
