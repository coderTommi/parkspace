package com.parkspace.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.parkspace.db.rmdb.entity.Community;
import com.parkspace.db.rmdb.entity.ParkingSpace;
import com.parkspace.db.rmdb.entity.Zone;

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
	
	public String spaceno = "1";
	
	public String comid = "d2ac2ef6-acad-411a-9b2a-9732d47028b5";
	public String zoneid = "4e73503c-7052-41bc-a716-b8b2d2e32e5e";
	@Test
	public void addParkingSpace(){
		ParkingSpace parkingSpace = new ParkingSpace();
		parkingSpace.setComid(comid);
		parkingSpace.setCreateBy("孙辽东create");
		parkingSpace.setCreateTime(new Date());
		parkingSpace.setMemo("测试这位添加");
		parkingSpace.setModifyBy("孙辽东modify");
		parkingSpace.setModifyTime(new Date());
		parkingSpace.setParkPositionDes("3号楼附近");
		parkingSpace.setParkPositionFloor("-1F");
		parkingSpace.setParkPositionX("100X");
		parkingSpace.setParkPositionY("200Y");
		parkingSpace.setParkPositionZone("A区");
//		parkingSpace.setParkStatus("-1");
//		parkingSpace.setParkType("P");
		parkingSpace.setSpaceno(spaceno);
		parkingSpace.setSpaceOwner("13518678898");
		this.parkingSpaceService.addParkingSpace(parkingSpace);
	}
	@Test
	public void getParkingSpace(){
		ParkingSpace parkingSpace = parkingSpaceService.getParkingSpace(spaceno);
		System.out.println(parkingSpace);
	}
	
	@Test 
	public void updateParkingSpace() {
		ParkingSpace parkingSpace = parkingSpaceService.getParkingSpace(spaceno);
		parkingSpace.setMemo(parkingSpace.getMemo() + "测试修改信息");
		this.parkingSpaceService.updateParkingSpace(parkingSpace);
	}
	
	@Test
	public void enableParkingSpace() {
		this.parkingSpaceService.enableParkingSpace(spaceno, "modifyBy");
	}
	@Test
	public void disableParkingSpace() {
		this.parkingSpaceService.disableParkingSpace(spaceno, "modifyBy禁用");
	}
	@Test
	public void deleteParkingSpace() {
		ParkingSpace parkingSpace = parkingSpaceService.getParkingSpace(spaceno);
		this.parkingSpaceService.deleteParkingSpace(parkingSpace);
	}
	
	@Test
	public void getParkingSpaceListIncludeComAndZone() {
		ParkingSpace parkingSpace = new ParkingSpace();
		//行政区域
		Zone zone = new Zone();
		zone.setZoneid(zoneid);
		parkingSpace.setZone(zone);
		//小区
		Community c = new Community();
//		c.setZoneid(zoneid);
		parkingSpace.setCommunity(c);
		this.parkingSpaceService.getParkingSpaceListIncludeComAndZone(parkingSpace);
	}
	@Test
	public void getFreeParkingSpaceCount() {
		System.out.println(this.parkingSpaceService.getFreeParkingSpaceCount());
	}
	@Test
	public void getEnableParkingSpaceCount() {
		System.out.println(this.parkingSpaceService.getEnableParkingSpaceCount(null));
	}
	@Test
	public void getParkingSpaceListBySpacenoLike() {
		this.parkingSpaceService.getParkingSpaceListBySpacenoLike(spaceno);
	}
	
	@Test
	public void testDate() {
		Date today = new Date();
        Calendar c=Calendar.getInstance();
        c.setTime(today);
        int weekday=c.get(Calendar.DAY_OF_WEEK);
        System.out.println(weekday);
	}
	
	@Test
	public void getParkingSpaceListByComidAndParkHours() {
		List<ParkingSpace> list = this.parkingSpaceService.getParkingSpaceListByComidAndParkHours(comid, 10);
		for(ParkingSpace p : list) {
			System.out.println(p);
		}
	}
}
