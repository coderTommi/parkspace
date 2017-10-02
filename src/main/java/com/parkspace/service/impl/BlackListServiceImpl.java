package com.parkspace.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkspace.db.rmdb.dao.BlackListDao;
import com.parkspace.db.rmdb.entity.BlackList;
import com.parkspace.service.IBlackListService;

/**
 * @Title: BlackListServiceImpl.java
 * @Package com.parkspace.service.impl
 * <p>Description:黑名单service实现类</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月2日 下午3:09:15</p>
*/
@Service("blackListService")
public class BlackListServiceImpl implements IBlackListService{
	@Resource
	private BlackListDao blackListDao;
	
	/**
	 * @Title: addBlackList
	 * <p>Description:需要加入黑名单的用户信息</p>
	 * @param     blackList 黑名单信息
	 * 包括：用户编号、备注信息等
	 * @return BlackList    返回类型，返回添加之后的信息
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午10:04:16</p>
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public BlackList addBlackList(BlackList blackList) {
		//获取主键信息
		String uuid = UUID.randomUUID().toString();
		if(blackList != null){
			BlackList newBlackList = new BlackList();
			newBlackList.setCreateTime(new Date());
			//默认否
			newBlackList.setIsCancel(0);
			//加入黑名单的原因
			newBlackList.setMemo(blackList.getMemo());
			newBlackList.setModifyTime(new Date());
			newBlackList.setUserId(blackList.getUserId());
			newBlackList.setUserType(blackList.getUserType());
			newBlackList.setUUID(uuid);
			blackListDao.addBlackList(newBlackList);
			return newBlackList;
		}
		return null;
	}
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
	@Override
	public List<BlackList> getBlackListAllInfoList(BlackList blackList) {
		List<BlackList> list = new ArrayList<BlackList>();
		if(blackList == null){
			blackList = new BlackList();
		}
		list = blackListDao.getBlackListAllInfoList(blackList);
		return list;
	}
}
