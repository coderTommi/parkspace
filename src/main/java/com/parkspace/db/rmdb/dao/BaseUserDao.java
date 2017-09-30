package com.parkspace.db.rmdb.dao;

import org.mybatis.spring.annotation.MapperScan;

import com.parkspace.db.rmdb.entity.BaseUser;

/**
 * 用户基础信息表
 * @author lidongliang
 *
 */
@MapperScan
public interface BaseUserDao {
	/**
	 * 新增
	 * @param baseUser
	 */
	public void save(BaseUser baseUser);
	/**
	 * 删除
	 * @param userId
	 */
	public void delete(String userId);
	/**
	 * 更新
	 * @param baseUser
	 */
	public void update(BaseUser baseUser);
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public BaseUser getById(String userId);
	/**
	 * 根據手機號查詢
	 * @param telePhone
	 * @return
	 */
	public BaseUser getByTelphone(String telePhone);
}
