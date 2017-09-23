package com.parkspace.db.rmdb.dao;

import java.util.List;

import com.parkspace.db.rmdb.entity.Zone;

/**
 * @Title: ZoneDao.java
 * @Package com.parkspace.db.rmdb.dao
 * <p>Description:
 * 行政区域（Zone），记录区域的基本信息
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午11:14:27</p>
*/

public interface ZoneDao {
	/**
	 * @Title: getZone
	 * <p>Description:
	 * 根据区域id获取区域信息
	 * </p>
	 * @param     zoneid 区域编号
	 * @return Zone    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:00:12</p>
	 */
	public Zone getZone(String zoneid);
	/**
	 * @Title: addZone
	 * <p>Description:
	 * 保存区域信息id自动生成,在service层返回主键id
	 * </p>
	 * @param     zone 区域信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:00:49</p>
	 */
	public void addZone(Zone zone);
	/**
	 * 
	 * @Title: updateZone
	 * <p>Description:
	 * 更改区域信息
	 * </p>
	 * @param     zone 需要更改的区域信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:01:18</p>
	 */
	public void updateZone(Zone zone);
	/**
	 * 
	 * @Title: deleteZone
	 * <p>Description:
	 * 删除区域信息,修改isenable为2,需要同时更新编辑人和编辑时间
	 * </p>
	 * @param     zone 需要删除的区域信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:01:50</p>
	 */
	public void deleteZone(Zone zone);
	/**
	 * @Title: getZoneList
	 * <p>Description:
	 * 根据条件查询区域信息
	 * </p>
	 * @param     zone 查询条件
	 * @return List<Zone>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:02:14</p>
	 */
	public List<Zone> getZoneList(Zone zone);
}
