package com.parkspace.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.parkspace.db.rmdb.entity.BlackList;
import com.parkspace.db.rmdb.entity.SpaceOwner;

/**
 * @Title: TestSpaceOwnerService.java
 * @Package com.parkspace.service
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午10:15:01</p>
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/dispatcher-servlet.xml"}) 
public class TestSpaceOwnerService {
	@Resource
	private ISpaceOwnerService spaceOwnerService;
	@Test
	public void testGetSpaceOwnerList(){
		SpaceOwner spaceOwner = new SpaceOwner();
//		spaceOwner.setSpaceno("3-001");
//		spaceOwner.setSpaceno("3-001-0");
//		spaceOwner.setCarno("1");
		spaceOwnerService.getSpaceOwnerList(spaceOwner);
	}
	@Test
	public void getSpaceOwnerAllInfoList(){
		SpaceOwner spaceOwner = new SpaceOwner();
		spaceOwner.setIsenableQuery(new Integer[]{2});
		spaceOwner.setIsauthQuery(new Integer[]{1});
//		spaceOwner.setSpaceno("3-001");
//		spaceOwner.setSpaceno("3-001-0");
//		spaceOwner.setCarno("1");
		spaceOwner.setZoneIsenableQuery(new Integer[]{1});
		spaceOwnerService.getSpaceOwnerAllInfoList(spaceOwner);
	}
	@Test
	public void getSpaceOwnerAllInfoListForPage(){
		int pageNum = 1;
		PageHelper.startPage(pageNum, 2);
		SpaceOwner spaceOwner = new SpaceOwner();
//		spaceOwner.setSpaceno("3-001");
//		spaceOwner.setSpaceno("3-001-0");
//		spaceOwner.setCarno("1");
		List<SpaceOwner> list = spaceOwnerService.getSpaceOwnerAllInfoList(spaceOwner);
		PageInfo<SpaceOwner> page = new PageInfo<SpaceOwner>(list);
		page.getList();
	}
	
	@Test
	public void addBlackList(){
		BlackList addBlackList = new BlackList();
		addBlackList.setUserId("1");
		addBlackList.setMemo("测试加入黑名单");
		BlackList returnBlackList = spaceOwnerService.addBlackList(addBlackList);
		System.out.println(returnBlackList);
	}
	
	@Test
	public void getBlackListAll(){
		BlackList queryBlackList = new BlackList();
		queryBlackList.setIsCancel(1);
		List<BlackList> list = spaceOwnerService.getBlackListAllInfoList(queryBlackList);
		for(BlackList b : list){
			System.out.println(b);
		}
	}
}
