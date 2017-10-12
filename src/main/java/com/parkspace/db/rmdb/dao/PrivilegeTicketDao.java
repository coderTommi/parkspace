package com.parkspace.db.rmdb.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.parkspace.db.rmdb.entity.PrivilegeTicket;

/**
 * 优惠券
 * @author lidongliang
 *
 */
@MapperScan
public interface PrivilegeTicketDao {
	/**
	 * 新增
	 * @Title: save
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月9日 上午11:16:19</p>
	 */
	public void save(PrivilegeTicket ticket);
	/**
	 * 修改
	 * @Title: update
	 * <p>Description:</p>
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月9日 上午11:16:35</p>
	 */
	public void update(PrivilegeTicket ticket);
	/**
	 * 金额排序查询
	 * @Title: getById
	 * <p>Description:</p>
	 * @param     参数
	 * @return List<PrivilegeTicket>    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月9日 上午11:17:42</p>
	 */
	public List<PrivilegeTicket> getByUserId_amt(PrivilegeTicket ticket);
	/**
	 * 时间排序查询
	 * @Title: getByUserId_time
	 * <p>Description:</p>
	 * @param     参数
	 * @return List<PrivilegeTicket>    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月9日 上午11:18:11</p>
	 */
	public List<PrivilegeTicket> getByUserId_time(PrivilegeTicket ticket);
}
