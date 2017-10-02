package com.parkspace.service.impl;

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
}
