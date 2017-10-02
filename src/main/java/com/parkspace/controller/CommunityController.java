package com.parkspace.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
import com.parkspace.db.rmdb.entity.BaseUser;
import com.parkspace.db.rmdb.entity.Community;
import com.parkspace.db.rmdb.entity.PropertyMgmtUser;
import com.parkspace.service.ICommunityService;

/**
 * @Title: CommunityController.java
 * @Package com.parkspace.controller
 * <p>Description:
 * 小区Action
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月1日 下午2:11:24</p>
*/
@Controller
@RequestMapping("/v1/community")
public class CommunityController {
	@Resource
	private ICommunityService communityService;
	 /**
     * 获取日志接口.
     */
    private static final Log LOG = LogFactory.getLog(CommunityController.class);
	/**
	 * @Title: addCommunity
	 * <p>Description:增加小区信息
	 * /v1/community/addcommunity
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/addcommunity")
    @ResponseBody
	public OperationResult addCommunity(
			@RequestBody Community community,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		BaseUser baseUser = (BaseUser)request.getSession().getAttribute("_USER");
		try {
			String userName = "";
			if(baseUser != null) {
				userName = baseUser.getUserName();
				if(StringUtils.isEmpty(userName)) {
					userName = "admin";
				}
			}else {
				userName = "admin";
			}
			//设置编辑人和创建人信息
			community.setCreateBy(userName);
			community.setModifyBy(userName);
			
			Community newCommunity = this.communityService.addCommunity(community);
			res.setResData(newCommunity);
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("添加小区信息失败"+"{"+community+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: updateCommunity
	 * <p>Description:更新小区信息
	 * /v1/community/updatecommunity
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/updatecommunity")
    @ResponseBody
	public OperationResult updateCommunity(
			@RequestBody Community community,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		BaseUser baseUser = (BaseUser)request.getSession().getAttribute("_USER");
		try {
			String userName = "";
			if(baseUser != null) {
				userName = baseUser.getUserName();
				if(StringUtils.isEmpty(userName)) {
					userName = "admin";
				}
			}else {
				userName = "admin";
			}
			//设置编辑人和创建人信息
			community.setModifyBy(userName);
			
			this.communityService.updateCommunity(community);
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("更新小区信息失败"+"{"+community+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: deleteCommunity
	 * <p>Description:删除某个小区
	 * /v1/community/deletecommunity/b6a0a6e7-f522-4c4f-9dc8-33897f8a6da4
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/deletecommunity/{comid}")
    @ResponseBody
	public OperationResult deleteCommunity(
            @PathVariable String comid,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		BaseUser baseUser = (BaseUser)request.getSession().getAttribute("_USER");
		try {
			String userName = "";
			if(baseUser != null) {
				userName = baseUser.getUserName();
				if(StringUtils.isEmpty(userName)) {
					userName = "admin";
				}
			}else {
				userName = "admin";
			}
			//初始化需要删除的信息
			Community community = new Community();
			community.setComid(comid);
			community.setModifyBy(userName);
			community.setIsenable(-1);
			
			this.communityService.deleteCommunity(community);
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("根据小区主键comid"+"{"+comid+"},删除小区信息失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * 
	 * @Title: getAllCommunity
	 * <p>Description:查询所有小区信息
	 * /v1/community/getallcommunity
	 * 可以通过设置小区的状态获取对应的信息
	 * 状态  0：未开放  1：封闭式小区，2：开放式小区,默认0，如果是-1表示禁用
	 * private Integer isenable;
	 * 多条件查询：状态查询条件
	 * private Integer[] isenableQuery;
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getallcommunity")
    @ResponseBody
	public OperationResult getAllCommunity(
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize,
            @RequestBody Community community,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		PageHelper.startPage(page, pageSize);
		try {
			List<Community> list = this.communityService.getCommunityAllInfoList(community);
			res.setFlag(true);
			if(list != null && list.size() > 0) {
				PageInfo<Community> listPage = new PageInfo<Community>(list);
				res.setResData(listPage);
			}else {
				res.setResData(list);
			}
		}catch(ParkspaceServiceException e) {
			LOG.error("查询小区信息失败："+"{"+community+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: getCommunity
	 * <p>Description:获取某个小区的信息
	 * /v1/community/getcommunity/1d93e91d-ae0b-4c5f-b0cc-0e6e711357ff
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getcommunity/{comid}")
    @ResponseBody
	public OperationResult getCommunity(
            @PathVariable String comid,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		try {
			Community community = this.communityService.getCommunity(comid);
			res.setResData(community);
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("根据小区主键comid"+"{"+comid+"},获取小区详细信息失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: getPropertyMgmtUser
	 * <p>Description:获取某个小区的物业信息
	 * /v1/community/getpropertymgmtuser/1d93e91d-ae0b-4c5f-b0cc-0e6e711357ff
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getpropertymgmtuser/{comid}")
    @ResponseBody
	public OperationResult getPropertyMgmtUser(
            @PathVariable String comid,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		try {
			PropertyMgmtUser propertyMgmtUser = communityService.getPropertyMgmtUserByComid(comid);
			res.setResData(propertyMgmtUser);
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("根据小区主键comid"+"{"+comid+"},获取小区物业详细信息失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
}
