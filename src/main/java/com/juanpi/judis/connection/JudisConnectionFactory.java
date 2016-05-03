package com.juanpi.judis.connection;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;

/**
 *
 * @author zuqiang
 * @data 2016年4月29日  下午6:02:56
 */
public class JudisConnectionFactory implements PooledObjectFactory<Connection>{

	/* (non-Javadoc)
	 * @see org.apache.commons.pool2.PooledObjectFactory#makeObject()
	 */
	@Override
	public PooledObject<Connection> makeObject() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.pool2.PooledObjectFactory#destroyObject(org.apache.commons.pool2.PooledObject)
	 */
	@Override
	public void destroyObject(PooledObject<Connection> p) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.pool2.PooledObjectFactory#validateObject(org.apache.commons.pool2.PooledObject)
	 */
	@Override
	public boolean validateObject(PooledObject<Connection> p) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.pool2.PooledObjectFactory#activateObject(org.apache.commons.pool2.PooledObject)
	 */
	@Override
	public void activateObject(PooledObject<Connection> p) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.pool2.PooledObjectFactory#passivateObject(org.apache.commons.pool2.PooledObject)
	 */
	@Override
	public void passivateObject(PooledObject<Connection> p) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
