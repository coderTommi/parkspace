package com.parkspace.service;

import java.math.BigDecimal;

import com.parkspace.common.exception.ParkspaceServiceException;

/**
 * @Title: IRemoteTrsService.java
 * @Package com.parkspace.service
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月3日 下午2:59:40</p>
*/

public interface IRemoteTrsService {
	
	public static final String ADMINUSER = "admin";
	
	/**
	 * 查看遠程交易結果
	 * @Title: checkRemoteTrsRes
	 * <p>Description:</p>
	 * @param     参数
	 * @return boolean    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月3日 下午3:01:31</p>
	 */
	public boolean checkRemoteTrsRes(String remoteJnlNo, Integer payChannel, BigDecimal amt);
	/**
	 * 遠程轉賬
	 * @Title: remoteTrans
	 * <p>Description:</p>
	 * @param     参数
	 * @return boolean    返回遠程流水號
	 * @throws
	 * <p>CreateDate:2017年10月3日 下午3:02:37</p>
	 */
	public String remoteTrans(String payee, Integer payChannel, BigDecimal amt);
	
}
