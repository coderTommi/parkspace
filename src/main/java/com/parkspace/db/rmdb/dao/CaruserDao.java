package com.parkspace.db.rmdb.dao;

import java.util.List;
import java.util.Map;

import com.parkspace.db.rmdb.entity.Caruser;

/**
 * @Title: CaruserDao.java
 * @Package com.parkspace.db.rmdb.dao
 * <p>Description:
 * 车主表信息
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午11:14:27</p>
*/

public interface CaruserDao {
	/**
	 * @Title: getCaruser
	 * <p>Description:
	 * 根据用户编号和车牌号获取车主信息
	 * </p>
	 * @param     userIdAndcarnoMap 构建查询map
	 * @return Caruser    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:21:21</p>
	 */
	public Caruser getCaruser(Map<String,String> userIdAndcarnoMap);
	/**
	 * @Title: addCaruser
	 * <p>Description:保存车主信息</p>
	 * @param     Caruser 车主信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:20:53</p>
	 */
	public void addCaruser(Caruser Caruser);
	/**
	 * @Title: updateCaruser
	 * <p>Description:更改车主信息</p>
	 * @param     Caruser 车主信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:20:47</p>
	 */
	public void updateCaruser(Caruser Caruser);
	/**
	 * @Title: deleteCaruser
	 * <p>Description:
	 * 删除车主信息,修改isauth为-1,需要同时更新编辑人和编辑时间
	 * </p>
	 * @param     Caruser 车主信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:20:39</p>
	 */
	public void deleteCaruser(Caruser Caruser);
	/**
	 * @Title: getCaruserList
	 * <p>Description:根据条件查询车主信息</p>
	 * @param     Caruser 车主信息
	 * @return List<Caruser>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:20:10</p>
	 */
	public List<Caruser> getCaruserList(Caruser Caruser);
}
