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
import com.parkspace.db.rmdb.dao.CaruserDao;
import com.parkspace.db.rmdb.entity.Caruser;

/**
 * @Title: TestCaruserDao.java
 * @Package com.parkspace.dao
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午11:26:07</p>
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/dispatcher-servlet.xml"}) 
public class TestCaruserDao {
	@Resource
	private CaruserDao caruserDao;
	public String userId = "1";
	public String carno = "鲁A-001-1";
	
	@Test
	public void testAddCaruser(){
		for(int i = 0; i < 3; i++){
			Caruser caruser = new Caruser();
			caruser.setCarno("鲁A-001-"+i);
			caruser.setCreateBy("createBy"+i);
			caruser.setCreateTime(new Date());
			caruser.setIsauth(1);
			caruser.setMemo("memo"+i);
			caruser.setModifyBy("modifyBy"+i);
			caruser.setModifyTime(new Date());
			caruser.setUserId(userId);
			caruserDao.addCaruser(caruser);
		}
	}
	
	@Test
	public void testGetCaruser(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("carno", carno);
		map.put("userId", userId);
		Caruser caruser = caruserDao.getCaruser(map);
		System.out.println(caruser);
	}
	
	@Test
	public void testUpdateCaruser(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("carno", carno);
		map.put("userId", userId);
		Caruser caruser = caruserDao.getCaruser(map);
		caruser.setIsauth(0);
		caruser.setMemo("memo001");
		caruser.setModifyBy("modifyBy001");
		caruser.setModifyTime(new Date());
		caruserDao.updateCaruser(caruser);
	}
	
	@Test
	public void testDeleteCaruser(){
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("carno", carno);
		map.put("userId", userId);
		Caruser caruser = caruserDao.getCaruser(map);
		
		caruser.setModifyBy("setModifyBy2");
		caruser.setModifyTime(new Date());
		caruserDao.deleteCaruser(caruser);
	}
	
	@Test
	public void testGetCaruserList(){
		Caruser caruser = new Caruser();
		List<Caruser> list = caruserDao.getCaruserList(caruser);
		for(Caruser com : list){
			System.out.println(com);
		}
		
	}
	
	@Test
	public void testGetCaruserListForPage(){
		int pageNum = 1;
		PageHelper.startPage(pageNum, 2);
		Caruser caruser = new Caruser();
		List<Caruser> list = caruserDao.getCaruserList(caruser);
		PageInfo<Caruser> page = new PageInfo<Caruser>(list);
		List<Caruser> list2 = page.getList();
		for(Caruser Caruser1 : list2){
			System.out.println(Caruser1);
		}
	}
}
