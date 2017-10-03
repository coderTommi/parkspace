package com.parkspace.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.parkspace.common.OperationResult;
import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.entity.ParkingSpace;
import com.parkspace.db.rmdb.entity.ParkingSpaceBill;
import com.parkspace.model.ParkingSpaceSurvey;
import com.parkspace.service.IParkingSpaceBillService;
import com.parkspace.service.IParkingSpaceService;

/**
 * @Title: ParkingSpaceController.java
 * @Package com.parkspace.controller
 * <p>Description:车位信息action</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月2日 下午5:05:15</p>
*/
@Controller
@RequestMapping("/v1/parkingspace")
public class ParkingSpaceController {
	/**
     * 获取日志接口.
     */
    private static final Log LOG = LogFactory.getLog(ParkingSpaceController.class);
	@Resource
	private IParkingSpaceService parkingSpaceService;
	@Resource
	private IParkingSpaceBillService parkingSpaceBillService;
	
	/**
	 * 
	 * @Title: getAllParkingSpace
	 * <p>Description:查询所有车位信息
	 * 对于物业段只需要赋值comid属性即可
	 * /v1/parkingspace/getallparkingspace
	 * 可以通过状态进行过滤：注意对于空闲的车位需要判断是否有可用共享时间段
	 * 车位状态，1占用，0空闲，-1不对外开放
	 * private String parkStatus;
	 * 多状态查询
	 * private String[] parkStatusQuery;
	 * 通过配置一些属性多条件查询
	 * 按行政区划（String zone.zoneid）、
	 * 小区【支持多选】查询业主（String[] community.comidQuery）
	 * 车牌号(spaceno),如果使用模糊查询需要使用字段spacenoLikeQuery
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getallparkingspace")
    @ResponseBody
	public OperationResult getAllParkingSpace(
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize,
            @RequestBody ParkingSpace parkingSpace,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		PageHelper.startPage(page, pageSize);
		try {
			List<ParkingSpace> list = parkingSpaceService.getParkingSpaceListIncludeComAndZone(parkingSpace);
			res.setFlag(true);
			if(list != null && list.size() > 0) {
				PageInfo<ParkingSpace> listPage = new PageInfo<ParkingSpace>(list);
				res.setResData(listPage);
			}else {
				res.setResData(list);
			}
		}catch(ParkspaceServiceException e) {
			LOG.error("查询车位信息失败："+"{"+parkingSpace+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * 
	 * @Title: getParkingSpaceSurvey
	 * <p>Description:查询车位概要信息
	 * 对于物业段只需要赋值comid属性即可
	 * /v1/parkingspace/getparkingspacesurvey
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getparkingspacesurvey")
    @ResponseBody
	public OperationResult getParkingSpaceSurvey(
            @RequestBody ParkingSpace parkingSpace,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		try {
			ParkingSpaceSurvey parkingSpaceSurvey = new ParkingSpaceSurvey();
			int enableSpacenoCount = parkingSpaceService.getEnableParkingSpaceCount(parkingSpace);
			parkingSpaceSurvey.setEnableSpacenoCount(enableSpacenoCount);
			int noUseingSpacenoCount = parkingSpaceService.getNoUseingParkingSpaceCount(parkingSpace);
			parkingSpaceSurvey.setNoUseingSpacenoCount(noUseingSpacenoCount);
			res.setFlag(true);
			res.setResData(parkingSpaceSurvey);
		}catch(ParkspaceServiceException e) {
			LOG.error("查询车位概要信息失败："+"{"+parkingSpace+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: getParkingSpace
	 * <p>Description:获取某个车位信息
	 * /v1/parkingspace/getparkingspace/9f7abc0f-197d-4d43-bd09-92b98fd45105
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getparkingspace/{spaceno}")
    @ResponseBody
	public OperationResult getParkingSpace(
            @PathVariable String spaceno,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		try {
			ParkingSpace parkingSpace = parkingSpaceService.getParkingSpace(spaceno);
			res.setResData(parkingSpace);
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("根据车位编号spaceno"+"{"+spaceno+"},获取车位详细信息失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: getEnableParkingSpace
	 * <p>Description:查询可预订的车位信息
	 * /v1/parkingspace/getenableparkingspace
	 * </p>
	 * @param     comid     小区编号
	 * @param     parkHours 停车时长
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getenableparkingspace")
    @ResponseBody
	public OperationResult getEnableParkingSpace(
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize,
            @RequestParam(value = "comid", required = true) String comid,
            @RequestParam(value = "parkHours", required = true) int parkHours,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		PageHelper.startPage(page, pageSize);
		try {
			List<ParkingSpace> list = parkingSpaceService.getParkingSpaceListByComidAndParkHours(comid, parkHours);
			res.setFlag(true);
			if(list != null && list.size() > 0) {
				PageInfo<ParkingSpace> listPage = new PageInfo<ParkingSpace>(list);
				res.setResData(listPage);
			}else {
				res.setResData(list);
			}
		}catch(ParkspaceServiceException e) {
			LOG.error("通过小区编号"+"{"+comid+"}，停车时长"+ "{"+parkHours+"}查询可预约车位失败"
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: orderParkingSpace
	 * <p>Description:预约车位
	 * /v1/parkingspace/orderparkingspace
	 * </p>
	 * @param     spaceno   车位编号
	 * @param     parkHours 停车时长
	 * @param     userId 用户编号
	 * @param     carno 车牌号
	 * @param     unitPrice 车位单价
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/orderparkingspace")
    @ResponseBody
	public OperationResult orderParkingSpace(
            @RequestParam(value = "spaceno", required = true) String spaceno,
            @RequestParam(value = "parkHours", required = true) int parkHours,
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "carno", required = true) String carno,
            @RequestParam(value = "unitPrice", required = true) BigDecimal unitPrice,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		try {
			ParkingSpaceBill parkingSpaceBill = new ParkingSpaceBill();
			parkingSpaceBill.setSpaceno(spaceno);
			parkingSpaceBill.setParkHours(parkHours);
			parkingSpaceBill.setUnitPrice(unitPrice);
			parkingSpaceBill.setCarno(carno);
			parkingSpaceBill.setUserId(userId);
			
			ParkingSpaceBill newParkingSpaceBill = parkingSpaceService.addOrderParkingSpace(parkingSpaceBill);
			res.setResData(newParkingSpaceBill);
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("预约车位："+"{"+spaceno+"}，失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: getParkingSpaceParkHoursBySpaceno
	 * <p>Description:获取车位的最长预定时间
	 * 如果是A表示没有限制
	 * /v1/parkingspace/getparkingspaceparkhoursbyspaceno
	 * </p>
	 * @param     spaceno   车位编号
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getparkingspaceparkhoursbyspaceno")
    @ResponseBody
	public OperationResult getParkingSpaceParkHoursBySpaceno(
            @RequestParam(value = "spaceno", required = true) String spaceno,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		try {
			String parkHoursString = parkingSpaceService.getParkingSpaceParkHoursBySpaceno(spaceno);
			res.setResData(parkHoursString);
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("查询车位："+"{"+spaceno+"}，最长预约时间失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: cancelOrderParkingSpace
	 * <p>Description:取消当前订单
	 * /v1/parkingspace/cancelorderparkingspace
	 * </p>
	 * @param     orderJnlNo   订单编号
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/cancelorderparkingspace")
    @ResponseBody
	public OperationResult cancelOrderParkingSpace(
            @RequestParam(value = "orderJnlNo", required = true) String orderJnlNo,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		try {
			parkingSpaceService.cancelOrderParkingSpace(orderJnlNo);
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("取消订单："+"{"+orderJnlNo+"}，失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: confirmOrderParkingSpace
	 * <p>Description:确认停车
	 * /v1/parkingspace/confirmorderparkingspace
	 * </p>
	 * @param     orderJnlNo   订单编号
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/confirmorderparkingspace")
    @ResponseBody
	public OperationResult confirmOrderParkingSpace(
            @RequestParam(value = "orderJnlNo", required = true) String orderJnlNo,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		try {
			parkingSpaceService.confirmOrderParkingSpace(orderJnlNo);
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("确认停车："+"{"+orderJnlNo+"}，失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: delayOrderParkingSpace
	 * <p>Description:延长停车
	 * 注意：只有当前状态为使用中的才能执行该操作，
	 * 并且需要判断该车位是否允许延长停车
	 * /v1/parkingspace/delayorderparkingspace
	 * </p>
	 * @param     orderJnlNo     订单编号
	 * @param     delayParkHours 延长停车时长
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/delayorderparkingspace")
    @ResponseBody
	public OperationResult delayOrderParkingSpace(
            @RequestParam(value = "orderJnlNo", required = true) String orderJnlNo,
            @RequestParam(value = "delayParkHours", required = true) int delayParkHours,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		try {
			parkingSpaceService.delayOrderParkingSpace(orderJnlNo,delayParkHours);
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("延长停车："+"{"+delayParkHours+"}，小时失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: getParkingSpaceBill
	 * <p>Description:查询订单信息
	 * /v1/parkingspace/getparkingspacebill
	 * </p>
	 * @param     spaceno        车位编号
	 * @param     delayParkHours 延长停车时长
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getparkingspacebill")
    @ResponseBody
	public OperationResult getParkingSpaceBill(
            @RequestParam(value = "orderJnlNo", required = true) String orderJnlNo,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		try {
			ParkingSpaceBill parkingSpaceBill = parkingSpaceBillService.getParkingSpaceBill(orderJnlNo);
			res.setFlag(true);
			res.setResData(parkingSpaceBill);
		}catch(ParkspaceServiceException e) {
			LOG.error("根据订单编号："+"{"+orderJnlNo+"}，查询订单失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
}
