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
	/**
	 * @Title: getShareConfig
	 * <p>Description:根据id获取车位共享信息</p>
	 * @param     UUID 车位共享主键
	 * @return ShareConfig    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:04:46</p>
	 */
	public ShareConfig getShareConfig(String UUID);
	/**
	 * @Title: addShareConfig
	 * <p>Description:
	 * 保存车位共享信息id自动生成,在service层返回主键id
	 * </p>
	 * @param     shareConfig 车位共享信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:05:09</p>
	 */
	public void addShareConfig(ShareConfig shareConfig);
	/**
	 * @Title: updateShareConfig
	 * <p>Description:
	 * 更改车位共享信息
	 * </p>
	 * @param     shareConfig 车位共享信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:05:35</p>
	 */
	public void updateShareConfig(ShareConfig shareConfig);
	/**
	 * @Title: deleteShareConfig
	 * <p>Description:
	 * 删除车位共享信息,修改isOpen为-1,需要同时更新编辑人和编辑时间
	 * </p>
	 * @param     shareConfig 车位共享信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:06:12</p>
	 */
	public void deleteShareConfig(ShareConfig shareConfig);
	/**
	 * @Title: getShareConfigList
	 * <p>Description:根据条件查询车位共享信息</p>
	 * @param     shareConfig 车位共享信息
	 * @return List<ShareConfig>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:05:58</p>
	 */
	public List<ShareConfig> getShareConfigList(ShareConfig shareConfig);
}
