package com.juanpi.judis.command;

import java.util.List;

import com.juanpi.judis.io.RedisInputStream;
import com.juanpi.judis.io.RedisOutputStream;
import com.juanpi.judis.util.ProtocolUtil;
import com.juanpi.judis.util.TypeUtil;

/**
 * 所有命令实现类的抽象实现，其中封装了命令执行的抽象过程。
 *
 * @author zuqiang
 * @data 2016年2月1日  下午4:57:15
 */
public abstract class AbstractCommand<T> implements Command<T>{
	
	private static final String COMMAND_SEPARATOR = "_";
	private static final String SPACE = " ";
	
	@SuppressWarnings("unchecked")
	@Override
	public T execute(RedisOutputStream outputStream,RedisInputStream inputStream, Commands command,Object... arguments) {
		T result = null;
		try {
			String commandString = command.name();
			if (command.name().indexOf(COMMAND_SEPARATOR) > 0) {
				commandString = command.name().replace(COMMAND_SEPARATOR, SPACE);
			}
			byte[][] argumentBytes = new byte[arguments.length][];
			for (int i = 0; i < arguments.length; i++) {
				if (arguments[i] instanceof byte[]) {
					argumentBytes[i] = (byte[]) arguments[i];
				} else if (List.class.isAssignableFrom(arguments[i].getClass())) {
					List<?> list = (List<?>) arguments[i];
					byte[][] extendArgumentBytes = new byte[arguments.length + list.size() - 1][];
					System.arraycopy(argumentBytes, 0, extendArgumentBytes, 0, i);
					for (int j = 0; j < list.size(); j++) {
						extendArgumentBytes[i++] = TypeUtil.stringToBytes(list.get(j).toString());
					}
					argumentBytes = extendArgumentBytes;
				} else if (arguments[i].getClass().isArray()) {
					Object[] array = (Object[]) arguments[i];
					byte[][] extendArgumentBytes = new byte[arguments.length + array.length - 1][];
					System.arraycopy(argumentBytes, 0, extendArgumentBytes, 0, i);
					for (int j = 0; j < array.length; j++) {
						extendArgumentBytes[i++] = TypeUtil.stringToBytes(array[j].toString());
					}
					argumentBytes = extendArgumentBytes;
				} else {
					argumentBytes[i] = TypeUtil.stringToBytes(arguments[i].toString());
				}
			}
			ProtocolUtil.sendCommand(outputStream, TypeUtil.stringToBytes(commandString), argumentBytes);
			ProtocolUtil.close(outputStream);
			
			result = (T) receive(inputStream, command, arguments);
		} catch (Exception e) {
			throw new RuntimeException("command execute failed!", e);
		}
		return result;
	}
	protected abstract Object receive(RedisInputStream inputStream, Commands command, Object... arguments) throws Exception;
}
