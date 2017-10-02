package com.parkspace.controller;

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
import com.parkspace.model.ParkingSpaceSurvey;
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
	
}
