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
import com.parkspace.db.rmdb.dao.ParkingSpaceBillDao;
import com.parkspace.db.rmdb.dao.ParkingSpaceBillHisDao;
import com.parkspace.db.rmdb.entity.ParkingSpaceBill;
import com.parkspace.db.rmdb.entity.ParkingSpaceBillHis;

/**
 * @Title: TestParkingSpaceBillDao.java
 * @Package com.parkspace.dao
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午11:26:07</p>
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/dispatcher-servlet.xml"}) 
public class TestParkingSpaceBillDao {
	@Resource
	private ParkingSpaceBillDao parkingSpaceBillDao;
	@Resource
	private ParkingSpaceBillHisDao parkingSpaceBillHisDao;
	public String carno = "鲁A-001-0";
	public String userId = "1";
	public String spaceno = "3-001-0";
	public String orderJnlNo = "448b297c-ea8b-4057-9d28-a3d15840ab13";
	public String uuid1 = "c7997094-02b5-4c0a-9d6b-138886db59c4";
	@Test
	public void testAddParkingSpaceBill(){
		for(int i = 0; i < 3; i++){
			String orderJnlNo = UUID.randomUUID().toString();
			ParkingSpaceBill parkingSpaceBill = new ParkingSpaceBill();
			parkingSpaceBill.setBillStatus(1);
			parkingSpaceBill.setBudgetPrice(10.0 * (i+1));
			parkingSpaceBill.setCarno(carno);
			parkingSpaceBill.setCreateTime(new Date());
			parkingSpaceBill.setOrderJnlNo(orderJnlNo);
			parkingSpaceBill.setParkHours(10 * (i+1));
			parkingSpaceBill.setSpaceno(spaceno);
			parkingSpaceBill.setUnitPrice(2.0 * (i+1));
			parkingSpaceBill.setUserId(userId);
			parkingSpaceBillDao.addParkingSpaceBill(parkingSpaceBill);
		}
	}
	
	@Test
	public void testGetParkingSpaceBill(){
		ParkingSpaceBill parkingSpaceBill = parkingSpaceBillDao.getParkingSpaceBill(orderJnlNo);
		System.out.println(parkingSpaceBill);
	}
	
	@Test
	public void testGetParkingSpaceBillHis(){
		ParkingSpaceBillHis parkingSpaceBillHis = parkingSpaceBillHisDao.getParkingSpaceBillHis(uuid1);
		System.out.println(parkingSpaceBillHis);
	}
	
	@Test
	public void testUpdateParkingSpaceBill(){
		ParkingSpaceBill parkingSpaceBill = parkingSpaceBillDao.getParkingSpaceBill(orderJnlNo);
		parkingSpaceBill.setBillStatus(2);
		parkingSpaceBill.setBudgetPrice(100.0);
		parkingSpaceBill.setCreateTime(new Date());
		parkingSpaceBill.setParkHours(11);
		parkingSpaceBill.setUnitPrice(23.0);
		parkingSpaceBillDao.updateParkingSpaceBill(parkingSpaceBill);
	}
	
	@Test
	public void testDeleteParkingSpaceBill(){
		ParkingSpaceBill parkingSpaceBill = parkingSpaceBillDao.getParkingSpaceBill(orderJnlNo);
		
		ParkingSpaceBillHis parkingSpaceBillHis = new ParkingSpaceBillHis();
		parkingSpaceBillHis.setActualParkHours(10.00);
		parkingSpaceBillHis.setActualPrice(20.00);
		parkingSpaceBillHis.setBillStatus(4);
		parkingSpaceBillHis.setBudgetPrice(parkingSpaceBill.getBudgetPrice());
		parkingSpaceBillHis.setCarno(parkingSpaceBill.getCarno());
		parkingSpaceBillHis.setCreateTime(parkingSpaceBill.getCreateTime());
		parkingSpaceBillHis.setOrderJnlNo(parkingSpaceBill.getOrderJnlNo());
		parkingSpaceBillHis.setParkHours(parkingSpaceBill.getParkHours());
		parkingSpaceBillHis.setSpaceno(parkingSpaceBill.getSpaceno());
		parkingSpaceBillHis.setUnitPrice(parkingSpaceBill.getUnitPrice());
		parkingSpaceBillHis.setUserId(parkingSpaceBill.getUserId());
		parkingSpaceBillHis.setUUID(UUID.randomUUID().toString());
		parkingSpaceBillDao.deleteParkingSpaceBill(parkingSpaceBill.getOrderJnlNo());
		parkingSpaceBillHisDao.addParkingSpaceBillHis(parkingSpaceBillHis);
	}
	
	@Test
	public void testGetParkingSpaceBillList(){
		ParkingSpaceBill parkingSpaceBill = new ParkingSpaceBill();
		List<ParkingSpaceBill> list = parkingSpaceBillDao.getParkingSpaceBillList(parkingSpaceBill);
		for(ParkingSpaceBill z : list){
			System.out.println(z);
		}
	}
	
	@Test
	public void testGetParkingSpaceBillListForPage(){
		int pageNum = 1;
		PageHelper.startPage(pageNum, 2);
		ParkingSpaceBill parkingSpaceBill = new ParkingSpaceBill();
		List<ParkingSpaceBill> list = parkingSpaceBillDao.getParkingSpaceBillList(parkingSpaceBill);
		PageInfo<ParkingSpaceBill> page = new PageInfo<ParkingSpaceBill>(list);
		List<ParkingSpaceBill> list2 = page.getList();
		for(ParkingSpaceBill ParkingSpaceBill1 : list2){
			System.out.println(ParkingSpaceBill1);
		}
	}
	
	
	@Test
	public void testGetParkingSpaceBillHisList(){
		ParkingSpaceBillHis parkingSpaceBillHis = new ParkingSpaceBillHis();
		List<ParkingSpaceBillHis> list = parkingSpaceBillHisDao.getParkingSpaceBillHisList(parkingSpaceBillHis);
		for(ParkingSpaceBillHis z : list){
			System.out.println(z);
		}
	}
	
	@Test
	public void testGetParkingSpaceBillHisListForPage(){
		int pageNum = 1;
		PageHelper.startPage(pageNum, 2);
		ParkingSpaceBillHis parkingSpaceBillHis = new ParkingSpaceBillHis();
		List<ParkingSpaceBillHis> list = parkingSpaceBillHisDao.getParkingSpaceBillHisList(parkingSpaceBillHis);
		PageInfo<ParkingSpaceBillHis> page = new PageInfo<ParkingSpaceBillHis>(list);
		List<ParkingSpaceBillHis> list2 = page.getList();
		for(ParkingSpaceBillHis ParkingSpaceBill1 : list2){
			System.out.println(ParkingSpaceBill1);
		}
	}
}
