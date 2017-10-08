package com.parkspace.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.parkspace.db.rmdb.dao.ReportCommonDao;
import com.parkspace.model.ReportCommonModel;
import com.parkspace.service.IReportCommonService;

/**
 * @Title: ReportCommonServiceImpl.java
 * @Package com.parkspace.service.impl
 * <p>Description:报表统计service实现类</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月8日 下午9:22:33</p>
*/
@Service("reportCommonService")
public class ReportCommonServiceImpl implements IReportCommonService{
	@Resource
	private ReportCommonDao reportCommonDao;
	/**
	 * @Title: getCommunityReport
	 * <p>Description:推广小区报表</p>
	 * @param     reportCommonModel 过滤条件
	 * @return ReportCommonModel    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月8日 下午9:05:42</p>
	 */
	@Override
	public List<ReportCommonModel> getCommunityReport(ReportCommonModel reportCommonModel){
		if(reportCommonModel == null) {
			reportCommonModel = new ReportCommonModel();
		}
		return reportCommonDao.getCommunityReport(reportCommonModel);
	}
	
	/**
	 * @Title: getParkingSpaceBillHisReport
	 * <p>Description:车位订单</p>
	 * @param     reportCommonModel 过滤条件
	 * @return ReportCommonModel    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月8日 下午9:05:42</p>
	 */
	@Override
	public List<ReportCommonModel> getParkingSpaceBillHisReport(ReportCommonModel reportCommonModel){
		if(reportCommonModel == null) {
			reportCommonModel = new ReportCommonModel();
		}
		return reportCommonDao.getParkingSpaceBillHisReport(reportCommonModel);
	}
	
	/**
	 * @Title: getCertifiedParkingSpaceReport
	 * <p>Description:认证车位数</p>
	 * @param     reportCommonModel 过滤条件
	 * @return ReportCommonModel    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月8日 下午9:05:42</p>
	 */
	@Override
	public List<ReportCommonModel> getCertifiedParkingSpaceReport(ReportCommonModel reportCommonModel){
		if(reportCommonModel == null) {
			reportCommonModel = new ReportCommonModel();
		}
		return reportCommonDao.getCertifiedParkingSpaceReport(reportCommonModel);
	}
	
	/**
	 * @Title: getActivingParkingSpaceReport
	 * <p>Description:查询活跃车位数</p>
	 * @param     reportCommonModel 过滤条件
	 * @return ReportCommonModel    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月8日 下午9:05:42</p>
	 */
	@Override
	public List<ReportCommonModel> getActivingParkingSpaceReport(ReportCommonModel reportCommonModel){
		if(reportCommonModel == null) {
			reportCommonModel = new ReportCommonModel();
		}
		return reportCommonDao.getActivingParkingSpaceReport(reportCommonModel);
	}
	
	/**
	 * @Title: getCertifiedUserReport
	 * <p>Description:查询认证用户数（车主和业主）</p>
	 * @param     reportCommonModel 过滤条件
	 * @return ReportCommonModel    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月8日 下午9:05:42</p>
	 */
	@Override
	public List<ReportCommonModel> getCertifiedUserReport(ReportCommonModel reportCommonModel){
		if(reportCommonModel == null) {
			reportCommonModel = new ReportCommonModel();
		}
		return reportCommonDao.getCertifiedUserReport(reportCommonModel);
	}
	
	/**
	 * @Title: getActivingUserReport
	 * <p>Description:查询活跃用户数（车主和业主）</p>
	 * @param     reportCommonModel 过滤条件
	 * @return ReportCommonModel    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月8日 下午9:05:42</p>
	 */
	@Override
	public List<ReportCommonModel> getActivingUserReport(ReportCommonModel reportCommonModel){
		if(reportCommonModel == null) {
			reportCommonModel = new ReportCommonModel();
		}
		return reportCommonDao.getActivingUserReport(reportCommonModel);
	}
}
