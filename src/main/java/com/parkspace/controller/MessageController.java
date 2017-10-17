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
import com.parkspace.db.rmdb.entity.Message;
import com.parkspace.service.IMessageService;

/**
 * @Title: MessageController.java
 * @Package com.parkspace.controller
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月7日 下午8:28:00</p>
*/
@Controller
@RequestMapping("/v1/message")
public class MessageController {
	/**
     * 获取日志接口.
     */
    private static final Log LOG = LogFactory.getLog(MessageController.class);
	@Resource
	private IMessageService messageService;
	
	/**
	 * @Title: addMessage
	 * <p>Description:增加消息信息
	 * post:http://localhost:8080/parkspace/v1/message/addmessage
	 * 入参：{"context":"测试消息内容体Tue Oct 17 18:46:51 CST 2017","status":0,"messageObject":2,"comid":"","enableStartTime":1508237211979,"enableEndTime":1508410008458,"memo":"消息备注Tue Oct 17 18:46:51 CST 2017"}
	 * 出参：{"flag":true,"errCode":null,"resData":{"context":"测试消息内容体Tue Oct 17 18:46:51 CST 2017","status":0,"statusQuery":null,"messageObject":2,"messageObjectQuery":null,"comid":"","enableStartTime":1508237211979,"enableEndTime":1508410008458,"memo":"消息备注Tue Oct 17 18:46:51 CST 2017","createTime":1508237212323,"modifyTime":1508237212323,"uuid":"7cd4952a-65b8-4000-a4c6-3bf965b32b35"}}
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/addmessage")
    @ResponseBody
	public OperationResult addMessage(
			@RequestBody Message message,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		try {
			Message newMessage = messageService.addMessage(message);
			res.setResData(newMessage);
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("添加消息失败"+"{"+message+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: deleteMessage
	 * <p>Description:删除某个消息
	 * post:http://localhost:8080/parkspace/v1/message/deletemessage/b6a0a6e7-f522-4c4f-9dc8-33897f8a6da4
	 * 入参：在url中增加id
	 * 出参：{"flag":true,"errCode":null,"resData":null}
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/deletemessage/{UUID}")
    @ResponseBody
	public OperationResult deleteMessage(
            @PathVariable String UUID,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		try {
			messageService.deleteMessage(UUID);
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("根据消息主键UUID"+"{"+UUID+"},删除消息信息失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * @Title: getMessage
	 * <p>Description:查询某个消息的具体内容
	 * post:http://localhost:8080/parkspace/v1/message/getmessage/b6a0a6e7-f522-4c4f-9dc8-33897f8a6da4
	 * 入参：在url中增加id
	 * 出参：{"flag":true,"errCode":null,"resData":{"context":"测试消息内容体Tue Oct 17 18:46:51 CST 2017","status":2,"statusQuery":null,"messageObject":2,"messageObjectQuery":null,"comid":"","enableStartTime":1508169600000,"enableEndTime":1508342400000,"memo":"消息备注Tue Oct 17 18:46:51 CST 2017","createTime":1508237212000,"modifyTime":1508237365000,"uuid":"7cd4952a-65b8-4000-a4c6-3bf965b32b35"}}
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/getmessage/{UUID}")
    @ResponseBody
	public OperationResult getmessage(
            @PathVariable String UUID,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		try {
			Message message = messageService.getMessage(UUID);
			res.setResData(message);
			res.setFlag(true);
		}catch(ParkspaceServiceException e) {
			LOG.error("根据消息主键UUID"+"{"+UUID+"},查询消息详细信息失败" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	/**
	 * 
	 * @Title: getAllMessage
	 * <p>Description:查询所有message
	 * get:http://localhost:8080/parkspace/v1/message/getallmessage?page=1&pageSize=1
	 * 入参(查询所有)：空
	 * 入参（小区消息）：{"comid":"1"}
	 * 入参（消息对象消息面向的人群0物业，1业主，2车主,-1 所有）：{"messageObjectQuery":[0]}
	 * 入参（状态：）{"statusQuery":[1,2]}
	 * 出参：{"flag":true,"errCode":null,"resData":{"pageNum":1,"pageSize":1,"size":1,"startRow":1,"endRow":1,"total":6,"pages":6,"list":[{"context":"测试消息内容体Tue Oct 17 18:46:51 CST 2017","status":2,"statusQuery":null,"messageObject":2,"messageObjectQuery":null,"comid":"","enableStartTime":1508169600000,"enableEndTime":1508342400000,"memo":"消息备注Tue Oct 17 18:46:51 CST 2017","createTime":1508237212000,"modifyTime":1508237365000,"uuid":"7cd4952a-65b8-4000-a4c6-3bf965b32b35"}],"prePage":0,"nextPage":2,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2,3,4,5,6],"navigateFirstPage":1,"navigateLastPage":6,"firstPage":1,"lastPage":6}}
	 * 1.可以通过信息的状态过滤信息
	 * 消息状态：0草稿，1，发布，2删除
	 * private Integer status;
	 * 消息状态，多条件查询
	 * private Integer[] statusQuery;
	 * 2.可以通过消息对象查询消息群体
	 * 消息对象：消息面向的人群0物业，1业主，2车主,-1 所有
	 * private Integer messageObject;
	 * 消息对象多条件查询
	 * private Integer[] messageObjectQuery;
	 * 3.可以通过小区id查询某些小区的消息
	 * 小区编号,如果不选择默认为全局，可以选择多个表示面向多个小区
	 * private String comid;
	 * 4.可以通过有效范围过滤数据
	 * 有效开始时间：private Date enableStartTime;
	 * 有效截至时间：private Date enableEndTime;
	 * </p>
	 * @param     参数
	 * @return OperationResult    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月1日 上午9:39:25</p>
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getallmessage")
    @ResponseBody
	public OperationResult getAllMessage(
			@RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "pageSize", required = true) int pageSize,
            @RequestBody Message message,
            HttpServletRequest request) {
		OperationResult res = new OperationResult();
		PageHelper.startPage(page, pageSize);
		try {
			List<Message> list = this.messageService.getMessageAllList(message);
			res.setFlag(true);
			if(list != null && list.size() > 0) {
				PageInfo<Message> listPage = new PageInfo<Message>(list);
				res.setResData(listPage);
			}else {
				res.setResData(list);
			}
		}catch(ParkspaceServiceException e) {
			LOG.error("查询消息失败："+"{"+message+"}" 
					+ e.getMessageCode() + e.getMessage());
			res.setFlag(false);
			res.setErrCode(e.getMessageCode());
		}
		return res;
	}
	
	
}
