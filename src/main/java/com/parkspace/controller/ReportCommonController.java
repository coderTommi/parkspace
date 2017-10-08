package com.parkspace.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.parkspace.common.OperationResult;
import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.model.ReportCommonModel;
import com.parkspace.service.IReportCommonService;

/**
 * @Title: ReportCommonController.java
 * @Package com.parkspace.controller
 * <p>Description:报表controller</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月8日 下午9:25:09</p>
*/
@Controller
@RequestMapping("/v1/report")
public class ReportCommonController {
	/**
     * 获取日志接口.
     */
    private static final Log LOG = LogFactory.getLog(ReportCommonController.class);
	@Resource
	private IReportCommonService reportCommonService;
	/**
	 * 
	 * @Title: getCommunityReport
	 * <p>Description:查询推广的小区
	 * /v1/report/getcommunityreport
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getcommunityreport")
    @ResponseBody
	public OperationResult getCommunityReport(
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize,
            @RequestBody ReportCommonModel reportCommonModel,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		PageHelper.startPage(page, pageSize);
		try {
			List<ReportCommonModel> list = reportCommonService.getCommunityReport(reportCommonModel);
			res.setFlag(true);
			if(list != null && list.size() > 0) {
				PageInfo<ReportCommonModel> listPage = new PageInfo<ReportCommonModel>(list);
				res.setResData(listPage);
			}else {
				res.setResData(list);
			}
		}catch(ParkspaceServiceException e) {
			LOG.error("查询推广的小区报表数据失败："+"{"+reportCommonModel+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * 
	 * @Title: getParkingSpaceBillHisReport
	 * <p>Description:查询车位订单趋势图
	 * /v1/report/getparkingspacebillhisreport
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getparkingspacebillhisreport")
    @ResponseBody
	public OperationResult getParkingSpaceBillHisReport(
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize,
            @RequestBody ReportCommonModel reportCommonModel,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		PageHelper.startPage(page, pageSize);
		try {
			reportCommonModel.setStatusQuery(new String[] {"4"});//只查询已结算的
			List<ReportCommonModel> list = reportCommonService.getParkingSpaceBillHisReport(reportCommonModel);
			res.setFlag(true);
			if(list != null && list.size() > 0) {
				PageInfo<ReportCommonModel> listPage = new PageInfo<ReportCommonModel>(list);
				res.setResData(listPage);
			}else {
				res.setResData(list);
			}
		}catch(ParkspaceServiceException e) {
			LOG.error("查询推广的订单趋势图报表数据失败："+"{"+reportCommonModel+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * 
	 * @Title: getCertifiedParkingSpaceReport
	 * <p>Description:查询认证车位趋势图
	 * /v1/report/getcertifiedparkingspacereport
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getcertifiedparkingspacereport")
    @ResponseBody
	public OperationResult getCertifiedParkingSpaceReport(
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize,
            @RequestBody ReportCommonModel reportCommonModel,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		PageHelper.startPage(page, pageSize);
		try {
			List<ReportCommonModel> list = reportCommonService.getCertifiedParkingSpaceReport(reportCommonModel);
			res.setFlag(true);
			if(list != null && list.size() > 0) {
				PageInfo<ReportCommonModel> listPage = new PageInfo<ReportCommonModel>(list);
				res.setResData(listPage);
			}else {
				res.setResData(list);
			}
		}catch(ParkspaceServiceException e) {
			LOG.error("查询认证的车位趋势图报表数据失败："+"{"+reportCommonModel+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * 
	 * @Title: getActivingParkingSpaceReport
	 * <p>Description:查询活跃车位趋势图
	 * /v1/report/getactivingparkingspacereport
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getactivingparkingspacereport")
    @ResponseBody
	public OperationResult getActivingParkingSpaceReport(
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize,
            @RequestBody ReportCommonModel reportCommonModel,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		PageHelper.startPage(page, pageSize);
		try {
			List<ReportCommonModel> list = reportCommonService.getActivingParkingSpaceReport(reportCommonModel);
			res.setFlag(true);
			if(list != null && list.size() > 0) {
				PageInfo<ReportCommonModel> listPage = new PageInfo<ReportCommonModel>(list);
				res.setResData(listPage);
			}else {
				res.setResData(list);
			}
		}catch(ParkspaceServiceException e) {
			LOG.error("查询活跃车位趋势图报表数据失败："+"{"+reportCommonModel+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * 
	 * @Title: getCertifiedUserReport
	 * <p>Description:查询认证用户趋势图
	 * /v1/report/getcertifieduserreport
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getcertifieduserreport")
    @ResponseBody
	public OperationResult getCertifiedUserReport(
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize,
            @RequestBody ReportCommonModel reportCommonModel,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		PageHelper.startPage(page, pageSize);
		try {
			List<ReportCommonModel> list = reportCommonService.getCertifiedUserReport(reportCommonModel);
			res.setFlag(true);
			if(list != null && list.size() > 0) {
				PageInfo<ReportCommonModel> listPage = new PageInfo<ReportCommonModel>(list);
				res.setResData(listPage);
			}else {
				res.setResData(list);
			}
		}catch(ParkspaceServiceException e) {
			LOG.error("查询认证用户趋势图报表数据失败："+"{"+reportCommonModel+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * 
	 * @Title: getActivingUserReport
	 * <p>Description:查询活跃用户趋势图
	 * /v1/report/getactivinguserreport
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getactivinguserreport")
    @ResponseBody
	public OperationResult getActivingUserReport(
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize,
            @RequestBody ReportCommonModel reportCommonModel,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		PageHelper.startPage(page, pageSize);
		try {
			List<ReportCommonModel> list = reportCommonService.getActivingUserReport(reportCommonModel);
			res.setFlag(true);
			if(list != null && list.size() > 0) {
				PageInfo<ReportCommonModel> listPage = new PageInfo<ReportCommonModel>(list);
				res.setResData(listPage);
			}else {
				res.setResData(list);
			}
		}catch(ParkspaceServiceException e) {
			LOG.error("查询活跃用户趋势图报表数据失败："+"{"+reportCommonModel+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
}
