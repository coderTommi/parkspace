package com.parkspace.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.parkspace.db.rmdb.dao.ParkingSpaceDao;
import com.parkspace.db.rmdb.entity.ParkingSpace;
import com.parkspace.service.IParkingSpaceService;

/**
 * @Title: ParkingSpaceServiceImpl.java
 * @Package com.parkspace.service.impl
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午10:10:31</p>
*/
@Service("parkingSpaceService")
public class ParkingSpaceServiceImpl implements IParkingSpaceService{
	@Resource
	private ParkingSpaceDao parkingSpaceDao;
	/**
	 * @Title: getParkingSpace
	 * <p>Description:根据车位编号查询车位信息</p>
	 * @param     spaceno 车位编号
	 * @return ParkingSpace    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:10:51</p>
	 */
	@Override
	public ParkingSpace getParkingSpace(String spaceno) {
		return parkingSpaceDao.getParkingSpace(spaceno);
	}
	/**
	 * @Title: addParkingSpace
	 * <p>Description:
	 * 保存车位信息，车位编号手动输入
	 * 车位编号需要有自动生成规则
	 * </p>
	 * @param     parkingSpace 车位信息
	 * @return ParkingSpace    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:10:04</p>
	 */
	public ParkingSpace addParkingSpace(ParkingSpace parkingSpace){
		if(parkingSpace != null){
			parkingSpace.setCreateTime(new Date());
			parkingSpace.setModifyTime(new Date());
			//空闲状态
			parkingSpace.setParkStatus("0");
			//默认属于物业
			parkingSpace.setParkType("P");
			this.parkingSpaceDao.addParkingSpace(parkingSpace);
			return parkingSpace;
		}else{
			return null;
		}
	}
	/**
	 * @Title: updateParkingSpace
	 * <p>Description:更改车位信息</p>
	 * @param     parkingSpace 车位信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:09:51</p>
	 */
	public void updateParkingSpace(ParkingSpace parkingSpace){
		if(parkingSpace != null){
			parkingSpace.setModifyTime(new Date());
			this.parkingSpaceDao.updateParkingSpace(parkingSpace);
		}
	}
	/**
	 * @Title: deleteParkingSpace
	 * <p>Description:
	 * 删除车位信息,修改parkStatus为-1表示不对外开放）
	 * ,需要同时更新编辑人和编辑时间
	 * </p>
	 * @param     parkingSpace 车位信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:09:28</p>
	 */
	public void deleteParkingSpace(ParkingSpace parkingSpace){
		if(parkingSpace != null){
			parkingSpace.setModifyTime(new Date());
			this.parkingSpaceDao.deleteParkingSpace(parkingSpace);
		}
	}
	/**
	 * @Title: getParkingSpaceList
	 * <p>Description:根据条件查询车位信息</p>
	 * @param     parkingSpace 车位信息
	 * @return List<ParkingSpace>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:09:07</p>
	 */
	public List<ParkingSpace> getParkingSpaceList(ParkingSpace parkingSpace){
		if(parkingSpace == null){
			parkingSpace = new ParkingSpace();
		}
		return this.parkingSpaceDao.getParkingSpaceList(parkingSpace);
	}
}
