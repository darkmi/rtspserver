/**
 * 
 */
package com.darkmi.vvs;

import junit.framework.TestCase;

/**
 * @Description TODO
 * @author slaton.wu@gmail.com
 * @date 2012-7-20下午5:15:19
 *
 */
public class AppTest extends TestCase{

	static VerifyCodePool<String> pool = null;
	@Override
	protected void setUp() throws Exception {
		pool = new VerifyCodePool<String>();
	}

	
	public void add() throws InterruptedException{
		pool.put("key1111", "123123");
	}
	
	public void get(){
		System.out.println("第一次"+pool.get("key1111"));
	}
}