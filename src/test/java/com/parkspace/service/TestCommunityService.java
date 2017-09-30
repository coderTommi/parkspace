package com.parkspace.service;

import java.util.List;

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
	
	public String zoneid = "4e73503c-7052-41bc-a716-b8b2d2e32e5e";
	
	public String comid = "d2ac2ef6-acad-411a-9b2a-9732d47028b5";
	
	public String userId = "1";
	
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
		Community community = this.communityService.getCommunity(comid);
		System.out.println(community);
		
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
		List<Community> list = this.communityService.getCommunityList(community);
		for(Community c : list) {
			System.out.println(c);
		}
		
	}
	
	@Test
	public void getCommunityAllInfoList(){
		Community community = new Community();
//		community.setIsenableQuery(new Integer[]{0});
//		community.setZoneIsenableQuery(new Integer[]{1});
		List<Community> list = communityService.getCommunityAllInfoList(community);
		for(Community c : list) {
			System.out.println(c);
		}
		
	}
	
	@Test
	public void getCommunityListByCity(){
		List<Community> list = this.communityService.getCommunityListByCity("济南",new Integer[]{1,2},
				new Integer[]{1});
		for(Community c : list) {
			System.out.println(c);
		}
	}
	
	@Test
	public void getCommunityListByZoneId(){
		List<Community> list = this.communityService.getCommunityListByZoneId(zoneid,new Integer[]{1,2},
				new Integer[]{1});
		for(Community c : list) {
			System.out.println(c);
		}
	}
	
	@Test
	public void getCommunityListByComName(){
		List<Community> list = this.communityService.getCommunityListByComName("王府",new Integer[]{1,2},
				new Integer[]{1});
		for(Community c : list) {
			System.out.println(c);
		}
	}
	
	@Test
	public void getPropertyMgmtUser(){
		this.communityService.getPropertyMgmtUser(userId);
	}
	
	@Test
	public void addUserCommunity(){
		this.communityService.addUserCommunity(comid, userId);
	}
	
	@Test
	public void deleteUserCommunity(){
		this.communityService.deleteUserCommunity(comid, userId);
	}
}
