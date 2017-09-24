package com.parkspace.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.parkspace.db.rmdb.entity.Zone;

/**
 * @Title: TestZoneService.java
 * @Package com.parkspace.service
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午10:15:01</p>
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/dispatcher-servlet.xml"}) 
public class TestZoneService {
	@Resource
	private IZoneService zoneService;
	public String zoneid = "4e73503c-7052-41bc-a716-b8b2d2e32e5e";
	@Test
	public void getZone(){
		Zone zone = this.zoneService.getZone(zoneid);
		System.out.println(zone);
	}
	@Test
	public void addZone(){
		Zone zone = new Zone();
		zone.setCity("济南");
		zone.setCreateBy("孙辽东");
		zone.setMemo("测试区域添加");
		zone.setModifyBy("孙辽东02");
		zone.setProvince("山东省");
		zone.setZone("天桥区");
		zone.setZonename("天桥行政区");
		System.out.println(this.zoneService.addZone(zone));
		
	}
	@Test
	public void updateZone(){
		Zone zone = this.zoneService.getZone(zoneid);
		zone.setMemo(zone.getMemo() + "0001");
		this.zoneService.updateZone(zone);
	}
	@Test
	public void deleteZone(){
		Zone zone = this.zoneService.getZone(zoneid);
		this.zoneService.deleteZone(zone);
	}
	
	@Test
	public void getZoneList(){
		Zone zone = new Zone();
		zone.setIsenableQuery(new Integer[]{1,0});
		List<Zone> list = this.zoneService.getZoneList(zone);
		for(Zone z : list){
			System.out.println(z);
		}
	}

	@Test
	public void getZoneListForPage(){
		int pageNum = 1;
		PageHelper.startPage(pageNum, 2);
		Zone zone = new Zone();
		List<Zone> list = this.zoneService.getZoneList(zone);
		for(Zone z : list){
			System.out.println(z);
		}
		System.out.println("========================");
		PageInfo<Zone> page = new PageInfo<Zone>(list);
		List<Zone> list2 = page.getList();
		for(Zone s1 : list2){
			System.out.println(s1);
		}
	}
}
