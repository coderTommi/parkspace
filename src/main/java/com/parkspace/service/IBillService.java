package com.parkspace.service;

import java.util.List;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.entity.Bill;

/**
 * @Title: IBillService.java
 * @Package com.parkspace.service
 * <p>Description:</p>
 * @author lidongliang
 * @version V1.0.0 
 * <p>CreateDate:2017年10月3日 下午2:18:07</p>
*/

public interface IBillService {
	
	public String save(Bill bill) throws ParkspaceServiceException, Exception;
	
	public void updateWithTransaction(Bill bill) throws ParkspaceServiceException, Exception;
	
	public void updateWithoutTransaction(Bill bill) throws ParkspaceServiceException, Exception;
	
	public List<Bill> getList() throws ParkspaceServiceException, Exception;
	
	public List<Bill> getListByUserId(String userId)  throws ParkspaceServiceException, Exception;

}
