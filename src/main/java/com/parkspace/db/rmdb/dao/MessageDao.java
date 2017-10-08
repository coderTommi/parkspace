package com.parkspace.db.rmdb.dao;

import java.util.List;

import com.parkspace.db.rmdb.entity.Message;

/**
 * @Title: MessageDao.java
 * @Package com.parkspace.db.rmdb.dao
 * <p>Description:消息Dao层接口
 * 定义数据库操作接口
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月7日 下午7:58:50</p>
*/

public interface MessageDao {
	/**
	 * 
	 * @Title: getMessage
	 * <p>Description:查询消息</p>
	 * @param     UUID 消息主键
	 * @return Message    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月7日 下午8:01:22</p>
	 */
	public Message getMessage(String UUID);
	/**
	 * 
	 * @Title: addMessage
	 * <p>Description:增加message</p>
	 * @param     message 需要增加的信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月7日 下午8:03:28</p>
	 */
	public void addMessage(Message message);
	/**
	 * 
	 * @Title: updateMessage
	 * <p>Description:更新message</p>
	 * @param     message 需要更新的信息
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月7日 下午8:03:49</p>
	 */
	public void updateMessage(Message message);
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
	public void deleteMessage(String UUID);
	/**
	 * 
	 * @Title: getMessageAllList
	 * <p>Description:查询所有message</p>
	 * @param     message message查询过滤条件
	 * @return List<Message>    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月7日 下午8:04:47</p>
	 */
	public List<Message> getMessageAllList(Message message);
	
}
