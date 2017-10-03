package com.parkspace.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkspace.common.OperationResult;
import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.entity.Caruser;
import com.parkspace.db.rmdb.entity.Community;
import com.parkspace.db.rmdb.entity.ParkingSpace;
import com.parkspace.db.rmdb.entity.PropertyMgmtUser;
import com.parkspace.db.rmdb.entity.SpaceOwner;
import com.parkspace.model.AdminIndexSurvey;
import com.parkspace.service.ICaruserService;
import com.parkspace.service.ICommunityService;
import com.parkspace.service.IParkingSpaceService;
import com.parkspace.service.ISpaceOwnerService;

/**
 * @Title: AdminIndex.java
 * @Package com.parkspace.controller
 * <p>Description:管理端首页action</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月1日 下午5:55:12</p>
*/
@Controller
@RequestMapping("/v1/admin/index")
public class AdminIndexController {
	@Resource
	private ICommunityService communityService;
	@Resource
	private IParkingSpaceService parkingSpaceService;
	@Resource
	private ISpaceOwnerService spaceOwnerService;
	@Resource
	private ICaruserService caruserService;
	
	 /**
     * 获取日志接口.
     */
    private static final Log LOG = LogFactory.getLog(AdminIndexController.class);
	/**
	 * 
	 * @Title: getAdminIndexSurvey
	 * <p>Description:查询管理员首页概况信息
	 * /v1/admin/index/getadminindexsurvey
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getadminindexsurvey")
    @ResponseBody
	public OperationResult getAdminIndexSurvey(HttpServletRequest request) {
		OperationResult res = new OperationResult();
		try {
			AdminIndexSurvey adminIndexSurvey = new AdminIndexSurvey();
			//推广的物业数
			PropertyMgmtUser propertyMgmtUser = new PropertyMgmtUser();
			int propertyMgmtUserCount = this.communityService.getPropertyMgmtUserCount(propertyMgmtUser);
			adminIndexSurvey.setPropertyMgmtUserCount(propertyMgmtUserCount);
			//用户注册数:包括车主和业主
			int userCout = 0;
			SpaceOwner spaceOwner = new SpaceOwner();
			//目前获取所有没排除未开启的用户
			int spaceUser = spaceOwnerService.getSpaceOwnerCount(spaceOwner);
			Caruser caruser = new Caruser();
			int carUser = caruserService.getCaruserCount(caruser);
			userCout = spaceUser + carUser;
			adminIndexSurvey.setUserCout(userCout);
			//管理的车位数，排除未对外开放的车位
			ParkingSpace parkingSpace = new ParkingSpace();
			int spaceCount = parkingSpaceService.getEnableParkingSpaceCount(parkingSpace);
			adminIndexSurvey.setSpaceCount(spaceCount);
			
			res.setResData(adminIndexSurvey);
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("获取首页概况信息失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * 
	 * @Title: getCommunitySurvey
	 * <p>Description:查询小区概况信息
	 * /v1/admin/index/getcommunitysurvey/43143143
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getcommunitysurvey/{comid}")
    @ResponseBody
	public OperationResult getCommunitySurvey(
				@PathVariable String comid,
				HttpServletRequest request
			) {
		OperationResult res = new OperationResult();
		try {
			AdminIndexSurvey adminIndexSurvey = new AdminIndexSurvey();
			
			Community community = new Community();
			community.setComid(comid);
			
			//用户注册数:包括车主和业主
			int userCout = 0;
			SpaceOwner spaceOwner = new SpaceOwner();
			spaceOwner.setCommunity(community);
			//目前获取所有没排除未开启的用户
			int spaceUser = spaceOwnerService.getSpaceOwnerCount(spaceOwner);
			Caruser caruser = new Caruser();
			caruser.setCommunity(community);
			int carUser = caruserService.getCaruserAllInfoCount(caruser);
			userCout = spaceUser + carUser;
			adminIndexSurvey.setUserCout(userCout);
			//管理的车位数，排除未对外开放的车位
			ParkingSpace parkingSpace = new ParkingSpace();
			parkingSpace.setComid(comid);
			int spaceCount = parkingSpaceService.getEnableParkingSpaceCount(parkingSpace);
			adminIndexSurvey.setSpaceCount(spaceCount);
			
			res.setResData(adminIndexSurvey);
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("获取首页概况信息失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
}
