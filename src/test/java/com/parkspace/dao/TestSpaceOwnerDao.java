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
import com.parkspace.db.rmdb.dao.SpaceOwnerDao;
import com.parkspace.db.rmdb.entity.ParkingSpace;
import com.parkspace.db.rmdb.entity.SpaceOwner;

/**
 * @Title: TestSpaceOwnerDao.java
 * @Package com.parkspace.dao
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午11:26:07</p>
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/dispatcher-servlet.xml"}) 
public class TestSpaceOwnerDao {
	@Resource
	private SpaceOwnerDao spaceOwnerDao;
	@Resource
	private ParkingSpaceDao parkingSpaceDao;
	public String spaceno = "3-001-0";
	public String userId = "1";
	
	@Test
	public void testAddSpaceOwner(){
		List<ParkingSpace> list = parkingSpaceDao.getParkingSpaceList(new ParkingSpace());
		int i = 0;
		for(ParkingSpace p : list){
			i++;
			SpaceOwner spaceOwner = new SpaceOwner();
			spaceOwner.setCarno("carno" + i);
			spaceOwner.setCreateBy("createBy"+i);
			spaceOwner.setCreateTime(new Date());
			spaceOwner.setIsauth(0);
			spaceOwner.setMemo("memo"+i);
			spaceOwner.setModifyBy("modifyBy"+i);
			spaceOwner.setModifyTime(new Date());
			spaceOwner.setSpaceno(p.getSpaceno());
			spaceOwner.setUserId(userId);
			spaceOwnerDao.addSpaceOwner(spaceOwner);
		}
	}
	
	@Test
	public void testGetSpaceOwner(){
		SpaceOwner spaceOwner = spaceOwnerDao.getSpaceOwner(spaceno);
		System.out.println(spaceOwner);
	}
	
	@Test
	public void testUpdateSpaceOwner(){
		SpaceOwner spaceOwner = spaceOwnerDao.getSpaceOwner(spaceno);
		spaceOwner.setCarno("carno001");
		spaceOwner.setIsauth(1);
		spaceOwner.setMemo("memo001");
		spaceOwner.setModifyBy("modifyBy001");
		spaceOwner.setModifyTime(new Date());
		spaceOwner.setUserId(userId);
		spaceOwnerDao.updateSpaceOwner(spaceOwner);
	}
	
	@Test
	public void testDeleteSpaceOwner(){
		SpaceOwner spaceOwner = spaceOwnerDao.getSpaceOwner(spaceno);
		spaceOwner.setModifyBy("setModifyBy2");
		spaceOwner.setModifyTime(new Date());
		spaceOwner.setIsauth(-1);
		spaceOwnerDao.deleteSpaceOwner(spaceOwner);
	}
	
	@Test
	public void testGetSpaceOwnerList(){
		SpaceOwner spaceOwner = new SpaceOwner();
//		spaceOwner.setSpaceno("3-001-0");
		spaceOwner.setCarno("4");
		List<SpaceOwner> list = spaceOwnerDao.getSpaceOwnerList(spaceOwner);
		for(SpaceOwner z : list){
			System.out.println(z);
		}
	}
	
	@Test
	public void testGetSpaceOwnerListForPage(){
		int pageNum = 1;
		PageHelper.startPage(pageNum, 2);
		SpaceOwner spaceOwner = new SpaceOwner();
		List<SpaceOwner> list = spaceOwnerDao.getSpaceOwnerList(spaceOwner);
		PageInfo<SpaceOwner> page = new PageInfo<SpaceOwner>(list);
		List<SpaceOwner> list2 = page.getList();
		for(SpaceOwner SpaceOwner1 : list2){
			System.out.println(SpaceOwner1);
		}
	}
	
	@Test
	public void getSpaceOwnerAllInfoList(){
		SpaceOwner spaceOwner = new SpaceOwner();
//		spaceOwner.setSpaceno("3-001-0");
//		spaceOwner.setCarno("4");
//		spaceOwner.setUserName("辽");
		spaceOwner.setZoneisenable(0);
		List<SpaceOwner> list = spaceOwnerDao.getSpaceOwnerAllInfoList(spaceOwner);
		for(SpaceOwner z : list){
			System.out.println(z);
		}
	}
}
