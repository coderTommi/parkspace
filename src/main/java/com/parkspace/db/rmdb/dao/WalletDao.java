package com.parkspace.db.rmdb.dao;

import org.mybatis.spring.annotation.MapperScan;

import com.parkspace.db.rmdb.entity.Wallet;

/**
 * 钱包操作表DAO
 * @author lidongliang
 *
 */
@MapperScan
public interface WalletDao {
	/** 初始化钱包-新增. **/
	public void save(Wallet wallet);
	/** 修改 **/
	public void update(Wallet wallet);
	
	/** 查询详情 **/
	public Wallet getByUserId(String userId);
}
