package com.parkspace.socket;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.parkspace.db.rmdb.entity.ParkingSpaceBill;
import com.parkspace.model.SocketDataModel;
import com.parkspace.service.IParkingSpaceBillService;
import com.parkspace.service.IParkingSpaceService;
import com.parkspace.util.JsonUtils;

/**
 * @Title: CheckInCommunityDataListener.java
 * @Package com.parkspace.socket
 * <p>Description:进入小区数据处理监听</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月13日 上午11:32:49</p>
*/

public class CheckInCommunityDataListener<T> implements DataListener<T> {
	/**
     * 获取日志接口.
     */
    private static final Log LOG = LogFactory.getLog(CheckInCommunityDataListener.class);
    
	private IParkingSpaceService parkingSpaceService;
	private IParkingSpaceBillService parkingSpaceBillService;
	
	public CheckInCommunityDataListener() {
		
	}
	public CheckInCommunityDataListener(IParkingSpaceService parkingSpaceService,
			IParkingSpaceBillService parkingSpaceBillService) {
		this.parkingSpaceService = parkingSpaceService;
		this.parkingSpaceBillService = parkingSpaceBillService;
	}
	
    
	@Override
	public void onData(SocketIOClient client, T data, AckRequest ackSender) throws Exception {
		String sa = client.getRemoteAddress().toString();
		//获取客户端连接的ip
        String clientIp = sa.substring(1,sa.indexOf(":"));
        
        SocketDataModel socketDataModel = JsonUtils.str2Object(data.toString(), 
        		SocketDataModel.class);
        if(socketDataModel != null) {
        	String userId = socketDataModel.getUserId();
        	String carno = socketDataModel.getCarno();
        	//查询订单号
        	ParkingSpaceBill parkingSpaceBill = new ParkingSpaceBill();
        	parkingSpaceBill.setUserId(userId);
        	parkingSpaceBill.setCarno(carno);
        	List<ParkingSpaceBill> list = parkingSpaceBillService.getParkingSpaceBillList(parkingSpaceBill);
        	if(list != null && list.size() > 0) {
        		for(ParkingSpaceBill p : list) {
        			if(p != null) {
        				try {
        					parkingSpaceService.confirmOrderParkingSpace(p.getOrderJnlNo());
        					socketDataModel.setSuccess(true);
        				}catch(Exception e) {
        					socketDataModel.setSuccess(false);
        					e.printStackTrace();
        					LOG.error("确认订单失败："+e.getMessage());
        				}
        				break;
        			}
        		}
        	}
        }
        /**
         * 发送消息
         */
        client.sendEvent("checkInCommunity", JsonUtils.object2String(socketDataModel));
        /*
         * 记录日志
         */
        LOG.info("客户端IP：【"+clientIp+"】发送的数据内容是："+data);
	}

}
