package com.parkspace.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.parkspace.db.rmdb.entity.Community;

/**
 * @Title: TestCommunityService.java
 * @Package com.parkspace.service
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午10:15:01</p>
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/dispatcher-servlet.xml"}) 
public class TestCommunityService {
	@Resource
	private ICommunityService communityService;
	
	public String zoneid = "b01273b7-0063-47ee-afeb-47c8694e22a8";
	
	public String comid = "3a3296ec-2449-4321-b194-c92a171e2c0b";
	
	@Test
	public void addCommunity(){
		Community community = new Community();
		community.setAddress("济洛路王府庄园");
		community.setComname("王府庄园");
		community.setCreateBy("孙辽东创建");
		//默认开通
		community.setMemo("测试添加小区");
		community.setModifyBy("孙辽东编辑");
		community.setZoneid(zoneid);
		communityService.addCommunity(community);
	}
	
	@Test
	public void getCommunity(){
		this.communityService.getCommunity(comid);
	}
	
	@Test
	public void updateCommunity(){
		Community community = this.communityService.getCommunity(comid);
		community.setAddress(community.getAddress()+"更新地址");
		this.communityService.updateCommunity(community);
	}
	
	@Test
	public void deleteCommunity(){
		Community community = this.communityService.getCommunity(comid);
		this.communityService.deleteCommunity(community);
	}
	
	@Test
	public void getCommunityList(){
		Community community = new Community();
		this.communityService.getCommunityList(community);
	}
	
	@Test
	public void getCommunityAllInfoList(){
		Community community = new Community();
		community.setIsenableQuery(new Integer[]{0});
		community.setZoneIsenableQuery(new Integer[]{1});
		communityService.getCommunityAllInfoList(community);
	}
	
	@Test
	public void getCommunityListByCity(){
		this.communityService.getCommunityListByCity("济南",new Integer[]{1,2},
				new Integer[]{1});
	}
	
	@Test
	public void getCommunityListByZoneId(){
		this.communityService.getCommunityListByZoneId(zoneid,new Integer[]{1,2},
				new Integer[]{1});
	}
	
	@Test
	public void getCommunityListByComName(){
		this.communityService.getCommunityListByComName("王府",new Integer[]{1,2},
				new Integer[]{1});
	}
}
