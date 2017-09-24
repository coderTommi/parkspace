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
	/**
	 * @Title: getBlackList
	 * <p>Description:根据区域id获取黑名单信息</p>
	 * @param     UUID 黑名单主键
	 * @return BlackList    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:19:51</p>
	 */
	public BlackList getBlackList(String UUID);
	/**
	 * @Title: addBlackList
	 * <p>Description:
	 * 保存黑名单信息id自动生成,在service层返回主键id
	 * </p>
	 * @param     blackList 黑名单信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:19:37</p>
	 */
	public void addBlackList(BlackList blackList);
	/**
	 * @Title: updateBlackList
	 * <p>Description:更改黑名单信息</p>
	 * @param     blackList 黑名单信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:19:21</p>
	 */
	public void updateBlackList(BlackList blackList);
	/**
	 * @Title: deleteBlackList
	 * <p>Description:
	 * 删除黑名单信息,修改isCancel为1,需要同时更改编辑时间
	 * </p>
	 * @param     blackList 黑名单信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:18:59</p>
	 */
	public void deleteBlackList(BlackList blackList);
	/**
	 * @Title: getBlackListList
	 * <p>Description:根据条件查询黑名单信息</p>
	 * @param     blackList 黑名单信息
	 * @return List<BlackList>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:18:39</p>
	 */
	public List<BlackList> getBlackListList(BlackList blackList);
	
	/**
	 * @Title: getBlackListAllInfoList
	 * <p>Description:根据条件查询黑名单信息
	 * 包括用户基本信息
	 * </p>
	 * @param     blackList 黑名单信息
	 * @return List<BlackList>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:18:39</p>
	 */
	public List<BlackList> getBlackListAllInfoList(BlackList blackList);
}
