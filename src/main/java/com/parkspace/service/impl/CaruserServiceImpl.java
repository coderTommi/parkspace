package com.parkspace.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.parkspace.db.rmdb.dao.CaruserDao;
import com.parkspace.db.rmdb.entity.Caruser;
import com.parkspace.service.ICaruserService;

/**
 * @Title: CaruserServiceImpl.java
 * @Package com.parkspace.service.impl
 * <p>Description:车主service实现类</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月2日 上午9:23:57</p>
*/
@Service("caruserService")
public class CaruserServiceImpl implements ICaruserService{
	@Resource
	private CaruserDao caruserDao;
	/**
	 * @Title: getCaruserCount
	 * <p>Description:根据条件查询车主数量</p>
	 * @param     caruser 车主信息
	 * @return int    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:20:10</p>
	 */
	@Override
	public int getCaruserCount(Caruser caruser) {
		if(caruser == null) {
			caruser = new Caruser();
		}
		return caruserDao.getCaruserCount(caruser);
	}
	
	/**
	 * @Title: getSpaceOwnerAllInfoList
	 * <p>Description:
	 * 根据条件查询车主信息，信息包括
	 * 小区信息、行政区域信息、用户基本信息等
	 * </p>
	 * @param     caruser 车位业主信息
	 * @return List<Caruser>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月24日 上午1:42:12</p>
	 */
	@Override
	public List<Caruser> getCaruserAllInfoList(Caruser caruser){
		if(caruser == null) {
			caruser = new Caruser();
		}
		return caruserDao.getCaruserAllInfoList(caruser);
	}
	
	/**
	 * @Title: getSpaceOwnerCount
	 * <p>Description:
	 * 根据条件查询车主数量
	 * </p>
	 * @param     caruser 车主信息
	 * @return int    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月24日 上午1:42:12</p>
	 */
	@Override
	public int getCaruserAllInfoCount(Caruser caruser) {
		if(caruser == null) {
			caruser = new Caruser();
		}
		return caruserDao.getCaruserAllInfoCount(caruser);
	}
}
