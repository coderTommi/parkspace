package com.parkspace.service;

import java.util.List;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.entity.ShareConfig;

/**
 * @Title: IShareConfigService.java
 * @Package com.parkspace.service
 * <p>Description:共享时间设置service接口</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月27日 下午5:19:09</p>
*/

public interface IShareConfigService {
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
	 * @return ShareConfig    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:05:09</p>
	 */
	public ShareConfig addShareConfig(ShareConfig shareConfig);
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
	 * 删除车位共享信息,修改isOpen为0,需要同时更新编辑人和编辑时间
	 * </p>
	 * @param     shareConfig 车位共享信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:06:12</p>
	 */
	public void deleteShareConfig(ShareConfig shareConfig);
	/**
	 * @Title: disableShareConfig
	 * <p>Description:禁用某个共享时间段</p>
	 * @param     UUID 共享主键
	 * @param     modifyBy 编辑人
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月27日 下午6:03:20</p>
	 */
	public void disableShareConfig(String UUID, String modifyBy);
	/**
	 * @Title: enableShareConfig
	 * <p>Description:开启某个共享时间段</p>
	 * @param     UUID 共享主键
	 * @param     modifyBy 编辑人
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月27日 下午6:03:20</p>
	 */
	public void enableShareConfig(String UUID, String modifyBy);
	
	/**
	 * @Title: getShareConfigList
	 * <p>Description:根据条件查询车位共享信息
	 * 所有类型的数据包括启用、未启用和删除的共享信息
	 * </p>
	 * @param     shareConfig 车位共享信息
	 * @return List<ShareConfig>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:05:58</p>
	 */
	public List<ShareConfig> getShareConfigList(ShareConfig shareConfig);
	/**
	 * @Title: getShareConfigListBySpaceno
	 * <p>Description:根据车位编号获取共享信息
	 * 只获取该车位启用和未启用的共享信息（不包含删除的数据）
	 * </p>
	 * @param     spaceno 车位编号
	 * @return List<ShareConfig>    返回类型
	 * @throws PackspaceServiceException
	 * <p>CreateDate:2017年9月28日 下午6:02:54</p>
	 */
	public List<ShareConfig> getShareConfigListBySpaceno(String spaceno) 
			throws ParkspaceServiceException;
	
	/**
	 * @Title: getEnableShareConfigList
	 * <p>Description:根据条件查询车位共享信息
	 * 查询的信息表示从当时间预约，可预约的车位共享信息
	 * </p>
	 * @param     shareConfig 车位共享信息
	 * @return List<ShareConfig>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:05:58</p>
	 */
	public List<ShareConfig> getEnableShareConfigList(ShareConfig shareConfig);
}
