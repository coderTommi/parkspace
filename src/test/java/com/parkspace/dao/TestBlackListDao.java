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
import com.parkspace.db.rmdb.dao.BlackListDao;
import com.parkspace.db.rmdb.entity.BlackList;

/**
 * @Title: TestBlackListDao.java
 * @Package com.parkspace.dao
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午11:26:07</p>
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/dispatcher-servlet.xml"}) 
public class TestBlackListDao {
	@Resource
	private BlackListDao blackListDao;
	public String uuid1 = "137cf90c-2d76-42e9-8283-bf00a9291f5b";
	public String userId = "1";
	@Test
	public void testAddBlackList(){
		for(int i = 0; i < 3; i++){
			String uuid = UUID.randomUUID().toString();
			BlackList blackList = new BlackList();
			blackList.setCreateTime(new Date());
			blackList.setIsCancel(0);
			blackList.setMemo("memo"+i);
			blackList.setModifyTime(new Date());
			blackList.setUserId(userId);
			blackList.setUUID(uuid);
			blackListDao.addBlackList(blackList);
		}
	}
	
	@Test
	public void testGetBlackList(){
		BlackList blackList = blackListDao.getBlackList(uuid1);
		System.out.println(blackList);
	}
	
	@Test
	public void testUpdateBlackList(){
		BlackList blackList = blackListDao.getBlackList(uuid1);
		blackList.setIsCancel(-1);
		blackList.setMemo("memo001");
		blackList.setModifyTime(new Date());
		blackList.setUserId(userId);
		blackListDao.updateBlackList(blackList);
	}
	
	@Test
	public void testDeleteBlackList(){
		BlackList blackList = blackListDao.getBlackList(uuid1);
		blackList.setModifyTime(new Date());
		blackListDao.deleteBlackList(blackList);
	}
	
	@Test
	public void testGetBlackListList(){
		BlackList blackList = new BlackList();
		List<BlackList> list = blackListDao.getBlackListList(blackList);
		for(BlackList z : list){
			System.out.println(z);
		}
	}
	
	@Test
	public void testGetBlackListListForPage(){
		int pageNum = 1;
		PageHelper.startPage(pageNum, 2);
		BlackList blackList = new BlackList();
		List<BlackList> list =blackListDao.getBlackListList(blackList);
		PageInfo<BlackList> page = new PageInfo<BlackList>(list);
		List<BlackList> list2 = page.getList();
		for(BlackList BlackList1 : list2){
			System.out.println(BlackList1);
		}
	}
}
