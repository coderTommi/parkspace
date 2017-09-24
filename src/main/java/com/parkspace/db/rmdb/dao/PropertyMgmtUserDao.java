package com.parkspace.db.rmdb.dao;

import java.util.List;

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
	/**
	 * @Title: getPropertyMgmtUser
	 * <p>Description:
	 * 根据用户id获取物业信息
	 * </p>
	 * @param     userId 用户ID
	 * @return PropertyMgmtUser    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:08:13</p>
	 */
	public PropertyMgmtUser getPropertyMgmtUser(String userId);
	/**
	 * @Title: addPropertyMgmtUser
	 * <p>Description:保存物业信息</p>
	 * @param     propertyMgmtUser 物业管理员信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:08:01</p>
	 */
	public void addPropertyMgmtUser(PropertyMgmtUser propertyMgmtUser);
	/**
	 * @Title: updatePropertyMgmtUser
	 * <p>Description:更改物业信息</p>
	 * @param     propertyMgmtUser 物业管理员信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:07:46</p>
	 */
//	public void updatePropertyMgmtUser(PropertyMgmtUser propertyMgmtUser);
	/**
	 * @Title: deletePropertyMgmtUser
	 * <p>Description:
	 * 删除物业信息,修改isAdmin为-1,需要同时更新编辑人和编辑时间
	 * </p>
	 * @param     propertyMgmtUser 物业管理员信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:07:19</p>
	 */
//	public void deletePropertyMgmtUser(PropertyMgmtUser propertyMgmtUser);
	/**
	 * @Title: getPropertyMgmtUserList
	 * <p>Description:根据条件查询物业信息</p>
	 * @param     propertyMgmtUser 物业管理员信息
	 * @return List<PropertyMgmtUser>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:06:49</p>
	 */
	public List<PropertyMgmtUser> getPropertyMgmtUserList(PropertyMgmtUser propertyMgmtUser);
}
