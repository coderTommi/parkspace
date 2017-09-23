package com.parkspace.db.rmdb.dao;

import java.util.List;
import java.util.Map;

import com.parkspace.db.rmdb.entity.Caruser;

/**
 * @Title: CaruserDao.java
 * @Package com.parkspace.db.rmdb.dao
 * <p>Description:
 * 车主表信息
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午11:14:27</p>
*/

public interface CaruserDao {
	//根据用户编号和车牌号获取车主信息
	public Caruser getCaruser(Map<String,String> userIdAndcarnoMap);
	//保存车主信息
	public void addCaruser(Caruser Caruser);
	//更改车主信息
	public void updateCaruser(Caruser Caruser);
	//删除车主信息,修改isauth为-1,需要同时更新编辑人和编辑时间
	public void deleteCaruser(Caruser Caruser);
	//根据条件查询车主信息
	public List<Caruser> getCaruserList(Caruser Caruser);
}
