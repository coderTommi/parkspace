package com.parkspace.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkspace.db.rmdb.dao.ParkingSpaceBillHisDao;
import com.parkspace.db.rmdb.entity.ParkingSpaceBill;
import com.parkspace.db.rmdb.entity.ParkingSpaceBillHis;
import com.parkspace.service.IParkingSpaceBillHisService;

/**
 * @Title: ParkingSpaceBillHisServiceImpl.java
 * @Package com.parkspace.service.impl
 * <p>Description:历史订单service实现类</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月3日 下午4:52:11</p>
*/
@Service("parkingSpaceBillHisService")
public class ParkingSpaceBillHisServiceImpl implements IParkingSpaceBillHisService{
	@Resource
	private ParkingSpaceBillHisDao parkingSpaceBillHisDao;
	/**
	 * @Title: getParkingSpaceBillHis
	 * <p>Description:根据主键查询历史订单信息</p>
	 * @param     UUID 历史订单主键
	 * @return ParkingSpaceBillHis    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:11:58</p>
	 */
	@Override
	public ParkingSpaceBillHis getParkingSpaceBillHis(String UUID) {
		return parkingSpaceBillHisDao.getParkingSpaceBillHis(UUID);
	}
	/**
	 * @Title: addParkingSpaceBillHis
	 * <p>Description:
	 * 保存历史车位订单信息，id自动生成,在service层返回主键id
	 * </p>
	 * @param     parkingSpaceBill 车位订单信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:11:38</p>
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addParkingSpaceBillHis(ParkingSpaceBill parkingSpaceBill) {
		if(parkingSpaceBill != null) {
			ParkingSpaceBillHis parkingSpaceBillHis = new ParkingSpaceBillHis();
			//计算小时差：实际停车时长
			Date createTime = parkingSpaceBill.getCreateTime();
			Date currentTime = new Date();
			long longActualParkHours = currentTime.getTime() - createTime.getTime();
			BigDecimal actualParkHours = new BigDecimal(longActualParkHours/1000/60/60);
			parkingSpaceBillHis.setActualParkHours(actualParkHours);
			//实际价格：=单价*实际停车时长
			BigDecimal unitPrice = parkingSpaceBill.getUnitPrice();
			parkingSpaceBillHis.setActualPrice(unitPrice.multiply(actualParkHours));
			
			parkingSpaceBillHis.setBillStatus(parkingSpaceBill.getBillStatus());
			parkingSpaceBillHis.setBudgetPrice(parkingSpaceBill.getBudgetPrice());
			parkingSpaceBillHis.setCarno(parkingSpaceBill.getCarno());
			parkingSpaceBillHis.setCreateTime(createTime);
			parkingSpaceBillHis.setDelayParkHours(parkingSpaceBill.getDelayParkHours());
			parkingSpaceBillHis.setOrderJnlNo(parkingSpaceBill.getOrderJnlNo());
			parkingSpaceBillHis.setParkHours(parkingSpaceBill.getParkHours());
			parkingSpaceBillHis.setRecodeTime(new Date());
			parkingSpaceBillHis.setSpaceno(parkingSpaceBill.getSpaceno());
			parkingSpaceBillHis.setUnitPrice(unitPrice);
			parkingSpaceBillHis.setUserId(parkingSpaceBill.getUserId());
			parkingSpaceBillHis.setUUID(UUID.randomUUID().toString());
			
			parkingSpaceBillHisDao.addParkingSpaceBillHis(parkingSpaceBillHis);
		}
	}
	/**
	 * @Title: getParkingSpaceBillHisList
	 * <p>Description:根据条件查询历史车位订单信息</p>
	 * @param     parkingSpaceBillHis 历史车位订单信息
	 * @return List<ParkingSpaceBillHis>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:11:16</p>
	 */
	@Override
	public List<ParkingSpaceBillHis> getParkingSpaceBillHisList(
			ParkingSpaceBillHis parkingSpaceBillHis){
		if(parkingSpaceBillHis == null) {
			parkingSpaceBillHis = new ParkingSpaceBillHis();
		}
		return parkingSpaceBillHisDao.getParkingSpaceBillHisList(parkingSpaceBillHis);
	}
}
