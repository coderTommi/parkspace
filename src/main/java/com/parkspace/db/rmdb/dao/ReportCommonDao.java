package com.parkspace.db.rmdb.dao;

import java.util.List;

import com.parkspace.model.ReportCommonModel;

/**
 * @Title: ReportCommonDao.java
 * @Package com.parkspace.db.rmdb.dao
 * <p>Description:报表统计dao接口定义</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月8日 下午8:59:11</p>
*/

public interface ReportCommonDao {
	/**
	 * @Title: getCommunityReport
	 * <p>Description:推广小区报表</p>
	 * @param     reportCommonModel 过滤条件
	 * @return ReportCommonModel    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月8日 下午9:05:42</p>
	 */
	public List<ReportCommonModel> getCommunityReport(ReportCommonModel reportCommonModel);
	
	/**
	 * @Title: getParkingSpaceBillHisReport
	 * <p>Description:车位订单</p>
	 * @param     reportCommonModel 过滤条件
	 * @return ReportCommonModel    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月8日 下午9:05:42</p>
	 */
	public List<ReportCommonModel> getParkingSpaceBillHisReport(ReportCommonModel reportCommonModel);

	/**
	 * @Title: getCertifiedParkingSpaceReport
	 * <p>Description:查询认证车位数</p>
	 * @param     reportCommonModel 过滤条件
	 * @return ReportCommonModel    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月8日 下午9:05:42</p>
	 */
	public List<ReportCommonModel> getCertifiedParkingSpaceReport(ReportCommonModel reportCommonModel);
	
	/**
	 * @Title: getActivingParkingSpaceReport
	 * <p>Description:查询活跃车位数</p>
	 * @param     reportCommonModel 过滤条件
	 * @return ReportCommonModel    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月8日 下午9:05:42</p>
	 */
	public List<ReportCommonModel> getActivingParkingSpaceReport(ReportCommonModel reportCommonModel);
	
	/**
	 * @Title: getCertifiedUserReport
	 * <p>Description:查询认证用户数（车主和业主）</p>
	 * @param     reportCommonModel 过滤条件
	 * @return ReportCommonModel    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月8日 下午9:05:42</p>
	 */
	public List<ReportCommonModel> getCertifiedUserReport(ReportCommonModel reportCommonModel);
	
	/**
	 * @Title: getActivingUserReport
	 * <p>Description:查询活跃用户数（车主和业主）</p>
	 * @param     reportCommonModel 过滤条件
	 * @return ReportCommonModel    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月8日 下午9:05:42</p>
	 */
	public List<ReportCommonModel> getActivingUserReport(ReportCommonModel reportCommonModel);
	
}
