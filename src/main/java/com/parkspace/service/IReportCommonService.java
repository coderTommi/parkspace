package com.parkspace.service;

import java.util.List;

import com.parkspace.model.ReportCommonModel;

/**
 * @Title: IReportCommonService.java
 * @Package com.parkspace.service
 * <p>Description:报表统计service接口</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月8日 下午9:22:05</p>
*/

public interface IReportCommonService {
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
	 * <p>Description:认证车位数</p>
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
