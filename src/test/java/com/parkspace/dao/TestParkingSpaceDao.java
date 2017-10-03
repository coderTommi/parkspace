package com.parkspace.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.parkspace.db.rmdb.dao.ParkingSpaceDao;
import com.parkspace.db.rmdb.entity.ParkingSpace;
import com.parkspace.db.rmdb.entity.ShareConfig;

/**
 * @Title: TestParkingSpaceDao.java
 * @Package com.parkspace.dao
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午11:26:07</p>
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/dispatcher-servlet.xml"}) 
public class TestParkingSpaceDao {
	@Resource
	private ParkingSpaceDao parkingSpaceDao;
	public String comid = "34f3bf91-6c85-4591-8fd3-4db33c9f8330";
	public String comid2 = "da26e58b-83c8-48ff-b0b5-2613e3bfca11";
	public String spaceno = "3-001-1";
	
	@Test
	public void testAddParkingSpace(){
		for(int i = 0; i < 3; i++){
			String spaceno = "3-001-" + i;
			ParkingSpace parkingSpace = new ParkingSpace();
			parkingSpace.setComid(comid);
			parkingSpace.setCreateBy("createBy"+i);
			parkingSpace.setCreateTime(new Date());
			parkingSpace.setMemo("memo"+i);
			parkingSpace.setModifyBy("modifyBy" + i);
			parkingSpace.setModifyTime(new Date());
			parkingSpace.setParkPositionDes("parkPositionDes"+i);
			parkingSpace.setParkPositionFloor("parkPositionFloor"+i);
			parkingSpace.setParkPositionX("parkPositionX"+i);
			parkingSpace.setParkPositionY("parkPositionY"+i);
			parkingSpace.setParkPositionZone("parkPositionZone"+i);
			parkingSpace.setParkStatus("N");
			parkingSpace.setParkType("P");
			parkingSpace.setSpaceno(spaceno);
			parkingSpace.setSpaceOwner("spaceOwner"+i);
			parkingSpaceDao.addParkingSpace(parkingSpace);
		}
	}
	
	@Test
	public void testGetParkingSpace(){
		ParkingSpace parkingSpace = parkingSpaceDao.getParkingSpace(spaceno);
		System.out.println(parkingSpace);
	}
	
	@Test
	public void testUpdateParkingSpace(){
		ParkingSpace parkingSpace = parkingSpaceDao.getParkingSpace(spaceno);
		parkingSpace.setMemo("memo001");
		parkingSpace.setModifyBy("modifyBy001");
		parkingSpace.setModifyTime(new Date());
		parkingSpace.setParkPositionDes("parkPositionDes001");
		parkingSpace.setParkPositionFloor("parkPositionFloor001");
		parkingSpace.setParkPositionX("parkPositionX001");
		parkingSpace.setParkPositionY("parkPositionY001");
		parkingSpace.setParkPositionZone("parkPositionZone001");
		parkingSpace.setParkStatus("1");
		parkingSpace.setParkType("O");
		parkingSpace.setSpaceOwner("spaceOwner001");
		parkingSpace.setComid(comid2);
		parkingSpaceDao.updateParkingSpace(parkingSpace);
	}
	
	@Test
	public void testDeleteParkingSpace(){
		ParkingSpace parkingSpace = parkingSpaceDao.getParkingSpace(spaceno);
		parkingSpace.setModifyBy("setModifyBy2");
		parkingSpace.setModifyTime(new Date());
		parkingSpace.setParkStatus("N");
		parkingSpaceDao.deleteParkingSpace(parkingSpace);
	}
	
	@Test
	public void testGetParkingSpaceList(){
		ParkingSpace parkingSpace = new ParkingSpace();
		parkingSpace.setComid(comid);
		List<ParkingSpace> list = parkingSpaceDao.getParkingSpaceList(parkingSpace);
		for(ParkingSpace com : list){
			System.out.println(com);
		}
		
	}
	
	@Test
	public void testGetParkingSpaceListForPage(){
		int pageNum = 1;
		PageHelper.startPage(pageNum, 2);
		ParkingSpace parkingSpace = new ParkingSpace();
		List<ParkingSpace> list = parkingSpaceDao.getParkingSpaceList(parkingSpace);
		PageInfo<ParkingSpace> page = new PageInfo<ParkingSpace>(list);
		List<ParkingSpace> list2 = page.getList();
		for(ParkingSpace ParkingSpace1 : list2){
			System.out.println(ParkingSpace1);
		}
	}
	
	@Test
	public void getParkingSpaceEnableBillList() {
		ParkingSpace parkingSpace = new ParkingSpace();
		ShareConfig shareConfig = new ShareConfig();
		shareConfig.setIsOpen(1);
		
//		parkingSpace.setShareConfig(shareConfig);
		this.parkingSpaceDao.getParkingSpaceEnableBillList(parkingSpace);
	}
}
