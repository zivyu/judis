package com.juanpi.xuanyuan.judis;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.juanpi.judis.command.Commands;
import com.juanpi.judis.command.StringCommand;



/**
 *
 * @author zuqiang
 * @data 2016年2月1日  下午5:47:51
 */
public class JudisStringTest extends AbstractJuedisClientTest{
	
	@Test
	public void testAppend() throws IOException {
		StringCommand command = new StringCommand();
		System.out.println(command.execute(outputStream, inputStream, Commands.get, "foo"));
		Assert.assertEquals("bar",command.execute(outputStream, inputStream, Commands.get, "foo"));
	}
}
