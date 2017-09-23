package com.parkspace.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.parkspace.db.rmdb.dao.ParkingSpaceDao;
import com.parkspace.db.rmdb.dao.ShareConfigDao;
import com.parkspace.db.rmdb.entity.ParkingSpace;
import com.parkspace.db.rmdb.entity.ShareConfig;

/**
 * @Title: TestShareConfigDao.java
 * @Package com.parkspace.dao
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午11:26:07</p>
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/dispatcher-servlet.xml"}) 
public class TestShareConfigDao {
	@Resource
	private ShareConfigDao shareConfigDao;
	@Resource
	private ParkingSpaceDao parkingSpaceDao;
	public String spaceno = "3-001-0";
	public String uuid = "938dfa52-5290-41b4-8a0f-668810a87c58";
	
	@Test
	public void testAddShareConfig(){
		List<ParkingSpace> list = parkingSpaceDao.getParkingSpaceList(new ParkingSpace());
		int i = 0;
		for(ParkingSpace p : list){
			i++;
			ShareConfig shareConfig = new ShareConfig();
			shareConfig.setCreateBy("createBy"+i);
			shareConfig.setCreateTime(new Date());
			shareConfig.setEndTime("24:00:00");
			shareConfig.setInternalDate("internalDate"+i);
			shareConfig.setIsOpen(1);
			shareConfig.setModifyBy("modifyBy"+i);
			shareConfig.setModifyTime(new Date());
			shareConfig.setShareType(1);
			shareConfig.setSpaceno(p.getSpaceno());
			shareConfig.setStartTime("03:00:00");
			shareConfig.setUUID(UUID.randomUUID().toString());
			shareConfigDao.addShareConfig(shareConfig);
		}
	}
	
	@Test
	public void testGetShareConfig(){
		ShareConfig shareConfig = shareConfigDao.getShareConfig(uuid);
		System.out.println(shareConfig);
	}
	
	@Test
	public void testUpdateShareConfig(){
		ShareConfig shareConfig = shareConfigDao.getShareConfig(uuid);
		shareConfig.setEndTime("23:00:00");
		shareConfig.setInternalDate("internalDate001");
		shareConfig.setIsOpen(2);
		shareConfig.setModifyBy("modifyBy001");
		shareConfig.setModifyTime(new Date());
		shareConfig.setShareType(0);
		shareConfig.setSpaceno(spaceno);
		shareConfig.setStartTime("12:00:00");
		shareConfigDao.updateShareConfig(shareConfig);
	}
	
	@Test
	public void testDeleteShareConfig(){
		ShareConfig shareConfig = shareConfigDao.getShareConfig(uuid);
		shareConfig.setModifyBy("setModifyBy2");
		shareConfig.setModifyTime(new Date());
		shareConfigDao.deleteShareConfig(shareConfig);
	}
	
	@Test
	public void testGetShareConfigList(){
		ShareConfig shareConfig = new ShareConfig();
		shareConfig.setSpaceno(spaceno);
		List<ShareConfig> list = shareConfigDao.getShareConfigList(shareConfig);
		for(ShareConfig z : list){
			System.out.println(z);
		}
	}
	
	@Test
	public void testGetShareConfigListForPage(){
		int pageNum = 1;
		PageHelper.startPage(pageNum, 2);
		ShareConfig shareConfig = new ShareConfig();
		List<ShareConfig> list = shareConfigDao.getShareConfigList(shareConfig);
		PageInfo<ShareConfig> page = new PageInfo<ShareConfig>(list);
		List<ShareConfig> list2 = page.getList();
		for(ShareConfig ShareConfig1 : list2){
			System.out.println(ShareConfig1);
		}
	}
}
