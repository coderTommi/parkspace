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
import com.parkspace.db.rmdb.dao.CommunityDao;
import com.parkspace.db.rmdb.entity.Community;

/**
 * @Title: TestCommunityDao.java
 * @Package com.parkspace.dao
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午11:26:07</p>
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/dispatcher-servlet.xml"}) 
public class TestCommunityDao {
	@Resource
	private CommunityDao communityDao;
	public String zoneid = "12de7729-2aa9-404e-b8b8-9b0c0fa77b1a";
	public String zoneid2 = "a9e41d2f-8443-4f39-9a59-bac4071a25f5";
	public String comid = "34f3bf91-6c85-4591-8fd3-4db33c9f8330";
	
	@Test
	public void testAddCommunity(){
		for(int i = 0; i < 3; i++){
			String comid = UUID.randomUUID().toString();
			Community community = new Community();
			community.setComid(comid);
			
			community.setAddress("setAddress" + i);
			community.setComname("comname" + i);
			community.setCreateBy("setCreateBy" + i);
			community.setCreateTime(new Date());
			community.setIsenable(0);
			community.setMemo("memo" + i);
			community.setModifyBy("setModifyBy" + i);
			community.setModifyTime(new Date());
			community.setZoneid(zoneid);
			communityDao.addCommunity(community);
		}
	}
	
	@Test
	public void testGetCommunity(){
		Community community = communityDao.getCommunity(comid);
		System.out.println(community);
	}
	
	@Test
	public void testUpdateCommunity(){
		Community community = communityDao.getCommunity(comid);
		community.setAddress("address001");
		community.setComname("comname001");
		community.setIsenable(1);
		community.setMemo("memo001");
		community.setModifyBy("modifyBy001");
		community.setModifyTime(new Date());
		community.setZoneid(zoneid2);
		communityDao.updateCommunity(community);
	}
	
	@Test
	public void testDeleteCommunity(){
		Community community = communityDao.getCommunity(comid);
		community.setModifyBy("setModifyBy2");
		community.setModifyTime(new Date());
		community.setIsenable(2);
		communityDao.deleteCommunity(community);
	}
	
	@Test
	public void testGetcommunityList(){
		Community community = new Community();
		List<Community> list = communityDao.getCommunityList(community);
		for(Community com : list){
			System.out.println(com);
		}
		
	}
	
	@Test
	public void testGetcommunityListForPage(){
		int pageNum = 1;
		PageHelper.startPage(pageNum, 2);
		Community community = new Community();
		List<Community> list = communityDao.getCommunityList(community);
		PageInfo<Community> page = new PageInfo<Community>(list);
		List<Community> list2 = page.getList();
		for(Community community1 : list2){
			System.out.println(community1);
		}
	}
}
