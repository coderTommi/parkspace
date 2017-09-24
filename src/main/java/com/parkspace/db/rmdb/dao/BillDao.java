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
	 * 根据id查询
	 * @param billId
	 * @return
	 */
	public Bill getById(String billId);
	/**
	 * 根据用户查询列表
	 * @param userId
	 * @return
	 */
	public List<Bill> getBills(Bill bill);
	
}
