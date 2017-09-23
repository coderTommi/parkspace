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
	//根据区域id获取区域信息
	public Zone getZone(String zoneid);
	//保存区域信息id自动生成,在service层返回主键id
	public void addZone(Zone zone);
	//更改区域信息
	public void updateZone(Zone zone);
	//删除区域信息,修改isenable为2,需要同时更新编辑人和编辑时间
	public void deleteZone(Zone zone);
	//根据条件查询区域信息
	public List<Zone> getZoneList(Zone zone);
}
