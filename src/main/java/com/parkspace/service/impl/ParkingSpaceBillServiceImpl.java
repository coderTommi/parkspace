package com.parkspace.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.parkspace.db.rmdb.dao.ParkingSpaceBillDao;
import com.parkspace.db.rmdb.dao.ParkingSpaceDao;
import com.parkspace.db.rmdb.entity.ParkingSpace;
import com.parkspace.db.rmdb.entity.ParkingSpaceBill;
import com.parkspace.service.IParkingSpaceBillService;

/**
 * @Title: ParkingSpaceBillServiceImpl.java
 * @Package com.parkspace.service.impl
 * <p>Description:订单service实现类</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月3日 下午10:12:14</p>
*/
@Service("parkingSpaceBillService")
public class ParkingSpaceBillServiceImpl implements IParkingSpaceBillService{
	@Resource
	private ParkingSpaceBillDao parkingSpaceBillDao;
	@Resource
	private ParkingSpaceDao parkingSpaceDao;
	/**
	 * @Title: getParkingSpaceBill
	 * <p>Description:根据订单编号获取车位订单信息</p>
	 * @param     orderJnlNo 订单编号
	 * @return ParkingSpaceBill    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:16:29</p>
	 */
	@Override
	public ParkingSpaceBill getParkingSpaceBill(String orderJnlNo) {
		ParkingSpaceBill parkingSpaceBill = parkingSpaceBillDao.getParkingSpaceBill(orderJnlNo);
		if(parkingSpaceBill != null) {
			String spaceno = parkingSpaceBill.getSpaceno();
			//获取最大停车时间
			String maxParkHoursString = "";
			
			ParkingSpace parkingSpace = new ParkingSpace();
			parkingSpace.setParkHoursString("00:00:00");
			parkingSpace.setSpaceno(spaceno);
			List<ParkingSpace> list = parkingSpaceDao.getParkingSpaceEnableBillList(parkingSpace);
			if(list != null && list.size() > 0) {
				maxParkHoursString = list.get(0).getParkHoursString();
			}
			if(!StringUtils.isEmpty(maxParkHoursString)) {
				//查询可用差值
				parkingSpaceBill.setMaxParkHoursString(maxParkHoursString);
				String maxDelayParkHoursString = parkingSpaceBillDao.getMaxDelayParkHoursString(parkingSpaceBill);
				parkingSpaceBill.setMaxDelayParkHoursString(maxDelayParkHoursString);
			}
		}
		return parkingSpaceBill;
	}
	/**
	 * @Title: addParkingSpaceBill
	 * <p>Description:
	 * 保存车位订单信息，id自动生成,在service层返回主键id
	 * </p>
	 * @param     parkingSpaceBill 车位订单信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:16:08</p>
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addParkingSpaceBill(ParkingSpaceBill parkingSpaceBill) {
		parkingSpaceBillDao.addParkingSpaceBill(parkingSpaceBill);
	}
	/**
	 * @Title: updateParkingSpaceBill
	 * <p>Description:
	 * 更改车位订单信息,当状态发生变换时需要把状态之前的数据保存到历史订单中
	 * </p>
	 * @param     parkingSpaceBill 车位订单信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:15:51</p>
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateParkingSpaceBill(ParkingSpaceBill parkingSpaceBill) {
		parkingSpaceBillDao.updateParkingSpaceBill(parkingSpaceBill);
	}
	/**
	 * @Title: delayParkingSpaceBill
	 * <p>Description:
	 * 延长订单,当状态发生变换时需要把状态之前的数据保存到历史订单中
	 * </p>
	 * @param     parkingSpaceBill 车位订单信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:15:51</p>
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delayParkingSpaceBill(ParkingSpaceBill parkingSpaceBill) {
		parkingSpaceBillDao.delayParkingSpaceBill(parkingSpaceBill);
	}
	/**
	 * @Title: deleteParkingSpaceBill
	 * <p>Description:
	 * 删除车位订单信息，数据保存到历史订单中
	 * </p>
	 * @param     orderJnlNo 车位订单编号
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:15:24</p>
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteParkingSpaceBill(String orderJnlNo) {
		parkingSpaceBillDao.deleteParkingSpaceBill(orderJnlNo);
	}
	/**
	 * @Title: getParkingSpaceBillList
	 * <p>Description:根据条件查询车位订单信息</p>
	 * @param     parkingSpaceBill 车位订单信息
	 * @return List<ParkingSpaceBill>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:15:03</p>
	 */
	@Override
	public List<ParkingSpaceBill> getParkingSpaceBillList(ParkingSpaceBill parkingSpaceBill){
		if(parkingSpaceBill == null) {
			parkingSpaceBill = new ParkingSpaceBill();
		}
		return parkingSpaceBillDao.getParkingSpaceBillList(parkingSpaceBill);
	}
	/**
	 * @Title: getParkingSpaceBill
	 * <p>Description:根据订单编号获取车位订单信息</p>
	 * @param     orderJnlNo 订单编号
	 * @param     delayParkHours 延长时间，用来判断停车的合法性
	 * @return ParkingSpaceBill    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:16:29</p>
	 */
	public ParkingSpaceBill getParkingSpaceBill(String orderJnlNo, int delayParkHours) {
		ParkingSpaceBill parkingSpaceBill = parkingSpaceBillDao.getParkingSpaceBill(orderJnlNo);
		if(parkingSpaceBill != null) {
			String spaceno = parkingSpaceBill.getSpaceno();
			//获取最大停车时间
			String maxParkHoursString = "";
			
			ParkingSpace parkingSpace = new ParkingSpace();
			parkingSpace.setParkHoursString("00:00:00");
			parkingSpace.setSpaceno(spaceno);
			List<ParkingSpace> list = parkingSpaceDao.getParkingSpaceEnableBillList(parkingSpace);
			if(list != null && list.size() > 0) {
				maxParkHoursString = list.get(0).getParkHoursString();
			}
			if(!StringUtils.isEmpty(maxParkHoursString)) {
				//查询可用差值
				parkingSpaceBill.setMaxParkHoursString(maxParkHoursString);
				parkingSpaceBill.setDelayParkHours(delayParkHours);
				parkingSpaceBill.setDelayParkHoursString("00:00:00");
				if("A".equalsIgnoreCase(maxParkHoursString)) {//无限制
					parkingSpaceBill.setMaxDelayParkHoursString(maxParkHoursString);
				}else {
					String maxDelayParkHoursString = parkingSpaceBillDao.getMaxDelayParkHoursString(parkingSpaceBill);
					parkingSpaceBill.setMaxDelayParkHoursString(maxDelayParkHoursString);
				}
			}
		}
		return parkingSpaceBill;
	}
}
