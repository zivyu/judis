package com.juanpi.xuanyuan.judis;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.juanpi.judis.command.Commands;
import com.juanpi.judis.command.ListResultCommand;

/**
 *
 * @author zuqiang
 * @data 2016年3月8日  下午6:11:02
 */
public class JudisListTest  extends AbstractJuedisClientTest{
	
	@Test
	public void testAppend() throws IOException {
		//StringCommand command = new StringCommand();
		//Assert.assertEquals("bar",command.execute(connect, Commands.get, "foo"));
		//System.out.println(command.execute(connect, Commands.get, "ss"));
		
		ListResultCommand command = new ListResultCommand();
		List<String> list = command.execute(pool.getConnection(), Commands.cluster, "slots");
		System.out.println(list.size());
		for(int i = 0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		
	}
	
}
