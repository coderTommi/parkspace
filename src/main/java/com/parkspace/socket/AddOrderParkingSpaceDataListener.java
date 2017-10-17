package com.parkspace.socket;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.db.rmdb.entity.ParkingSpaceBill;
import com.parkspace.model.SocketDataModel;
import com.parkspace.service.IParkingSpaceBillService;
import com.parkspace.service.IParkingSpaceService;
import com.parkspace.util.Constants;
import com.parkspace.util.JsonUtils;

/**
 * @Title: AddOrderParkingSpaceDataListener.java
 * @Package com.parkspace.socket
 * <p>Description:增加订单数据处理监听</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月13日 上午11:19:50</p>
*/

public class AddOrderParkingSpaceDataListener<T> implements DataListener<T> {
	/**
     * 获取日志接口.
     */
    private static final Log LOG = LogFactory.getLog(AddOrderParkingSpaceDataListener.class);
    private IParkingSpaceService parkingSpaceService;
	private IParkingSpaceBillService parkingSpaceBillService;
	
	public AddOrderParkingSpaceDataListener() {
		
	}
	public AddOrderParkingSpaceDataListener(IParkingSpaceService parkingSpaceService,
			IParkingSpaceBillService parkingSpaceBillService) {
		this.parkingSpaceService = parkingSpaceService;
		this.parkingSpaceBillService = parkingSpaceBillService;
	}
    
	@Override
	public void onData(SocketIOClient client, T data, AckRequest ackSender) 
			throws ParkspaceServiceException {
		LOG.info("========服务端返回临时车位授权信息========="+data);	
		SocketDataModel socketDataModel = JsonUtils.str2Object(data.toString(), 
        		SocketDataModel.class);
		// check is ack requested by client,

        // but it's not required check

        if (!ackSender.isAckRequested()) {
        	throw new ParkspaceServiceException(
					Constants.ERRORCODE.SEND_MESSAGE_IS_FAILURE.toString(), 
					"发送消息失败");
        }
		
		if(socketDataModel != null) {
			String userId = socketDataModel.getUserId();
        	String carno = socketDataModel.getCarno();
        	//查询订单号
        	ParkingSpaceBill parkingSpaceBill = new ParkingSpaceBill();
        	parkingSpaceBill.setUserId(userId);
        	parkingSpaceBill.setCarno(carno);
        	List<ParkingSpaceBill> list = parkingSpaceBillService.getParkingSpaceBillList(parkingSpaceBill);
			if(socketDataModel.isSuccess()) {//成功，校对本地数据是否存在如果不存在返回false，删除临时权限
	        	if(list == null || list.size() <= 0) {
	        		ackSender.sendAckData("false");
	        		throw new ParkspaceServiceException(
							Constants.ERRORCODE.SEND_MESSAGE_IS_FAILURE.toString(), 
							"发送消息失败");
	        	}else {
	        		for(ParkingSpaceBill b : list) {
						parkingSpaceBillService.updateGrantParkingSpaceBill(b.getOrderJnlNo());
					}
	        		ackSender.sendAckData("true");
	        	}
			}else {//失败，删除本地订单信息
				if(list != null && list.size() > 0) {
					for(ParkingSpaceBill b : list) {
						//取消订单
						parkingSpaceService.cancelOrderParkingSpace(b.getOrderJnlNo());
						//发送消息通知用户--订单信息被删除
//						parkingSpaceBillService.deleteParkingSpaceBill(b.getOrderJnlNo());
					}
				}
				ackSender.sendAckData("false");
				throw new ParkspaceServiceException(
						Constants.ERRORCODE.SEND_MESSAGE_IS_FAILURE.toString(), 
						"发送消息失败");
			}
		}
	}
	public IParkingSpaceService getParkingSpaceService() {
		return parkingSpaceService;
	}
	public void setParkingSpaceService(IParkingSpaceService parkingSpaceService) {
		this.parkingSpaceService = parkingSpaceService;
	}
	public IParkingSpaceBillService getParkingSpaceBillService() {
		return parkingSpaceBillService;
	}
	public void setParkingSpaceBillService(IParkingSpaceBillService parkingSpaceBillService) {
		this.parkingSpaceBillService = parkingSpaceBillService;
	}
}
