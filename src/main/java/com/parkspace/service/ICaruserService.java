package com.parkspace.service;

import com.parkspace.db.rmdb.entity.Caruser;

/**
 * @Title: ICaruserService.java
 * @Package com.parkspace.service
 * <p>Description:车主service接口</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月2日 上午9:23:24</p>
*/

public interface ICaruserService {
	/**
	 * @Title: getCaruserCount
	 * <p>Description:根据条件查询车主数量</p>
	 * @param     caruser 车主信息
	 * @return int    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:20:10</p>
	 */
	public int getCaruserCount(Caruser caruser);
}
