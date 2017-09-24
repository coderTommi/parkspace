package com.parkspace.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.parkspace.db.rmdb.dao.ZoneDao;
import com.parkspace.db.rmdb.entity.Zone;
import com.parkspace.service.IZoneService;

/**
 * @Title: ZoneServiceImpl.java
 * @Package com.parkspace.service.impl
 * <p>Description:行政区域管理service实现类</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月24日 上午2:58:00</p>
*/
@Service("zoneService")
public class ZoneServiceImpl implements IZoneService{
	@Resource
	private ZoneDao zoneDao;
	/**
	 * @Title: getZone
	 * <p>Description:
	 * 根据区域id获取区域信息
	 * </p>
	 * @param     zoneid 区域编号
	 * @return Zone    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:00:12</p>
	 */
	public Zone getZone(String zoneid){
		if(zoneid != null && !"".equals(zoneid)){
			return this.zoneDao.getZone(zoneid);
		}
		return null;
	}
	/**
	 * @Title: addZone
	 * <p>Description:
	 * 保存区域信息id自动生成,在service层返回主键id
	 * </p>
	 * @param     zone 区域信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:00:49</p>
	 */
	public Zone addZone(Zone zone){
		Zone returnZone = new Zone();
		if(zone != null){
			returnZone.setCity(zone.getCity());
			returnZone.setCreateBy(zone.getCreateBy());
			returnZone.setCreateTime(new Date());
			//表示启用
			returnZone.setIsenable(1);
			returnZone.setMemo(zone.getMemo());
			returnZone.setModifyBy(zone.getModifyBy());
			returnZone.setModifyTime(new Date());
			returnZone.setProvince(zone.getProvince());
			returnZone.setZone(zone.getZone());
			returnZone.setZoneid(UUID.randomUUID().toString());
			returnZone.setZonename(zone.getZonename());
			this.zoneDao.addZone(returnZone);
		}
		return returnZone;
	}
	/**
	 * 
	 * @Title: updateZone
	 * <p>Description:
	 * 更改区域信息
	 * </p>
	 * @param     zone 需要更改的区域信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:01:18</p>
	 */
	public void updateZone(Zone zone){
		if(zone != null){
			zone.setModifyTime(new Date());
			this.zoneDao.updateZone(zone);
		}
	}
	/**
	 * 
	 * @Title: deleteZone
	 * <p>Description:
	 * 删除区域信息,修改isenable为-1,需要同时更新编辑人和编辑时间
	 * </p>
	 * @param     zone 需要删除的区域信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:01:50</p>
	 */
	public void deleteZone(Zone zone){
		if(zone != null){
			zone.setIsenable(-1);
			zone.setModifyTime( new Date());
			this.zoneDao.deleteZone(zone);
		}
	}
	/**
	 * @Title: getZoneList
	 * <p>Description:
	 * 根据条件查询区域信息
	 * </p>
	 * @param     zone 查询条件
	 * @return List<Zone>    返回类型
	 * @throws
	 * <p>CreateDate:2017年9月23日 下午9:02:14</p>
	 */
	public List<Zone> getZoneList(Zone zone){
		List<Zone> list = new ArrayList<Zone>();
		if(zone == null){
			zone = new Zone();
		}
		list = this.zoneDao.getZoneList(zone);
		return list;
	}
}
