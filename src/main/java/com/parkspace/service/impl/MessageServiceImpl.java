package com.parkspace.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.parkspace.db.rmdb.dao.MessageDao;
import com.parkspace.db.rmdb.entity.Message;
import com.parkspace.service.IMessageService;

/**
 * @Title: MessageServiceImpl.java
 * @Package com.parkspace.service.impl
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月7日 下午8:22:41</p>
*/
@Service("messageService")
public class MessageServiceImpl implements IMessageService{
	@Resource
	private MessageDao messageDao;
	/**
	 * 
	 * @Title: getMessage
	 * <p>Description:查询消息</p>
	 * @param     UUID 消息主键
	 * @return Message    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月7日 下午8:01:22</p>
	 */
	@Override
	public Message getMessage(String UUID) {
		return messageDao.getMessage(UUID);
	}
	/**
	 * 
	 * @Title: addMessage
	 * <p>Description:增加message</p>
	 * @param     message 需要增加的信息
	 * @return Message    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月7日 下午8:03:28</p>
	 */
	@Override
	public Message addMessage(Message message) {
		message.setUUID(UUID.randomUUID().toString());
		message.setCreateTime(new Date());
		message.setModifyTime(new Date());
		messageDao.addMessage(message);
		return message;
	}
	/**
	 * 
	 * @Title: updateMessage
	 * <p>Description:更新message</p>
	 * @param     message 需要更新的信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月7日 下午8:03:49</p>
	 */
	@Override
	public void updateMessage(Message message) {
		message.setModifyTime(new Date());
		messageDao.updateMessage(message);
	}
	/**
	 * 
	 * @Title: deleteMessage
	 * <p>Description:删除message
	 * 更新消息的状态status 为2
	 * </p>
	 * @param     UUID 消息主键
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月7日 下午8:04:07</p>
	 */
	@Override
	public void deleteMessage(String UUID) {
		messageDao.deleteMessage(UUID);
	}
	/**
	 * 
	 * @Title: getMessageAllList
	 * <p>Description:查询所有message</p>
	 * @param     message message查询过滤条件
	 * @return List<Message>    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月7日 下午8:04:47</p>
	 */
	@Override
	public List<Message> getMessageAllList(Message message){
		if(message == null) {
			message = new Message();
		}
		return messageDao.getMessageAllList(message);
	}
}
