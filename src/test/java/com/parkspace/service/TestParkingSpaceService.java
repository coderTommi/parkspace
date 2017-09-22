package com.parkspace.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.parkspace.db.rmdb.entity.ParkingSpace;

/**
 * @Title: TestParkingSpaceService.java
 * @Package com.parkspace.service
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午10:15:01</p>
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/dispatcher-servlet.xml"}) 
public class TestParkingSpaceService {
	@Resource
	private IParkingSpaceService parkingSpaceService;
	@Test
	public void testGetParkingSpace(){
		ParkingSpace parkingSpace = parkingSpaceService.getParkingSpace("1");
		System.out.println(parkingSpace);
	}
}
