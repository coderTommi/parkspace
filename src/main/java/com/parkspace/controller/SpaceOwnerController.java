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
	 * get:http://localhost:8080/parkspace/v1/spaceowner/getallspaceowner?page=1&pageSize=1
	 * 入参（查询所有）：空
	 * 入参（业主可按名字、手机号、车位号模糊查询）：{"userName":"孙","telePhone":"15300201276","spaceno":"1","carno":"a"}
	 * 出参：{"flag":true,"errCode":null,"resData":{"pageNum":1,"pageSize":1,"size":1,"startRow":1,"endRow":1,"total":1,"pages":1,"list":[{"userId":"1","userName":"test01","nickName":"test01_nickName","userPwd":null,"telePhone":"15300201276","isAdmin":0,"idType":0,"idNo":"370714198211240087","state":2,"weixinAccount":"123456","avator":"admin","memo":"test user","createBy":"admin","createTime":1506246726000,"modifyBy":"admin","modifyTime":1506246726000,"realName":"孙辽东real","spaceno":"1","isauth":0,"isauthQuery":null,"carno":"abc","parkingSpace":{"spaceno":"1","spacenoLikeQuery":null,"comid":"d2ac2ef6-acad-411a-9b2a-9732d47028b5","parkPositionFloor":"-1F","parkPositionZone":"A区","parkPositionX":"100X","parkPositionY":"200Y","parkStatus":"0","parkStatusQuery":null,"parkType":"P","parkTypeQuery":null,"parkPositionDes":"3号楼附近","spaceOwner":"13518678898","memo":"测试这位添加测试修改信息","createBy":"孙辽东create","createTime":1506413381000,"modifyBy":"modifyBy","modifyTime":1506413856000,"community":null,"zone":null,"spaceOwnerUser":null,"parkHours":null,"parkHoursString":null,"shareConfigList":null,"useCount":2},"certifiedTime":1507471148000,"community":{"comid":"d2ac2ef6-acad-411a-9b2a-9732d47028b5","comidQuery":null,"zoneid":"4e73503c-7052-41bc-a716-b8b2d2e32e5e","comname":"王府庄园","address":"济洛路王府庄园更新地址更新地址","isenable":1,"isenableQuery":null,"memo":"测试添加小区","createBy":"孙辽东创建","createTime":1506244564000,"modifyBy":"孙辽东编辑","modifyTime":1506264204000,"price":null,"maxPriceAllDay":16.00,"freeParkingMinutes":60,"zone":null},"zone":{"zoneid":"4e73503c-7052-41bc-a716-b8b2d2e32e5e","zonename":"天桥行政区","isenable":1,"isenableQuery":null,"province":"山东省","city":"济南","zone":"天桥区","memo":"测试区域添加00010001","createBy":"孙辽东","createTime":1506244295000,"modifyBy":"孙辽东02","modifyTime":1506263494000}}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}}
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
	 * post:http://localhost:8080/parkspace/v1/spaceowner/addblacklist
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
	 * get:http://localhost:8080/parkspace/v1/spaceowner/getblacklist
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
