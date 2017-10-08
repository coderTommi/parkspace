package com.parkspace.db.rmdb.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * @Title: Message.java
 * @Package com.parkspace.db.rmdb.entity
 * <p>Description:系统消息实体类</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月7日 下午7:53:03</p>
*/

public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//主键
	private String UUID;
	//消息内容
	private String context;
	//消息状态：0草稿，1，发布，2删除
	private Integer status;
	//消息状态，多条件查询
	private Integer[] statusQuery;
	//消息对象：消息面向的人群0物业，1业主，2车主,-1 所有
	private Integer messageObject;
	//消息对象多条件查询
	private Integer[] messageObjectQuery;
	//小区编号,如果不选择默认为全局，可以选择多个表示面向多个小区
	private String comid;
	//有效开始时间
	private Date enableStartTime;
	//有效截至时间
	private Date enableEndTime;
	//备注
	private String memo;
	//创建时间
	private Date createTime;
	//修改时间
	private Date modifyTime;
	public Integer[] getStatusQuery() {
		return statusQuery;
	}
	public void setStatusQuery(Integer[] statusQuery) {
		this.statusQuery = statusQuery;
	}
	public Integer[] getMessageObjectQuery() {
		return messageObjectQuery;
	}
	public void setMessageObjectQuery(Integer[] messageObjectQuery) {
		this.messageObjectQuery = messageObjectQuery;
	}
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getMessageObject() {
		return messageObject;
	}
	public void setMessageObject(Integer messageObject) {
		this.messageObject = messageObject;
	}
	public String getComid() {
		return comid;
	}
	public void setComid(String comid) {
		this.comid = comid;
	}
	
	public Date getEnableStartTime() {
		return enableStartTime;
	}
	public void setEnableStartTime(Date enableStartTime) {
		this.enableStartTime = enableStartTime;
	}
	public Date getEnableEndTime() {
		return enableEndTime;
	}
	public void setEnableEndTime(Date enableEndTime) {
		this.enableEndTime = enableEndTime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	@Override
	public String toString() {
		return "Message [UUID=" + UUID + ", context=" + context + ", status=" + status + ", statusQuery="
				+ Arrays.toString(statusQuery) + ", messageObject=" + messageObject + ", messageObjectQuery="
				+ Arrays.toString(messageObjectQuery) + ", comid=" + comid + ", enableStartTime=" + enableStartTime
				+ ", enableEndTime=" + enableEndTime + ", memo=" + memo + ", createTime=" + createTime + ", modifyTime="
				+ modifyTime + "]";
	}
}
