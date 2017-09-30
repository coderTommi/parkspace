package com.parkspace.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.parkspace.db.rmdb.dao.ShareConfigDao;
import com.parkspace.db.rmdb.entity.ShareConfig;
import com.parkspace.service.IShareConfigService;

/**
 * @Title: ShareConfigServiceImpl.java
 * @Package com.parkspace.service.impl
 * <p>Description:共享设置实现类service</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月27日 下午5:19:32</p>
*/
@Service("shareConfigService")
public class ShareConfigServiceImpl implements IShareConfigService{
	
	@Resource
	private ShareConfigDao shareConfigDao;
	
	/**
	 * @Title: getShareConfig
	 * <p>Description:根据id获取车位共享信息</p>
	 * @param     UUID 车位共享主键
	 * @return ShareConfig    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:04:46</p>
	 */
	public ShareConfig getShareConfig(String UUID) {
		return this.shareConfigDao.getShareConfig(UUID);
	}
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
	public ShareConfig addShareConfig(ShareConfig shareConfig) {
		shareConfig.setUUID(UUID.randomUUID().toString());
		shareConfig.setCreateTime(new Date());
		shareConfig.setModifyTime(new Date());
		//默认开启
		shareConfig.setIsOpen(1);
		//判断共享类型
		if(shareConfig.getShareType() == 1) {//1周期性时间段，0自定义时间段
			shareConfig.setStartDate(null);
			shareConfig.setEndDate(null);
			//判断周六与周天是否连续
			String internalDate = shareConfig.getInternalDate();
			if(internalDate != null && !"".equals(internalDate) 
					&& !"null".equalsIgnoreCase(internalDate)) {
				if(internalDate.contains("1") && internalDate.contains("7")) {
					internalDate += ",1";
				}
			}
			shareConfig.setInternalDate(internalDate);
		}else {
			shareConfig.setInternalDate(null);
		}
		//判断是否全天
		if(shareConfig.getIsAllDay() == 1) {
			shareConfig.setStartTime("00:00:00");
			shareConfig.setEndTime("23:59:59");
		}
		this.shareConfigDao.addShareConfig(shareConfig);
		return shareConfig;
	}
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
	public void updateShareConfig(ShareConfig shareConfig) {
		if(shareConfig != null) {
			shareConfig.setModifyTime(new Date());
			//判断共享类型
			if(shareConfig.getShareType() == 1) {//1周期性时间段，0自定义时间段
				shareConfig.setStartDate(null);
				shareConfig.setEndDate(null);
				//判断周六与周天是否连续
				String internalDate = shareConfig.getInternalDate();
				if(internalDate != null && !"".equals(internalDate) 
						&& !"null".equalsIgnoreCase(internalDate)) {
					if(internalDate.contains("1") && internalDate.contains("7")) {
						internalDate += ",1";
					}
				}
				shareConfig.setInternalDate(internalDate);
			}else {
				shareConfig.setInternalDate(null);
			}
			//判断是否全天
			if(shareConfig.getIsAllDay() == 1) {
				shareConfig.setStartTime("00:00:00");
				shareConfig.setEndTime("23:59:59");
			}
			this.shareConfigDao.updateShareConfig(shareConfig);
		}
	}
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
	public void deleteShareConfig(ShareConfig shareConfig) {
		if(shareConfig != null) {
			shareConfig.setModifyTime(new Date());
			this.shareConfigDao.deleteShareConfig(shareConfig);
		}
	}
	/**
	 * @Title: disableShareConfig
	 * <p>Description:禁用某个共享时间段</p>
	 * @param     UUID 共享主键
	 * @param     modifyBy 编辑人
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月27日 下午6:03:20</p>
	 */
	public void disableShareConfig(String UUID,String modifyBy) {
		ShareConfig shareConfig = new ShareConfig();
		shareConfig.setUUID(UUID);
		shareConfig.setModifyTime(new Date());
		shareConfig.setModifyBy(modifyBy);
		//禁用
		shareConfig.setIsOpen(0);
		this.shareConfigDao.updateShareConfig(shareConfig);
	}
	/**
	 * @Title: enableShareConfig
	 * <p>Description:开启某个共享时间段</p>
	 * @param     UUID 共享主键
	 * @param     modifyBy 编辑人
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月27日 下午6:03:20</p>
	 */
	public void enableShareConfig(String UUID,String modifyBy) {
		ShareConfig shareConfig = new ShareConfig();
		shareConfig.setUUID(UUID);
		shareConfig.setModifyTime(new Date());
		shareConfig.setModifyBy(modifyBy);
		//启用
		shareConfig.setIsOpen(1);
		this.shareConfigDao.updateShareConfig(shareConfig);
	}
	/**
	 * @Title: getShareConfigList
	 * <p>Description:根据条件查询车位共享信息</p>
	 * @param     shareConfig 车位共享信息
	 * @return List<ShareConfig>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:05:58</p>
	 */
	public List<ShareConfig> getShareConfigList(ShareConfig shareConfig){
		if(shareConfig == null) {
			shareConfig = new ShareConfig();
		}
		return this.shareConfigDao.getShareConfigList(shareConfig);
	}
	
	/**
	 * @Title: getShareConfigListBySpaceno
	 * <p>Description:根据车位编号获取共享信息
	 * 只获取该车位启用和未启用的共享信息（不包含删除的数据）
	 * </p>
	 * @param     spaceno 车位编号
	 * @return List<ShareConfig>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月28日 下午6:02:54</p>
	 */
	public List<ShareConfig> getShareConfigListBySpaceno(String spaceno){
		ShareConfig shareConfig = new ShareConfig();
		shareConfig.setSpaceno(spaceno);
		//查询未开启和开启的共享信息
		shareConfig.setIsOpenQuery(new Integer[] {0,1});
		return this.shareConfigDao.getShareConfigList(shareConfig);
	}
}
