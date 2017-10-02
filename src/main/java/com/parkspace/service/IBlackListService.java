package com.parkspace.service;

import java.util.List;

import com.parkspace.db.rmdb.entity.BlackList;

/**
 * @Title: IBlackListService.java
 * @Package com.parkspace.service
 * <p>Description:黑名单service接口</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月2日 下午3:08:50</p>
*/

public interface IBlackListService {
	/**
	 * @Title: addBlackList
	 * <p>Description:需要加入黑名单的用户信息
	 * 将某个业主加入黑名单，要写明事由
	 * </p>
	 * @param     blackList 黑名单信息
	 * 包括：用户编号、备注信息等
	 * @return BlackList    返回类型，返回添加之后的信息
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午10:04:16</p>
	 */
	public BlackList addBlackList(BlackList blackList);
	/**
	 * @Title: getBlackListAllInfoList
	 * <p>Description:
	 * 查看所有加入黑名单的业主
	 * </p>
	 * @param     blackList 过滤条件，如果为空查询所有
	 * @return List<BlackList>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午10:13:17</p>
	 */
	public List<BlackList> getBlackListAllInfoList(BlackList blackList);
}
