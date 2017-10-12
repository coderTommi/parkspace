package com.parkspace.db.rmdb.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.parkspace.db.rmdb.entity.SpaceApply;

/**
 * @Title: SpaceApplyDao.java
 * @Package com.parkspace.db.rmdb.dao
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月12日 下午5:20:53</p>
*/
@MapperScan
public interface SpaceApplyDao {
	
	public void save(SpaceApply apply);
	
	public void update(SpaceApply apply);
	
	public List<SpaceApply> qryList(SpaceApply apply);
	
	public SpaceApply getById(String id);

}
