package com.parkspace.db.rmdb.dao;

import java.util.List;
import java.util.Map;

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
	/**
	 * 查询用户收付款信息
	 * @Title: getByPayeeOrPayer
	 * <p>Description:</p>
	 * @param     参数
	 * @return List<Bill>    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月8日 上午9:34:55</p>
	 */
	public List<Bill> getByUserId(Bill bill);
	
	public List<Bill> getIncomeList(Map qryMap);
	/**
	 * 提现列表
	 * @Title: getByBillType
	 * <p>Description:</p>
	 * @param     参数
	 * @return List<Bill>    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月12日 下午4:14:55</p>
	 */
	public List<Bill> getByBillType(Map qryMap);
	
}
