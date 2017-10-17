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
import com.parkspace.db.rmdb.entity.Zone;
import com.parkspace.service.IZoneService;

/**
 * @Title: ZoneController.java
 * @Package com.parkspace.controller
 * <p>Description:
 * 行政区域action处理
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月1日 下午2:11:51</p>
*/
@Controller
@RequestMapping("/v1/zone")
public class ZoneController {
	/**
     * 获取日志接口.
     */
    private static final Log LOG = LogFactory.getLog(ZoneController.class);
	@Resource
	private IZoneService zoneService;
	/**
	 * @Title: addZone
	 * <p>Description:新建行政区域
	 * post:http://localhost:8080/parkspace/v1/zone/addzone
	 * 入参：{"zonename":"天桥行政区1","province":"山东省1","city":"济南1","zone":"天桥区1","memo":"测试区域添加1","createBy":null,"modifyBy":null}
	 * 出参：{"flag":true,"errCode":null,"resData":{"zoneid":"4d2d629e-45f1-49fb-bcb2-6cf62078c9ff","zonename":"天桥行政区1","isenable":1,"isenableQuery":null,"province":"山东省1","city":"济南1","zone":"天桥区1","memo":"测试区域添加1","createBy":"admin","createTime":1508231174243,"modifyBy":"admin","modifyTime":1508231174243}}
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/addzone")
    @ResponseBody
	public OperationResult addZone(
			@RequestBody Zone zone,
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
			zone.setCreateBy(userName);
			zone.setModifyBy(userName);
			
			Zone newZone = zoneService.addZone(zone);
			res.setResData(newZone);
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("添加行政区域信息失败"+"{"+zone+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: updateZone
	 * <p>Description:更新行政区域信息
	 * post:http://localhost:8080/parkspace/v1/zone/updatezone
	 * 入参：入参{"zoneid":"59f0d481-e78e-4e50-a918-78345194f0df","zonename":"天桥行政区1","province":"山东省1","city":"济南1","zone":"天桥区1","memo":"测试更改端到端","modifyBy":"admin"}
	 * 出参：{"flag":true,"errCode":null,"resData":null}
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/updatezone")
    @ResponseBody
	public OperationResult updateZone(
			@RequestBody Zone zone,
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
			zone.setModifyBy(userName);
			
			this.zoneService.updateZone(zone);
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("更新行政区域信息失败"+"{"+zone+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: deleteZone
	 * <p>Description:删除某个行政区域
	 * post:http://localhost:8080/parkspace/v1/zone/deletezone/9f7abc0f-197d-4d43-bd09-92b98fd45105
	 * 入参：url中使用zoneid
	 * 出参：{"flag":true,"errCode":null,"resData":null}
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/deletezone/{zoneid}")
    @ResponseBody
	public OperationResult deleteZone(
            @PathVariable String zoneid,
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
			Zone zone = new Zone();
			zone.setZoneid(zoneid);
			zone.setModifyBy(userName);
			//删除
			zone.setIsenable(-1);
			this.zoneService.deleteZone(zone);
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("根据行政区域主键zoneid"+"{"+zoneid+"},删除行政区域信息失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * 
	 * @Title: getAllZone
	 * <p>Description:查询所有行政区域信息
	 * get:http://localhost:8080/parkspace/v1/zone/getallzone?page=1&pageSize=1
	 * 入参：入参：{"zoneid":null,"zonename":null,"isenable":null,"isenableQuery":null,"province":"山东省1","city":"济南1","zone":null,"memo":"测试区域添加1","createBy":null,"createTime":null,"modifyBy":null,"modifyTime":null}
	 * 出参：{"flag":true,"errCode":null,"resData":{"pageNum":1,"pageSize":1,"size":1,"startRow":1,"endRow":1,"total":3,"pages":3,"list":[{"zoneid":"59f0d481-e78e-4e50-a918-78345194f0df","zonename":"天桥行政区1","isenable":-1,"isenableQuery":null,"province":"山东省1","city":"济南1","zone":"天桥区1","memo":"测试更改端到端","createBy":"admin","createTime":1508231288000,"modifyBy":"admin","modifyTime":1508231925000}],"prePage":0,"nextPage":2,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2,3],"navigateFirstPage":1,"navigateLastPage":3,"firstPage":1,"lastPage":3}}
	 * 可以通过行政区域的状态获取相关的信息
	 * 状态  0：未开放  1：已开放，-1,表示删除，默认0
	 * private Integer isenable;
	 * 多条件查询：状态查询
	 * private Integer[] isenableQuery;
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getallzone")
    @ResponseBody
	public OperationResult getAllZone(
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize,
            @RequestBody Zone zone,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		PageHelper.startPage(page, pageSize);
		try {
			List<Zone> list = this.zoneService.getZoneList(zone);
			res.setFlag(true);
			if(list != null && list.size() > 0) {
				PageInfo<Zone> listPage = new PageInfo<Zone>(list);
				res.setResData(listPage);
			}else {
				res.setResData(list);
			}
		}catch(ParkspaceServiceException e) {
			LOG.error("查询行政区域信息失败："+"{"+zone+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: getZone
	 * <p>Description:获取某个行政区域的信息
	 * get:http://localhost:8080/parkspace/v1/zone/getzone/9f7abc0f-197d-4d43-bd09-92b98fd45105
	 * 入参：在url中使用zoneid
	 * 出参：{"flag":true,"errCode":null,"resData":{"zoneid":"59f0d481-e78e-4e50-a918-78345194f0df","zonename":"天桥行政区1","isenable":-1,"isenableQuery":null,"province":"山东省1","city":"济南1","zone":"天桥区1","memo":"测试区域添加1","createBy":"admin","createTime":1508231288000,"modifyBy":"admin","modifyTime":1508231635000}}
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getzone/{zoneid}")
    @ResponseBody
	public OperationResult getZone(
            @PathVariable String zoneid,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		try {
			Zone zone = this.zoneService.getZone(zoneid);
			res.setResData(zone);
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("根据行政区域主键zoneid"+"{"+zoneid+"},获取行政区域详细信息失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
}
