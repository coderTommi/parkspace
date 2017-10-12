package com.parkspace;

import org.junit.Test;

import com.parkspace.controller.TestBaseController;

/**
 * @Title: TestParkingScheduler.java
 * @Package com.parkspace
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月11日 上午9:04:01</p>
*/

public class TestParkingScheduler  extends TestBaseController{
	
	@Test
	public void doSomething() {
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
