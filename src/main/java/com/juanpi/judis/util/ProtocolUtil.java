package com.juanpi.judis.util;


import java.io.IOException;

import com.juanpi.judis.io.RedisOutputStream;

/**
 * 协议工具类，封装了一些响应解析的操作
 *
 * @author zuqiang
 * @data 2016年2月1日  下午2:31:03
 */
public abstract class ProtocolUtil {
	
	private static final int defaultNumber = 1;

	private static final String trueResultPrefix = "+";

	private static final String falseResultPrefix = "-";

	private static final String intResultPrefix = ":";

	private static final String stringLengthResultPrefix = "$";

	private static final String arrayLengthResultPrefix = "*";

	private static final byte stringLengthResultPrefixByte = '$';

	private static final byte arrayLengthResultPrefixByte = '*';

	public static void sendCommand(RedisOutputStream outputStream, final byte[] command, final byte[]... args) {
		try {
			outputStream.write(arrayLengthResultPrefixByte);
			outputStream.writeIntCrLf(args.length + 1);
			outputStream.write(stringLengthResultPrefixByte);
			outputStream.writeIntCrLf(command.length);
			outputStream.write(command);
			outputStream.writeCrLf();
			for (final byte[] arg : args) {
				outputStream.write(stringLengthResultPrefixByte);
				outputStream.writeIntCrLf(arg.length);
				outputStream.write(arg);
				outputStream.writeCrLf();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void close(RedisOutputStream outputStream){
		try {
			outputStream.flush();
		} catch (IOException e) {
			throw new RuntimeException("outputStream close exception",e);
		}
	}

	public static boolean isOk(String response) {
		return response != null && response.startsWith(trueResultPrefix);
	}

	public static boolean isError(String response) {
		return response != null && response.startsWith(falseResultPrefix);
	}

	public static boolean isStringLengthResultOk(String response) {
		return response != null && response.startsWith(stringLengthResultPrefix);
	}

	public static boolean isIntResultOk(String response) {
		return response != null && response.startsWith(intResultPrefix);
	}

	public static boolean isArrayLengthResultOk(String response) {
		return response != null && response.startsWith(arrayLengthResultPrefix);
	}
	
	public static boolean intResultToBooleanResult(String response) {
		return intResultToBooleanResult(defaultNumber, response);
	}

	public static boolean intResultToBooleanResult(int number, String response) {
		return response != null && isIntResultOk(response) && intResultToBooleanResult(number, Integer.valueOf(extractResult(response)));
	}
	
	public static boolean intResultToBooleanResult(int intResult) {
		return intResultToBooleanResult(defaultNumber, intResult);
	}

	public static boolean intResultToBooleanResult(int number, int intResult) {
		return intResult >= number;
	}

	public static String extractResult(String response) {
		return (response == null || response.length() == 0) ? null : response.substring(1);
	}
	
}
