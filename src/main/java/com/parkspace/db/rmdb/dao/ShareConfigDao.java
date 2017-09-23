package com.parkspace.db.rmdb.dao;

import java.util.List;

import com.parkspace.db.rmdb.entity.ShareConfig;

/**
 * @Title: ShareConfigDao.java
 * @Package com.parkspace.db.rmdb.dao
 * <p>Description:
 * 共享时间设置表：记录车位的共享时间段的设置信息
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午11:14:27</p>
*/

public interface ShareConfigDao {
	//根据id获取车位共享信息
	public ShareConfig getShareConfig(String UUID);
	//保存车位共享信息id自动生成,在service层返回主键id
	public void addShareConfig(ShareConfig shareConfig);
	//更改车位共享信息
	public void updateShareConfig(ShareConfig shareConfig);
	//删除车位共享信息,修改isOpen为0,需要同时更新编辑人和编辑时间
	public void deleteShareConfig(ShareConfig shareConfig);
	//根据条件查询车位共享信息
	public List<ShareConfig> getShareConfigList(ShareConfig shareConfig);
}
