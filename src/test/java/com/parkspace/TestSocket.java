package com.parkspace;

import org.junit.Test;

import com.parkspace.controller.TestBaseController;

/**
 * @Title: TestSocket.java
 * @Package com.parkspace
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月12日 上午11:32:58</p>
*/

public class TestSocket   extends TestBaseController{

	@Test
	public void socket() {
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
