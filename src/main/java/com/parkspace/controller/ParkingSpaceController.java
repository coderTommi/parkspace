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
import com.parkspace.db.rmdb.entity.ParkingSpaceBillHis;
import com.parkspace.model.ParkingSpaceSurvey;
import com.parkspace.service.IParkingSpaceBillHisService;
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
	@Resource
	private IParkingSpaceBillHisService parkingSpaceBillHisService;
	
	/**
	 * 
	 * @Title: getAllParkingSpace
	 * <p>Description:查询所有车位信息
	 * 对于物业段只需要赋值comid属性即可
	 * get:http://localhost:8080/parkspace/v1/parkingspace/getallparkingspace?page=1&pageSize=1
	 * 入参（查询所有）：空
	 * 入参（按行政区划、小区【支持多选】查询）：{"community":{"comidQuery":["d2ac2ef6-acad-411a-9b2a-9732d47028b5"]},"zone":{"zoneid":"4e73503c-7052-41bc-a716-b8b2d2e32e5e"}}
	 * 入参（可以根据车位号模糊查询）：{"spacenoLikeQuery":"1"}
	 * 出参：{"flag":true,"errCode":null,"resData":{"pageNum":1,"pageSize":1,"size":1,"startRow":1,"endRow":1,"total":1,"pages":1,"list":[{"spaceno":"1","spacenoLikeQuery":null,"comid":"d2ac2ef6-acad-411a-9b2a-9732d47028b5","parkPositionFloor":"-1F","parkPositionZone":"A区","parkPositionX":"100X","parkPositionY":"200Y","parkStatus":"0","parkStatusQuery":null,"parkType":"P","parkTypeQuery":null,"parkPositionDes":"3号楼附近","spaceOwner":"13518678898","memo":"测试这位添加测试修改信息","createBy":"孙辽东create","createTime":1506413381000,"modifyBy":"modifyBy","modifyTime":1506413856000,"community":{"comid":"d2ac2ef6-acad-411a-9b2a-9732d47028b5","comidQuery":null,"zoneid":"4e73503c-7052-41bc-a716-b8b2d2e32e5e","comname":"王府庄园","address":"济洛路王府庄园更新地址更新地址","isenable":1,"isenableQuery":null,"memo":"测试添加小区","createBy":"孙辽东创建","createTime":1506244564000,"modifyBy":"孙辽东编辑","modifyTime":1506264204000,"price":null,"maxPriceAllDay":16.00,"freeParkingMinutes":60,"zone":null},"zone":{"zoneid":"4e73503c-7052-41bc-a716-b8b2d2e32e5e","zonename":"天桥行政区","isenable":1,"isenableQuery":null,"province":"山东省","city":"济南","zone":"天桥区","memo":"测试区域添加00010001","createBy":"孙辽东","createTime":1506244295000,"modifyBy":"孙辽东02","modifyTime":1506263494000},"spaceOwnerUser":{"userId":"1","userName":"test01","nickName":"test01_nickName","userPwd":null,"telePhone":"15300201276","isAdmin":0,"idType":0,"idNo":"370714198211240087","state":2,"weixinAccount":"123456","avator":"admin","memo":"test user","createBy":"admin","createTime":1506246726000,"modifyBy":"admin","modifyTime":1506246726000,"realName":"孙辽东real","spaceno":"1","isauth":0,"isauthQuery":null,"carno":"abc","parkingSpace":null,"certifiedTime":1507471148000,"community":null,"zone":null},"parkHours":null,"parkHoursString":null,"shareConfigList":null,"useCount":2}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}}
	 * 可以通过状态进行过滤：注意对于空闲的车位需要判断是否有可用共享时间段
	 * 车位状态，1占用，0空闲，N不对外开放
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
	 * get:http://localhost:8080/parkspace/v1/parkingspace/getparkingspacesurvey
	 * 入参：空
	 * 出参：{"flag":true,"errCode":null,"resData":{"enableSpacenoCount":1,"noUseingSpacenoCount":1}}
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
	 * get:http://localhost:8080/parkspace/v1/parkingspace/getparkingspace/9f7abc0f-197d-4d43-bd09-92b98fd45105
	 * 入参：在url中输入spaceno
	 * 出参：{"flag":true,"errCode":null,"resData":{"spaceno":"1","spacenoLikeQuery":null,"comid":"d2ac2ef6-acad-411a-9b2a-9732d47028b5","parkPositionFloor":"-1F","parkPositionZone":"A区","parkPositionX":"100X","parkPositionY":"200Y","parkStatus":"0","parkStatusQuery":null,"parkType":"P","parkTypeQuery":null,"parkPositionDes":"3号楼附近","spaceOwner":"13518678898","memo":"测试这位添加测试修改信息","createBy":"孙辽东create","createTime":1506413381000,"modifyBy":"modifyBy","modifyTime":1506413856000,"community":null,"zone":null,"spaceOwnerUser":null,"parkHours":null,"parkHoursString":null,"shareConfigList":[{"spaceno":"1","isOpen":1,"isOpenQuery":null,"shareType":1,"isAllDay":0,"startDate":null,"startTime":"13:03:59","endDate":null,"endTime":"18:10:59","internalDate":"1,2,3,7,1","createBy":"admin","createTime":1506832151000,"modifyBy":"admin","modifyTime":1506832151000,"queryDate":null,"queryTime":null,"nowWeek":null,"nowNextWeek":null,"parkHourString":null,"uuid":"7201d911-cf41-40e7-bb77-ea33ca24f133"},{"spaceno":"1","isOpen":1,"isOpenQuery":null,"shareType":1,"isAllDay":0,"startDate":null,"startTime":"13:03:59","endDate":null,"endTime":"18:10:59","internalDate":"1,2,3,6,7,1","createBy":"孙创建","createTime":1506569946000,"modifyBy":"孙编辑","modifyTime":1506569946000,"queryDate":null,"queryTime":null,"nowWeek":null,"nowNextWeek":null,"parkHourString":null,"uuid":"be18ffd5-eea6-45f5-b647-dfe503fa8e6a"},{"spaceno":"1","isOpen":1,"isOpenQuery":null,"shareType":1,"isAllDay":0,"startDate":null,"startTime":"00:03:59","endDate":null,"endTime":"23:59:59","internalDate":"1,2,3,4,5,6,7,1","createBy":"孙创建","createTime":1506569946000,"modifyBy":"孙编辑","modifyTime":1506569946000,"queryDate":null,"queryTime":null,"nowWeek":null,"nowNextWeek":null,"parkHourString":null,"uuid":"be18ffd5-eea6-45f5-b647-dfe503fa8e6b"}],"useCount":2}}
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
	 * get:http://localhost:8080/parkspace/v1/parkingspace/getenableparkingspace?page=1&pageSize=1&comid=1&parkHours=2
	 * 入参：参考url
	 * 出参：{"flag":true,"errCode":null,"resData":{"pageNum":1,"pageSize":10,"size":1,"startRow":1,"endRow":1,"total":1,"pages":1,"list":[{"spaceno":"1","spacenoLikeQuery":null,"comid":"d2ac2ef6-acad-411a-9b2a-9732d47028b5","parkPositionFloor":"-1F","parkPositionZone":"A区","parkPositionX":"100X","parkPositionY":"200Y","parkStatus":"0","parkStatusQuery":null,"parkType":"P","parkTypeQuery":null,"parkPositionDes":"3号楼附近","spaceOwner":"13518678898","memo":"测试这位添加测试修改信息","createBy":"孙辽东create","createTime":1506413381000,"modifyBy":"modifyBy","modifyTime":1506413856000,"community":null,"zone":null,"spaceOwnerUser":null,"parkHours":null,"parkHoursString":"04:14:52","shareConfigList":null,"useCount":2}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"lastPage":1,"firstPage":1}}
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
	 * post:http://localhost:8080/parkspace/v1/parkingspace/orderparkingspace?spaceno=1&parkHours=1&userId=1&carno=1&unitPrice=2
	 * 入参：参考url
	 * 出参：{"flag":true,"errCode":null,"resData":{"orderJnlNo":"21a35916-2781-447d-b1c1-543b40c006f5","userId":"1","spaceOwnerUserId":"1","carno":"aaa","spaceno":"1","billStatus":1,"billStatusQuery":null,"parkHours":3,"delayParkHours":0,"delayParkHoursString":null,"unitPrice":2,"budgetPrice":6,"createTime":1508239236978,"lastPayTime":null,"payedMoney":0,"usedParkHoursString":null,"maxParkHoursString":null,"maxDelayParkHoursString":null,"actualParkHours":null,"actualPrice":null,"isQuerySoonExpire":0,"maxPriceAllDay":null,"freeParkingMinutes":null,"freePrice":null,"actualPayPrice":null,"isGrantSuccess":null,"comid":"d2ac2ef6-acad-411a-9b2a-9732d47028b5","tryGrantCount":null,"caruser":null}}
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
			
			LOG.info("！！！！！调用底层保持订单信息开始--事务开始！！！！！");
			
			ParkingSpaceBill newParkingSpaceBill = parkingSpaceService.addOrderParkingSpace(parkingSpaceBill);
			
			LOG.info("！！！！！调用底层保持订单信息完成--事务结束！！！！！");
			
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
	 * get:http://localhost:8080/parkspace/v1/parkingspace/getparkingspaceparkhoursbyspaceno
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
	 * get:http://localhost:8080/parkspace/v1/parkingspace/cancelorderparkingspace
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
	 * get:http://localhost:8080/parkspace/v1/parkingspace/confirmorderparkingspace?orderJnlNo=1
	 * 入参：参考url
	 * 出参：{"flag":true,"errCode":null,"resData":null}
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
	 * get:http://localhost:8080/parkspace/v1/parkingspace/delayorderparkingspace?orderJnlNo=1&delayParkHours=2
	 * 入参：参考url
	 * 出参：{"flag":true,"errCode":null,"resData":null}
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
	 * get:http://localhost:8080/parkspace/v1/parkingspace/getparkingspacebill?orderJnlNo=1
	 * 入参：参考url
	 * 出参：{"flag":true,"errCode":null,"resData":{"orderJnlNo":"21a35916-2781-447d-b1c1-543b40c006f5","userId":"1","spaceOwnerUserId":"1","carno":"aaa","spaceno":"1","billStatus":1,"billStatusQuery":null,"parkHours":3,"delayParkHours":0,"delayParkHoursString":null,"unitPrice":2.00,"budgetPrice":6.00,"createTime":1508239237000,"lastPayTime":null,"payedMoney":0.00,"usedParkHoursString":"00:02:32","maxParkHoursString":"04:06:50","maxDelayParkHoursString":"04:04:18","actualParkHours":0.04,"actualPrice":2.0,"isQuerySoonExpire":0,"maxPriceAllDay":16.00,"freeParkingMinutes":60,"freePrice":2.00,"actualPayPrice":0.0,"isGrantSuccess":null,"comid":null,"tryGrantCount":null,"caruser":null}}
	 * </p>
	 * @param     orderJnlNo        订单编号
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
	
	/**
	 * @Title: payOrderParkingSpace
	 * <p>Description:结算
	 * /v1/parkingspace/payorderparkingspace
	 * </p>
	 * @param     orderJnlNo        订单编号
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/payorderparkingspace")
    @ResponseBody
	public OperationResult payOrderParkingSpace(
            @RequestParam(value = "orderJnlNo", required = true) String orderJnlNo,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		try {
			parkingSpaceService.payOrderParkingSpace(orderJnlNo);
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("结算订单："+"{"+orderJnlNo+"}，失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * 
	 * @Title: getParkingSpaceUsedHistory
	 * <p>Description:查询车位的使用记录
	 * get:http://localhost:8080/parkspace/v1/parkingspace/getparkingspaceusedhistory?page=1&pageSize=1&spaceno
	 * 入参：参考url
	 * 出参：{"flag":true,"errCode":null,"resData":{"pageNum":1,"pageSize":1,"size":1,"startRow":1,"endRow":1,"total":13,"pages":13,"list":[{"orderJnlNo":"06378d49-3f04-4a40-9815-a690129e17b3","userId":"1","spaceOwnerUserId":"1","carno":"aaa","spaceno":"1","billStatus":5,"billStatusQuery":null,"parkHours":3,"unitPrice":2.00,"budgetPrice":6.00,"createTime":1508229785000,"actualParkHours":0.29,"actualPrice":2.00,"delayParkHours":0,"lastPayTime":null,"payedMoney":0.00,"recodeTime":1508230823000,"uuid":"a170a326-aac8-48c7-b472-cb140eb8b646"}],"prePage":0,"nextPage":2,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2,3,4,5,6,7,8],"navigateFirstPage":1,"navigateLastPage":8,"lastPage":8,"firstPage":1}}
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getparkingspaceusedhistory")
    @ResponseBody
	public OperationResult getParkingSpaceUsedHistory(
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize,
            @RequestParam(value = "spaceno", required = false) String spaceno,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		PageHelper.startPage(page, pageSize);
		try {
			ParkingSpaceBillHis parkingSpaceBillHis = new ParkingSpaceBillHis();
			parkingSpaceBillHis.setSpaceno(spaceno);
			List<ParkingSpaceBillHis> list = parkingSpaceBillHisService.getParkingSpaceBillHisList(parkingSpaceBillHis);
			res.setFlag(true);
			if(list != null && list.size() > 0) {
				PageInfo<ParkingSpaceBillHis> listPage = new PageInfo<ParkingSpaceBillHis>(list);
				res.setResData(listPage);
			}else {
				res.setResData(list);
			}
		}catch(ParkspaceServiceException e) {
			LOG.error("查询车位使用记录失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * 
	 * @Title: getParkingSpaceUsing
	 * <p>Description:查询正在使用的车位
	 * get:http://localhost:8080/parkspace/v1/parkingspace/getparkingspaceusing?page=1&pageSize=1&spaceno=1
	 * 入参：参考url,spaceno可以为空
	 * 出参：{"flag":true,"errCode":null,"resData":{"pageNum":1,"pageSize":1,"size":1,"startRow":1,"endRow":1,"total":1,"pages":1,"list":[{"spaceno":"1","spacenoLikeQuery":null,"comid":"d2ac2ef6-acad-411a-9b2a-9732d47028b5","parkPositionFloor":"-1F","parkPositionZone":"A区","parkPositionX":"100X","parkPositionY":"200Y","parkStatus":"0","parkStatusQuery":null,"parkType":"P","parkTypeQuery":null,"parkPositionDes":"3号楼附近","spaceOwner":"13518678898","memo":"测试这位添加测试修改信息","createBy":"孙辽东create","createTime":1506413381000,"modifyBy":"modifyBy","modifyTime":1506413856000,"community":{"comid":"d2ac2ef6-acad-411a-9b2a-9732d47028b5","comidQuery":null,"zoneid":"4e73503c-7052-41bc-a716-b8b2d2e32e5e","comname":"王府庄园","address":"济洛路王府庄园更新地址更新地址","isenable":1,"isenableQuery":null,"memo":"测试添加小区","createBy":"孙辽东创建","createTime":1506244564000,"modifyBy":"孙辽东编辑","modifyTime":1506264204000,"price":null,"maxPriceAllDay":16.00,"freeParkingMinutes":60,"zone":null},"zone":{"zoneid":"4e73503c-7052-41bc-a716-b8b2d2e32e5e","zonename":"天桥行政区","isenable":1,"isenableQuery":null,"province":"山东省","city":"济南","zone":"天桥区","memo":"测试区域添加00010001","createBy":"孙辽东","createTime":1506244295000,"modifyBy":"孙辽东02","modifyTime":1506263494000},"spaceOwnerUser":{"userId":"1","userName":"test01","nickName":"test01_nickName","userPwd":null,"telePhone":"15300201276","isAdmin":0,"idType":0,"idNo":"370714198211240087","state":2,"weixinAccount":"123456","avator":"admin","memo":"test user","createBy":"admin","createTime":1506246726000,"modifyBy":"admin","modifyTime":1506246726000,"realName":"孙辽东real","spaceno":"1","isauth":0,"isauthQuery":null,"carno":"abc","parkingSpace":null,"certifiedTime":1507471148000,"community":null,"zone":null},"parkHours":null,"parkHoursString":null,"shareConfigList":null,"useCount":2}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}}
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getparkingspaceusing")
    @ResponseBody
	public OperationResult getParkingSpaceUsing(
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize,
            @RequestParam(value = "spaceno", required = false) String spaceno,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		PageHelper.startPage(page, pageSize);
		try {
			ParkingSpaceBill parkingSpaceBill = new ParkingSpaceBill();
			parkingSpaceBill.setSpaceno(spaceno);
			List<ParkingSpaceBill> list = parkingSpaceBillService.getParkingSpaceBillList(parkingSpaceBill);
			res.setFlag(true);
			if(list != null && list.size() > 0) {
				PageInfo<ParkingSpaceBill> listPage = new PageInfo<ParkingSpaceBill>(list);
				res.setResData(listPage);
			}else {
				res.setResData(list);
			}
		}catch(ParkspaceServiceException e) {
			LOG.error("查询正在使用的车位失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * 
	 * @Title: getParkingSpaceSoonExpire
	 * <p>Description:查询快到期的车位
	 * get:http://localhost:8080/parkspace/v1/parkingspace/getparkingspacesoonexpire?page=1&pageSize=1&spaceno=1
	 * 入参：参考url,spaceno可以为空
	 * 出参：{"flag":true,"errCode":null,"resData":{"pageNum":1,"pageSize":1,"size":1,"startRow":1,"endRow":1,"total":1,"pages":1,"list":[{"spaceno":"1","spacenoLikeQuery":null,"comid":"d2ac2ef6-acad-411a-9b2a-9732d47028b5","parkPositionFloor":"-1F","parkPositionZone":"A区","parkPositionX":"100X","parkPositionY":"200Y","parkStatus":"0","parkStatusQuery":null,"parkType":"P","parkTypeQuery":null,"parkPositionDes":"3号楼附近","spaceOwner":"13518678898","memo":"测试这位添加测试修改信息","createBy":"孙辽东create","createTime":1506413381000,"modifyBy":"modifyBy","modifyTime":1506413856000,"community":{"comid":"d2ac2ef6-acad-411a-9b2a-9732d47028b5","comidQuery":null,"zoneid":"4e73503c-7052-41bc-a716-b8b2d2e32e5e","comname":"王府庄园","address":"济洛路王府庄园更新地址更新地址","isenable":1,"isenableQuery":null,"memo":"测试添加小区","createBy":"孙辽东创建","createTime":1506244564000,"modifyBy":"孙辽东编辑","modifyTime":1506264204000,"price":null,"maxPriceAllDay":16.00,"freeParkingMinutes":60,"zone":null},"zone":{"zoneid":"4e73503c-7052-41bc-a716-b8b2d2e32e5e","zonename":"天桥行政区","isenable":1,"isenableQuery":null,"province":"山东省","city":"济南","zone":"天桥区","memo":"测试区域添加00010001","createBy":"孙辽东","createTime":1506244295000,"modifyBy":"孙辽东02","modifyTime":1506263494000},"spaceOwnerUser":{"userId":"1","userName":"test01","nickName":"test01_nickName","userPwd":null,"telePhone":"15300201276","isAdmin":0,"idType":0,"idNo":"370714198211240087","state":2,"weixinAccount":"123456","avator":"admin","memo":"test user","createBy":"admin","createTime":1506246726000,"modifyBy":"admin","modifyTime":1506246726000,"realName":"孙辽东real","spaceno":"1","isauth":0,"isauthQuery":null,"carno":"abc","parkingSpace":null,"certifiedTime":1507471148000,"community":null,"zone":null},"parkHours":null,"parkHoursString":null,"shareConfigList":null,"useCount":2}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}}
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getparkingspacesoonexpire")
    @ResponseBody
	public OperationResult getParkingSpaceSoonExpire(
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize,
            @RequestParam(value = "spaceno", required = false) String spaceno,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		PageHelper.startPage(page, pageSize);
		try {
			ParkingSpaceBill parkingSpaceBill = new ParkingSpaceBill();
			parkingSpaceBill.setIsQuerySoonExpire(1);
			parkingSpaceBill.setSpaceno(spaceno);
			List<ParkingSpaceBill> list = parkingSpaceBillService.getParkingSpaceBillList(parkingSpaceBill);
			res.setFlag(true);
			if(list != null && list.size() > 0) {
				PageInfo<ParkingSpaceBill> listPage = new PageInfo<ParkingSpaceBill>(list);
				res.setResData(listPage);
			}else {
				res.setResData(list);
			}
		}catch(ParkspaceServiceException e) {
			LOG.error("查询快要到期的车位失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
}
