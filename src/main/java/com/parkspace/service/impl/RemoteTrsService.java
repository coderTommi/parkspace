package com.parkspace.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.parkspace.service.IRemoteTrsService;

/**
 * @Title: RemoteTrsService.java
 * @Package com.parkspace.service.impl
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月3日 下午4:15:32</p>
*/
@Service("remoteTrsService")
public class RemoteTrsService implements IRemoteTrsService {

	@Override
	public boolean checkRemoteTrsRes(String remoteJnlNo, Integer payChannel, BigDecimal amt) {
		
		return true;
	}

	@Override
	public String remoteTrans(String payee, Integer payChannel, BigDecimal amt) {
		// TODO Auto-generated method stub
		return null;
	}

}
