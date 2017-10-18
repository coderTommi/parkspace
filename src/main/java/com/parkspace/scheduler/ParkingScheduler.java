package com.parkspace.scheduler;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.parkspace.db.rmdb.entity.ParkingSpaceBill;
import com.parkspace.model.SocketDataModel;
import com.parkspace.service.IParkingSpaceBillService;
import com.parkspace.service.IParkingSpaceService;
import com.parkspace.socket.SocketServerService;
import com.parkspace.util.JsonUtils;

/**
 * @Title: ParkingScheduler.java
 * @Package com.parkspace.scheduler
 * <p>Description:停车定时任务处理</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月3日 下午2:07:07</p>
*/
@Service("parkingScheduler")
public class ParkingScheduler {
	/**
     * 获取日志接口.
     */
    private static final Log LOG = LogFactory.getLog(ParkingScheduler.class);
    
	@Resource
	private IParkingSpaceBillService parkingSpaceBillService;
	@Resource
	private IParkingSpaceService parkingSpaceService;
	@Resource
	private SocketServerService socketServerService;
	
	/**
	 * @Title: cancelOrderParkingSpace
	 * <p>Description:监控预约的车位，超过15分钟自动取消
	 * 当一次方法执行完毕之后，延迟多少毫秒再执行该方法
	 * </p>
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月11日 上午8:57:07</p>
	 */
//	@Scheduled(fixedDelay = 1000)
	public void cancelOrderParkingSpace() {
		//查询预约超过15分钟的订单
		List<ParkingSpaceBill> list = parkingSpaceBillService.getOverdueOrderParkingSpaceBillList();
		if(list != null && list.size() > 0) {
			for(ParkingSpaceBill parkingSpaceBill : list) {
				String orderJnlNo = parkingSpaceBill.getOrderJnlNo();
				try {
					parkingSpaceService.cancelOrderParkingSpace(orderJnlNo);
				}catch(Exception e) {
					LOG.error("自动取消订单失败，订单编号：【"+orderJnlNo+"】失败描述"+e.getMessage());
				}
			}
		}
	}
	//监控使用中的车位，如果超时，需要提示信息处理
	/**
	 * @Title: regularDeductionsForParkingSpaceBill
	 * <p>Description:24小时扣款一次
	 * 针对未完成的订单（订单状态是使用中和延期使用中的订单）
	 * 24小时扣款一次，并且记录扣款时间
	 * </p>
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月11日 下午2:01:59</p>
	 */
//	@Scheduled(fixedDelay = 1000)
	public void regularDeductionsForParkingSpaceBill() {
		//24小时内未付款的订单
		List<ParkingSpaceBill> list = parkingSpaceBillService.getNoPayedParkingSpaceBillListInPayInterval();
		if(list != null && list.size() > 0) {
			for(ParkingSpaceBill parkingSpaceBill : list) {
				String orderJnlNo = parkingSpaceBill.getOrderJnlNo();
				try {
					//调用扣款操作
					parkingSpaceService.regularDeductionsForParkingSpaceBill(parkingSpaceBill);
				}catch(Exception e) {
					LOG.error("自动扣款失败，订单编号：【"+orderJnlNo+"】失败描述"+e.getMessage());
				}
			}
		}
	}
	/**
	 * @Title: sendParkingSpaceBill2ParkingSystem
	 * <p>Description:定时处理预约订单信息
	 * 1.查询为通过授权的数据
	 * 2.通过server发送到客户端
	 * 3.更新状态
	 * </p>
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月17日 下午4:16:15</p>
	 */
	@Scheduled(fixedDelay = 1000)
	public void sendParkingSpaceBill2ParkingSystem() {
		List<ParkingSpaceBill> list = parkingSpaceBillService.getNoGrantParkingSpaceBillList();
		if(list != null && list.size() > 0) {
			for(ParkingSpaceBill parkingSpaceBill : list) {
				if(parkingSpaceBill != null) {
					SocketDataModel socketDataModel = new SocketDataModel();
					socketDataModel.setCarno(parkingSpaceBill.getCarno());
					socketDataModel.setSpaceno(parkingSpaceBill.getSpaceno());
					socketDataModel.setUserId(parkingSpaceBill.getUserId());
					String message = JsonUtils.object2String(socketDataModel);
					try {
//						socketServerService.sendMessageToAllClient("addOrderParkingSpace", message);
						socketServerService.sendMessageToOneClient(parkingSpaceBill.getComid(),
								"addOrderParkingSpace", message);
					}catch(Exception e) {
						LOG.error("开通临时权限失败："+e.getMessage());
					}
				}
			}
		}
	}
}
