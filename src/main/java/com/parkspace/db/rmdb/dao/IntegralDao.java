package com.parkspace.db.rmdb.dao;

import com.parkspace.db.rmdb.entity.Integral;

/**
 * 积分dao.
 * @author lidongliang
 *
 */
public interface IntegralDao {
	public void save(Integral integral);
	
	public void update(Integral integral);
	
	public Integral getByUserId(String userId);
	
}
