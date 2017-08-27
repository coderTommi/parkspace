package com.parkspace.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 响应对象类.
 * @author lidongliang
 *
 */
public class OperationResult implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 成功识别标志，成功：true 失败：false. **/
	private boolean flag;
	/** 错误码. **/
	private String errCode;
	/** 返回数据. **/
	private Object resData;
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	@XmlElement
	public Object getResData() {
		return resData;
	}
	public void setResData(Object resData) {
		this.resData = resData;
	}
	
}
