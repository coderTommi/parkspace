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
import com.parkspace.db.rmdb.entity.BlackList;
import com.parkspace.db.rmdb.entity.SpaceOwner;
import com.parkspace.service.IBlackListService;
import com.parkspace.service.ISpaceOwnerService;

/**
 * @Title: SpaceOwnerController.java
 * @Package com.parkspace.controller
 * <p>Description:业主action</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月2日 上午11:18:21</p>
*/
@Controller
@RequestMapping("/v1/spaceowner")
public class SpaceOwnerController {
	/**
     * 获取日志接口.
     */
    private static final Log LOG = LogFactory.getLog(ZoneController.class);
	@Resource
	private ISpaceOwnerService spaceOwnerService;
	@Resource
	private IBlackListService blackListService;
	/**
	 * 
	 * @Title: getAllSpaceOwner
	 * <p>Description:查询所有的业主信息
	 * /v1/spaceowner/getallspaceowner
	 * 可以通过状态过滤信息
	 * 状态:0未认证，1认证，默认0，-1表示禁用不在公开车位
	 * private Integer isauth;
	 * 多状态查询：状态查询
	 * private Integer[]  isauthQuery;
	 * 
	 * 通过配置一些属性多条件查询
	 * 按行政区划（String zone.zoneid）、
	 * 小区【支持多选】查询业主（String[] community.comidQuery）
	 * 可按名字(userName)、
	 * 手机号(telePhone)、
	 * 车牌号(spaceno)、
	 * 车位号(carno)
	 * 
	 * 如果像查询某个车主信息可以构造
	 * userId和carno参数
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getallspaceowner")
    @ResponseBody
	public OperationResult getAllSpaceOwner(
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize,
            @RequestBody SpaceOwner spaceOwner,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		PageHelper.startPage(page, pageSize);
		try {
			List<SpaceOwner> list = spaceOwnerService.getSpaceOwnerAllInfoList(spaceOwner);
			res.setFlag(true);
			if(list != null && list.size() > 0) {
				PageInfo<SpaceOwner> listPage = new PageInfo<SpaceOwner>(list);
				res.setResData(listPage);
			}else {
				res.setResData(list);
			}
		}catch(ParkspaceServiceException e) {
			LOG.error("查询业主信息失败："+"{"+spaceOwner+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: addBlackList
	 * <p>Description:加入黑名单
	 * /v1/spaceowner/addblacklist
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/addblacklist")
    @ResponseBody
	public OperationResult addBlackList(
			@RequestBody BlackList blackList,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		try {
			//用户类型为业主
			blackList.setUserType(0);			
			BlackList newBlackList = this.blackListService.addBlackList(blackList);
			res.setResData(newBlackList);
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("添加黑名单信息失败"+"{"+blackList+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: getBlackList
	 * <p>Description:查询黑名单
	 * /v1/spaceowner/getblacklist
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getblacklist")
    @ResponseBody
	public OperationResult getBlackList(
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize,
			@RequestBody BlackList blackList,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		try {
			//用户类型为业主
			blackList.setUserType(0);			
			List<BlackList> list = blackListService.getBlackListAllInfoList(blackList);
			res.setFlag(true);
			if(list != null && list.size() > 0) {
				PageInfo<BlackList> listPage = new PageInfo<BlackList>(list);
				res.setResData(listPage);
			}else {
				res.setResData(list);
			}
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("查询黑名单信息失败"+"{"+blackList+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
}
