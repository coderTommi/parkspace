package com.parkspace.db.rmdb.dao;

import java.util.List;

import com.parkspace.db.rmdb.entity.SpaceOwner;

/**
 * @Title: SpaceOwnerDao.java
 * @Package com.parkspace.db.rmdb.dao
 * <p>Description:
 * 车位业主信息表,包含物业车位信息
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午11:14:27</p>
*/

public interface SpaceOwnerDao {
	//根据车位编号获取车位业主信息
	public SpaceOwner getSpaceOwner(String spaceno);
	//保存车位业主信息
	public void addSpaceOwner(SpaceOwner spaceOwner);
	//更改车位业主信息
	public void updateSpaceOwner(SpaceOwner spaceOwner);
	//删除车位业主信息,修改isauth为-1,需要同时更新编辑人和编辑时间
	public void deleteSpaceOwner(SpaceOwner spaceOwner);
	//根据条件查询车位业主信息
	public List<SpaceOwner> getSpaceOwnerList(SpaceOwner spaceOwner);
}
