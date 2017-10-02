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
import com.parkspace.common.exception.PackspaceServiceException;
import com.parkspace.db.rmdb.entity.BaseUser;
import com.parkspace.db.rmdb.entity.ShareConfig;
import com.parkspace.service.IShareConfigService;

/**
 * @Title: ShareConfigController.java
 * @Package com.parkspace.controller
 * <p>Description:共享时间段设置Controller</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月1日 上午9:23:54</p>
*/
@Controller
@RequestMapping("/v1/shareconfig")
public class ShareConfigController {
	@Resource
	private IShareConfigService shareConfigService;
	 /**
     * 获取日志接口.
     */
    private static final Log LOG = LogFactory.getLog(ShareConfigController.class);
	/**
	 * 
	 * @Title: getAllShareConfig
	 * <p>Description:显示某个车位的所有未删除的共享信息
	 * /v1/shareconfig/getallshareconfig/1
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getallshareconfig/{spaceno}")
    @ResponseBody
	public OperationResult getAllShareConfig(
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize,
            @PathVariable String spaceno,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		PageHelper.startPage(page, pageSize);
		try {
			List<ShareConfig> list = shareConfigService.getShareConfigListBySpaceno(spaceno);
			res.setFlag(true);
			if(list != null && list.size() > 0) {
				PageInfo<ShareConfig> listPage = new PageInfo<ShareConfig>(list);
				res.setResData(listPage);
			}else {
				res.setResData(list);
			}
		}catch(PackspaceServiceException e) {
			LOG.error("根据车位编号"+"{"+spaceno+"},查询车位共享信息失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: getShareConfig
	 * <p>Description:获取某个共享时间段的信息
	 * /v1/shareconfig/getshareconfig/1d93e91d-ae0b-4c5f-b0cc-0e6e711357ff
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getshareconfig/{UUID}")
    @ResponseBody
	public OperationResult getShareConfig(
            @PathVariable String UUID,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		try {
			ShareConfig shareConfig = this.shareConfigService.getShareConfig(UUID);
			res.setResData(shareConfig);
			res.setFlag(true);
		}catch(PackspaceServiceException e) {
			LOG.error("根据共享时间段UUID"+"{"+UUID+"},获取共享详细信息失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: disableShareConfig
	 * <p>Description:关闭某个共享时间段
	 * /v1/shareconfig/disableshareconfig/1d93e91d-ae0b-4c5f-b0cc-0e6e711357ff
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/disableshareconfig/{UUID}")
    @ResponseBody
	public OperationResult disableShareConfig(
            @PathVariable String UUID,
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
			shareConfigService.disableShareConfig(UUID, userName);
			res.setFlag(true);
		}catch(PackspaceServiceException e) {
			LOG.error("根据共享时间段UUID"+"{"+UUID+"},关闭共享信息失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: enableShareConfig
	 * <p>Description:开启某个共享时间段
	 * /v1/shareconfig/enableshareconfig/1d93e91d-ae0b-4c5f-b0cc-0e6e711357ff
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/enableshareconfig/{UUID}")
    @ResponseBody
	public OperationResult enableShareConfig(
            @PathVariable String UUID,
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
			shareConfigService.enableShareConfig(UUID, userName);
			res.setFlag(true);
		}catch(PackspaceServiceException e) {
			LOG.error("根据共享时间段UUID"+"{"+UUID+"},开启共享信息失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: deleteShareConfig
	 * <p>Description:删除某个共享时间段
	 * /v1/shareconfig/deleteshareconfig/1d93e91d-ae0b-4c5f-b0cc-0e6e711357ff
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/deleteshareconfig/{UUID}")
    @ResponseBody
	public OperationResult deleteShareConfig(
            @PathVariable String UUID,
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
			ShareConfig shareConfig = new ShareConfig();
			shareConfig.setUUID(UUID);
			shareConfig.setModifyBy(userName);
			shareConfig.setIsOpen(-1);
			
			shareConfigService.deleteShareConfig(shareConfig);
			res.setFlag(true);
		}catch(PackspaceServiceException e) {
			LOG.error("根据共享时间段UUID"+"{"+UUID+"},删除共享信息失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: addShareConfig
	 * <p>Description:新建共享时间段
	 * /v1/shareconfig/addshareconfig
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/addshareconfig")
    @ResponseBody
	public OperationResult addShareConfig(
			@RequestBody ShareConfig shareConfig,
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
			shareConfig.setCreateBy(userName);
			shareConfig.setModifyBy(userName);
			
			ShareConfig newShareConfig = shareConfigService.addShareConfig(shareConfig);
			res.setResData(newShareConfig);
			res.setFlag(true);
		}catch(PackspaceServiceException e) {
			LOG.error("添加共享信息失败"+"{"+shareConfig+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: updateShareConfig
	 * <p>Description:更新共享时间段
	 * /v1/shareconfig/updateshareconfig
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/updateshareconfig")
    @ResponseBody
	public OperationResult updateShareConfig(
			@RequestBody ShareConfig shareConfig,
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
			shareConfig.setModifyBy(userName);
			
			shareConfigService.updateShareConfig(shareConfig);
			res.setFlag(true);
		}catch(PackspaceServiceException e) {
			LOG.error("更新共享信息失败"+"{"+shareConfig+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
}
