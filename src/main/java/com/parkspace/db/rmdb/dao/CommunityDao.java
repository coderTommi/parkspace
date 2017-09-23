package com.parkspace.db.rmdb.dao;

import java.util.List;

import com.parkspace.db.rmdb.entity.Community;

/**
 * @Title: CommunityDao.java
 * @Package com.parkspace.db.rmdb.dao
 * <p>Description:
 * 小区表:记录小区的基本信息
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午11:14:27</p>
*/

public interface CommunityDao {
	//根据小区id获取小区信息
	public Community getCommunity(String comid);
	//保存小区信息id自动生成,在service层返回主键id
	public void addCommunity(Community community);
	//更改小区信息
	public void updateCommunity(Community community);
	//删除小区信息,修改isenable为2,需要同时更新编辑人和编辑时间
	public void deleteCommunity(Community community);
	//根据条件查询小区信息
	public List<Community> getCommunityList(Community community);
}
