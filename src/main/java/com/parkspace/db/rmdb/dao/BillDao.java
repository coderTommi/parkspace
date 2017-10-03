package com.parkspace.db.rmdb.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.parkspace.db.rmdb.entity.Bill;

/**
 * 账单dao
 * @author lidongliang
 *
 */
@MapperScan
public interface BillDao {
	/**
	 * 保存
	 * @param bill
	 */
	public void save(Bill bill);
	/**
	 * 更新
	 * @Title: update
	 * <p>Description:</p>
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月3日 下午2:48:49</p>
	 */
	public void update(Bill bill);
	
	/**
	 * 根据id查询
	 * @param billId
	 * @return
	 */
	public Bill getById(String billId);
	/**
	 * 根据用户查询列表
	 * @param bill
	 * @return
	 */
	public List<Bill> getBills(Bill bill);
	
}
