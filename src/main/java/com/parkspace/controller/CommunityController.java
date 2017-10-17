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
	 * post:http://localhost:8080/parkspace/v1/community/addcommunity
	 * 入参：{"zoneid":"4e73503c-7052-41bc-a716-b8b2d2e32e5e","comname":"金牛小区","address":"师范路金牛小区","memo":"测试添加小区1","createBy":"孙辽东创建1","modifyBy":"孙辽东编辑1","price":2,"maxPriceAllDay":16,"freeParkingMinutes":60}
	 * 出参：{"flag":true,"errCode":null,"resData":{"comid":"647f452a-9569-44ea-a416-af6a557e43f8","comidQuery":null,"zoneid":"4e73503c-7052-41bc-a716-b8b2d2e32e5e","comname":"金牛小区","address":"师范路金牛小区","isenable":1,"isenableQuery":null,"memo":"测试添加小区1","createBy":"admin","createTime":1508234380834,"modifyBy":"admin","modifyTime":1508234380834,"price":2,"maxPriceAllDay":16,"freeParkingMinutes":60,"zone":null}}
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
	 * post:http://localhost:8080/parkspace/v1/community/updatecommunity
	 * 入参：{"comid":"647f452a-9569-44ea-a416-af6a557e43f8","zoneid":"4e73503c-7052-41bc-a716-b8b2d2e32e5e","comname":"金牛小区","address":"师范路金牛小区","isenable":-1,"isenableQuery":null,"memo":"测试更新小区信息","createBy":"admin","modifyBy":"admin","price":2.00,"maxPriceAllDay":18,"freeParkingMinutes":30}
	 * 出参：{"flag":true,"errCode":null,"resData":null}
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
	 * post:http://localhost:8080/parkspace/v1/community/deletecommunity/b6a0a6e7-f522-4c4f-9dc8-33897f8a6da4
	 * 入参：在url中输入comid
	 * 出参：{"flag":true,"errCode":null,"resData":null}
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
	 * get:http://localhost:8080/parkspace/v1/community/getallcommunity?page=1&pageSize=1
	 * 入参（查询济南市）：{"zone":{"city":"济南"}}
	 * 入参（查询行政区域下的小区）：{"zone":{"zoneid":"4e73503c-7052-41bc-a716-b8b2d2e32e5e"}}
	 * 入参（小区名字）：{"comname":"王府"}
	 * 出参：{"flag":true,"errCode":null,"resData":{"pageNum":1,"pageSize":1,"size":1,"startRow":1,"endRow":1,"total":4,"pages":4,"list":[{"comid":"16bafa86-7ff2-478a-8e67-c0fb04a11312","comidQuery":null,"zoneid":"4e73503c-7052-41bc-a716-b8b2d2e32e5e","comname":"金牛小区","address":"师范路金牛小区","isenable":1,"isenableQuery":null,"memo":"测试添加小区1","createBy":"admin","createTime":1507601799000,"modifyBy":"admin","modifyTime":1507602067000,"price":2.00,"maxPriceAllDay":18.00,"freeParkingMinutes":60,"zone":{"zoneid":"4e73503c-7052-41bc-a716-b8b2d2e32e5e","zonename":"天桥行政区","isenable":1,"isenableQuery":null,"province":"山东省","city":"济南","zone":"天桥区","memo":"测试区域添加00010001","createBy":"孙辽东","createTime":1506244295000,"modifyBy":"孙辽东02","modifyTime":1506263494000}}],"prePage":0,"nextPage":2,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2,3,4],"navigateFirstPage":1,"navigateLastPage":4,"lastPage":4,"firstPage":1}}
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
	 * get:http://localhost:8080/parkspace/v1/community/getcommunity/1d93e91d-ae0b-4c5f-b0cc-0e6e711357ff
	 * 入参：只需在url中增加comid
	 * 出参：{"flag":true,"errCode":null,"resData":{"comid":"647f452a-9569-44ea-a416-af6a557e43f8","comidQuery":null,"zoneid":"4e73503c-7052-41bc-a716-b8b2d2e32e5e","comname":"金牛小区","address":"师范路金牛小区","isenable":-1,"isenableQuery":null,"memo":"测试更新小区信息","createBy":"admin","createTime":1508234381000,"modifyBy":"admin","modifyTime":1508234684000,"price":2.00,"maxPriceAllDay":18.00,"freeParkingMinutes":30,"zone":null}}
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
	 * get:http://localhost:8080/parkspace/v1/community/getpropertymgmtuser/1d93e91d-ae0b-4c5f-b0cc-0e6e711357ff
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
