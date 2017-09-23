package com.parkspace.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.parkspace.db.rmdb.dao.CommunityDao;
import com.parkspace.db.rmdb.dao.PropertyMgmtUserDao;
import com.parkspace.db.rmdb.entity.Community;
import com.parkspace.db.rmdb.entity.PropertyMgmtUser;

/**
 * @Title: TestPropertyMgmtUserDao.java
 * @Package com.parkspace.dao
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午11:26:07</p>
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/dispatcher-servlet.xml"}) 
public class TestPropertyMgmtUserDao {
	@Resource
	private PropertyMgmtUserDao propertyMgmtUserDao;
	@Resource
	private CommunityDao communityDao;
	
	public String userId = "1";
	public String comid = "34f3bf91-6c85-4591-8fd3-4db33c9f8330";
	
	@Test
	public void testAddPropertyMgmtUser(){
		List<Community> conList = communityDao.getCommunityList( new Community());
		int i = 0;
		for(Community c : conList){
			PropertyMgmtUser propertyMgmtUser = new PropertyMgmtUser();
			propertyMgmtUser.setComid(c.getComid());
			propertyMgmtUser.setCreateBy("createBy"+i);
			propertyMgmtUser.setCreateTime( new Date());
			propertyMgmtUser.setIsAdmin(1);
			propertyMgmtUser.setMemo("memo"+i);
			propertyMgmtUser.setModifyBy("modifyBy"+i);
			propertyMgmtUser.setModifyTime( new Date());
			propertyMgmtUser.setUserId(userId);
			propertyMgmtUserDao.addPropertyMgmtUser(propertyMgmtUser);
			i++;
			
		}
	}
	
	@Test
	public void testGetPropertyMgmtUser(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("comid", comid);
		map.put("userId", userId);
		PropertyMgmtUser propertyMgmtUser = propertyMgmtUserDao.getPropertyMgmtUser(map);
		System.out.println(propertyMgmtUser);
	}
	
	@Test
	public void testUpdatePropertyMgmtUser(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("comid", comid);
		map.put("userId", userId);
		PropertyMgmtUser propertyMgmtUser = propertyMgmtUserDao.getPropertyMgmtUser(map);
		propertyMgmtUser.setIsAdmin(0);
		propertyMgmtUser.setMemo("memo001");
		propertyMgmtUser.setModifyBy("modifyBy001");
		propertyMgmtUser.setModifyTime(new Date());
		propertyMgmtUserDao.updatePropertyMgmtUser(propertyMgmtUser);
	}
	
	@Test
	public void testDeletePropertyMgmtUser(){
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("comid", comid);
		map.put("userId", userId);
		PropertyMgmtUser propertyMgmtUser = propertyMgmtUserDao.getPropertyMgmtUser(map);
		
		propertyMgmtUser.setModifyBy("setModifyBy2");
		propertyMgmtUser.setModifyTime(new Date());
		propertyMgmtUserDao.deletePropertyMgmtUser(propertyMgmtUser);
	}
	
	@Test
	public void testGetPropertyMgmtUserList(){
		PropertyMgmtUser propertyMgmtUser = new PropertyMgmtUser();
		List<PropertyMgmtUser> list = propertyMgmtUserDao.getPropertyMgmtUserList(propertyMgmtUser);
		for(PropertyMgmtUser com : list){
			System.out.println(com);
		}
		
	}
	
	@Test
	public void testGetPropertyMgmtUserListForPage(){
		int pageNum = 1;
		PageHelper.startPage(pageNum, 2);
		PropertyMgmtUser propertyMgmtUser = new PropertyMgmtUser();
		List<PropertyMgmtUser> list = propertyMgmtUserDao.getPropertyMgmtUserList(propertyMgmtUser);
		PageInfo<PropertyMgmtUser> page = new PageInfo<PropertyMgmtUser>(list);
		List<PropertyMgmtUser> list2 = page.getList();
		for(PropertyMgmtUser PropertyMgmtUser1 : list2){
			System.out.println(PropertyMgmtUser1);
		}
	}
}
