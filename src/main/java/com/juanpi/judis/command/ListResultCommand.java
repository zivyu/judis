package com.juanpi.judis.command;

import java.util.ArrayList;
import java.util.List;

import com.juanpi.judis.io.RedisInputStream;
import com.juanpi.judis.util.ProtocolUtil;

/**
 *
 * @author zuqiang
 * @data 2016年2月15日  下午3:55:35
 */
public class ListResultCommand extends AbstractCommand<List<String>>{

	@Override
	protected List<String> receive(RedisInputStream inputStream, Commands command,
			Object... arguments) throws Exception {
		String response = inputStream.readLine();
		List<String> list = new ArrayList<String>();
		if (ProtocolUtil.isArrayLengthResultOk(response)) {
			int length = Integer.valueOf(ProtocolUtil.extractResult(response));
			for (int i = 0; i < length; i++) {
				String line = inputStream.readLine();
				if (ProtocolUtil.isStringLengthResultOk(line)&&Integer.valueOf(ProtocolUtil.extractResult(line)) > 0) {
					String str = inputStream.readLine();
					list.add(str);
				}else{
					list.add(null);
				}
			}
		}else{
			throw new RuntimeException(ProtocolUtil.extractResult(response));
		}
		return list;
	}
}
