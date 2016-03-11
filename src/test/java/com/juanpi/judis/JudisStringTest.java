package com.juanpi.judis;

import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.juanpi.judis.command.BooleanCommand;
import com.juanpi.judis.command.Commands;
import com.juanpi.judis.command.ListResultCommand;
import com.juanpi.judis.command.StringCommand;



/**
 *
 * @author zuqiang
 * @data 2016年2月1日  下午5:47:51
 */
public class JudisStringTest extends AbstractJuedisClientTest{
	
	@Test
	public void testAppend() throws IOException {
		//StringCommand command = new StringCommand();
		//Assert.assertEquals("bar",command.execute(connect, Commands.get, "foo"));
		//System.out.println(command.execute(connect, Commands.get, "ss"));
		
		ListResultCommand command = new ListResultCommand();
		List<String> list = command.execute(pool.getConnection(), Commands.hmget, "pet","dog","cat","sss","pig");
		System.out.println(list.size());
		for(int i = 0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		
	}
}

