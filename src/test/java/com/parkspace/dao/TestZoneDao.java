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
import com.parkspace.db.rmdb.dao.ZoneDao;
import com.parkspace.db.rmdb.entity.Zone;

/**
 * @Title: TestZoneDao.java
 * @Package com.parkspace.dao
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午11:26:07</p>
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/dispatcher-servlet.xml"}) 
public class TestZoneDao {
	@Resource
	private ZoneDao zoneDao;
	public String zoneid = "c7116d6b-c620-49ec-b671-1058724f9fa7";
	
	@Test
	public void testAddZone(){
		for(int i = 0; i < 3; i++){
			String zoneid = UUID.randomUUID().toString();
			Zone zone = new Zone();
			zone.setZoneid(zoneid);
			zone.setCity("setCity" + i);
			zone.setCreateBy("setCreateBy" + i);
			zone.setCreateTime(new Date());
			zone.setMemo("memo" + i);
			zone.setModifyBy("setModifyBy" + i);
			zone.setModifyTime(new Date());
			zone.setProvince("setProvince" + i);
			zone.setZone("setZone" + i);
			zone.setZonename("setZonename" + i);
			zone.setIsenable(0);
			zoneDao.addZone(zone);
		}
	}
	
	@Test
	public void testGetZone(){
		Zone zone = zoneDao.getZone(zoneid);
		System.out.println(zone);
	}
	
	@Test
	public void testUpdateZone(){
		Zone zone = zoneDao.getZone(zoneid);
		zone.setCity("setCity1");
		zone.setMemo("memo1");
		zone.setModifyBy("setModifyBy1");
		zone.setModifyTime(new Date());
		zone.setProvince("setProvince1");
		zone.setZone("setZone1");
		zone.setZonename("setZonename1");
		zone.setIsenable(1);
		zoneDao.updateZone(zone);
	}
	
	@Test
	public void testDeleteZone(){
		Zone zone = zoneDao.getZone(zoneid);
		zone.setModifyBy("setModifyBy2");
		zone.setModifyTime(new Date());
		zone.setIsenable(2);
		zoneDao.deleteZone(zone);
	}
	
	@Test
	public void testGetZoneList(){
		Zone zone = new Zone();
		List<Zone> list = zoneDao.getZoneList(zone);
		for(Zone z : list){
			System.out.println(z);
		}
	}
	
	@Test
	public void testGetZoneListForPage(){
		int pageNum = 1;
		PageHelper.startPage(pageNum, 2);
		Zone zone = new Zone();
		List<Zone> list = zoneDao.getZoneList(zone);
		PageInfo<Zone> page = new PageInfo<Zone>(list);
		List<Zone> list2 = page.getList();
		for(Zone zone1 : list2){
			System.out.println(zone1);
		}
	}
}
