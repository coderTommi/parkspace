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
import com.parkspace.db.rmdb.entity.Caruser;
import com.parkspace.service.IBlackListService;
import com.parkspace.service.ICaruserService;

/**
 * @Title: CaruserController.java
 * @Package com.parkspace.controller
 * <p>Description:车主action</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月2日 上午11:19:00</p>
*/
@Controller
@RequestMapping("/v1/caruser")
public class CaruserController {
	/**
     * 获取日志接口.
     */
    private static final Log LOG = LogFactory.getLog(CaruserController.class);
	@Resource
	private ICaruserService caruserService;
	@Resource
	private IBlackListService blackListService;
	
	/**
	 * 
	 * @Title: getAllCaruser
	 * <p>Description:查询所有的业主信息
	 * get:http://localhost:8080/parkspace/v1/caruser/getallcaruser?page=1&pageSize=1
	 * 入参（查询所有业主）：空
	 * 入参（查询某个业主）：{"userId":"1"}
	 * 入参（按行政区划、小区【支持多选】查询车主）：{"community":{"comidQuery":["d2ac2ef6-acad-411a-9b2a-9732d47028b5"]},"zone":{"zoneid":"4e73503c-7052-41bc-a716-b8b2d2e32e5e"}}
	 * 入参（可按名字、手机号、车牌号模糊查询）：{"userName":"孙","telePhone":"15300201276","carno":"a"}
	 * 出参：{"flag":true,"errCode":null,"resData":{"pageNum":1,"pageSize":1,"size":1,"startRow":1,"endRow":1,"total":1,"pages":1,"list":[{"userId":"1","userName":"test01","nickName":"test01_nickName","userPwd":null,"telePhone":"15300201276","isAdmin":0,"idType":0,"idNo":"370714198211240087","state":2,"weixinAccount":"123456","avator":"admin","memo":"test user","createBy":"admin","createTime":1506246726000,"modifyBy":"admin","modifyTime":1506246726000,"realName":null,"carno":"aaa","isauth":1,"isauthQuery":null,"community":{"comid":"b6a0a6e7-f522-4c4f-9dc8-33897f8a6da4","comidQuery":null,"zoneid":"4e73503c-7052-41bc-a716-b8b2d2e32e5e","comname":"王府庄园1","address":"济洛路王府庄园1","isenable":-1,"isenableQuery":null,"memo":"测试添加小区1","createBy":"admin","createTime":1506841978000,"modifyBy":"admin","modifyTime":1506843637000,"price":null,"maxPriceAllDay":16.00,"freeParkingMinutes":60,"zone":null},"zone":{"zoneid":"4e73503c-7052-41bc-a716-b8b2d2e32e5e","zonename":"天桥行政区","isenable":1,"isenableQuery":null,"province":"山东省","city":"济南","zone":"天桥区","memo":"测试区域添加00010001","createBy":"孙辽东","createTime":1506244295000,"modifyBy":"孙辽东02","modifyTime":1506263494000},"parkCount":13,"isQueryNoEnoughMoney":0,"certifiedTime":1507629755000,"wallet":{"userId":"1","pledge":22.00,"balance":11.00,"bonus":1.00,"unclosedAmt":1.00,"lastTrsTime":1507303596000,"openTime":1507303593000}}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}}
	 * 可以通过状态过滤信息
	 * 状态0:未认证 1：已认证，默认1，-1表示禁用
	 * private Integer isauth;
	 * 多状态查询：状态查询
	 * private Integer[]  isauthQuery;
	 * 
	 * 通过配置一些属性多条件查询
	 * 按行政区划（String zone.zoneid）、
	 * 小区【支持多选】查询业主（String[] community.comidQuery）
	 * 可按名字(userName)、
	 * 手机号(telePhone)、
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
	@RequestMapping(method = RequestMethod.GET, value = "/getallcaruser")
    @ResponseBody
	public OperationResult getAllCaruser(
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize,
            @RequestBody Caruser caruser,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		PageHelper.startPage(page, pageSize);
		try {
			List<Caruser> list = caruserService.getCaruserAllInfoList(caruser);
			res.setFlag(true);
			if(list != null && list.size() > 0) {
				PageInfo<Caruser> listPage = new PageInfo<Caruser>(list);
				res.setResData(listPage);
			}else {
				res.setResData(list);
			}
		}catch(ParkspaceServiceException e) {
			LOG.error("查询车主信息失败："+"{"+caruser+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}catch(Exception e1) {
			LOG.error("查询车主信息失败："+"{"+caruser+"}" 
					+  e1.getMessage());
			res.setFlag(false);
		}
		return res;
	}
	/**
	 * 
	 * @Title: getNoEnoughMoneyCaruser
	 * <p>Description:查询余额不足的所有的业主信息
	 * get:http://localhost:8080/parkspace/v1/caruser/getnoenoughmoneycaruser?page=1&pageSize=1
	 * 入参：空
	 * 出参：{"flag":true,"errCode":null,"resData":{"pageNum":1,"pageSize":1,"size":1,"startRow":1,"endRow":1,"total":1,"pages":1,"list":[{"userId":"1","userName":"test01","nickName":"test01_nickName","userPwd":null,"telePhone":"15300201276","isAdmin":0,"idType":0,"idNo":"370714198211240087","state":2,"weixinAccount":"123456","avator":"admin","memo":"test user","createBy":"admin","createTime":1506246726000,"modifyBy":"admin","modifyTime":1506246726000,"realName":null,"carno":"aaa","isauth":1,"isauthQuery":null,"community":{"comid":"b6a0a6e7-f522-4c4f-9dc8-33897f8a6da4","comidQuery":null,"zoneid":"4e73503c-7052-41bc-a716-b8b2d2e32e5e","comname":"王府庄园1","address":"济洛路王府庄园1","isenable":-1,"isenableQuery":null,"memo":"测试添加小区1","createBy":"admin","createTime":1506841978000,"modifyBy":"admin","modifyTime":1506843637000,"price":null,"maxPriceAllDay":16.00,"freeParkingMinutes":60,"zone":null},"zone":{"zoneid":"4e73503c-7052-41bc-a716-b8b2d2e32e5e","zonename":"天桥行政区","isenable":1,"isenableQuery":null,"province":"山东省","city":"济南","zone":"天桥区","memo":"测试区域添加00010001","createBy":"孙辽东","createTime":1506244295000,"modifyBy":"孙辽东02","modifyTime":1506263494000},"parkCount":13,"isQueryNoEnoughMoney":0,"certifiedTime":1507629755000,"wallet":{"userId":"1","pledge":22.00,"balance":11.00,"bonus":1.00,"unclosedAmt":1.00,"lastTrsTime":1507303596000,"openTime":1507303593000}}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}}
	 * 可以通过状态过滤信息
	 * 状态0:未认证 1：已认证，默认1，-1表示禁用
	 * private Integer isauth;
	 * 多状态查询：状态查询
	 * private Integer[]  isauthQuery;
	 * 
	 * 通过配置一些属性多条件查询
	 * 按行政区划（String zone.zoneid）、
	 * 小区【支持多选】查询业主（String[] community.comidQuery）
	 * 可按名字(userName)、
	 * 手机号(telePhone)、
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
	@RequestMapping(method = RequestMethod.GET, value = "/getnoenoughmoneycaruser")
    @ResponseBody
	public OperationResult getNoEnoughMoneyCaruser(
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize,
            @RequestBody Caruser caruser,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		PageHelper.startPage(page, pageSize);
		try {
			if(caruser == null) {
				caruser = new Caruser();
			}
			caruser.setIsQueryNoEnoughMoney(1);
			List<Caruser> list = caruserService.getCaruserAllInfoList(caruser);
			res.setFlag(true);
			if(list != null && list.size() > 0) {
				PageInfo<Caruser> listPage = new PageInfo<Caruser>(list);
				res.setResData(listPage);
			}else {
				res.setResData(list);
			}
		}catch(ParkspaceServiceException e) {
			LOG.error("查询余额不足的所有的业主信息失败："+"{"+caruser+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}catch(Exception e1) {
			LOG.error("查询余额不足的所有的业主信息失败："+"{"+caruser+"}" 
					+  e1.getMessage());
			res.setFlag(false);
		}
		return res;
	}
	/**
	 * @Title: addBlackList
	 * <p>Description:加入黑名单
	 * post:http://localhost:8080/parkspace/v1/caruser/addblacklist
	 * 入参：{"userId":"1","memo":"测试把用户添加到黑名单中--车主"}
	 * 出参：{"flag":true,"errCode":null,"resData":{"userId":"1","isCancel":0,"memo":"测试把用户添加到黑名单中--车主","createTime":1508235658268,"modifyTime":1508235658268,"userType":1,"baseUser":null,"uuid":"5ccfa70c-acab-46ee-b884-13edf0353c07"}}
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
			//用户类型为车主
			blackList.setUserType(1);			
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
	 * get:http://localhost:8080/parkspace/v1/caruser/getblacklist?page=1&pageSize=1
	 * 入参：空
	 * 出参：{"flag":true,"errCode":null,"resData":{"pageNum":1,"pageSize":2,"size":2,"startRow":0,"endRow":1,"total":2,"pages":1,"list":[{"userId":"1","isCancel":0,"memo":"测试把用户添加到黑名单中--车主","createTime":1508235658000,"modifyTime":1508235658000,"userType":1,"baseUser":{"userId":"1","userName":"test01","nickName":"test01_nickName","userPwd":"123456a?","telePhone":"15300201276","isAdmin":0,"idType":0,"idNo":"370714198211240087","state":2,"weixinAccount":"123456","avator":"admin","memo":"test user","createBy":"admin","createTime":1506246726000,"modifyBy":"admin","modifyTime":1506246726000,"realName":"孙辽东real"},"uuid":"5ccfa70c-acab-46ee-b884-13edf0353c07"},{"userId":"1","isCancel":0,"memo":"测试把用户添加到黑名单中--车主","createTime":1506929265000,"modifyTime":1506929265000,"userType":1,"baseUser":{"userId":"1","userName":"test01","nickName":"test01_nickName","userPwd":"123456a?","telePhone":"15300201276","isAdmin":0,"idType":0,"idNo":"370714198211240087","state":2,"weixinAccount":"123456","avator":"admin","memo":"test user","createBy":"admin","createTime":1506246726000,"modifyBy":"admin","modifyTime":1506246726000,"realName":"孙辽东real"},"uuid":"7c89f921-cb16-4750-87a0-cbff288b0d0d"}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}}
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
			//用户类型为车主
			blackList.setUserType(1);			
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
