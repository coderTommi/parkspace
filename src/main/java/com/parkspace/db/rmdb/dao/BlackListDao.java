package com.parkspace.db.rmdb.dao;

import java.util.List;

import com.parkspace.db.rmdb.entity.BlackList;

/**
 * @Title: BlackListDao.java
 * @Package com.parkspace.db.rmdb.dao
 * <p>Description:
 * 黑名单，记录用户黑名单信息
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午11:14:27</p>
*/

public interface BlackListDao {
	//根据区域id获取黑名单信息
	public BlackList getBlackList(String UUID);
	//保存黑名单信息id自动生成,在service层返回主键id
	public void addBlackList(BlackList blackList);
	//更改黑名单信息
	public void updateBlackList(BlackList blackList);
	//删除黑名单信息,修改isCancel为1,需要同时更改编辑时间
	public void deleteBlackList(BlackList blackList);
	//根据条件查询黑名单信息
	public List<BlackList> getBlackListList(BlackList blackList);
}
