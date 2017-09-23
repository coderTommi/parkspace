package com.parkspace.db.rmdb.dao;

import java.util.List;
import java.util.Map;

import com.parkspace.db.rmdb.entity.PropertyMgmtUser;

/**
 * @Title: PropertyMgmtUserDao.java
 * @Package com.parkspace.db.rmdb.dao
 * <p>Description:
 * 物业人员信息表（PropertyMgmtUser）:维护物业人员的管理关系
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午11:14:27</p>
*/

public interface PropertyMgmtUserDao {
	//根据小区id和用户id获取物业信息
	//构建hashmap comid和userId
	public PropertyMgmtUser getPropertyMgmtUser(Map<String,String> comidAndUserIdMap);
	//保存物业信息
	public void addPropertyMgmtUser(PropertyMgmtUser propertyMgmtUser);
	//更改物业信息
	public void updatePropertyMgmtUser(PropertyMgmtUser propertyMgmtUser);
	//删除物业信息,修改isAdmin为-1,需要同时更新编辑人和编辑时间
	public void deletePropertyMgmtUser(PropertyMgmtUser propertyMgmtUser);
	//根据条件查询物业信息
	public List<PropertyMgmtUser> getPropertyMgmtUserList(PropertyMgmtUser propertyMgmtUser);
}
