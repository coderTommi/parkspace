package com.parkspace.scheduler;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
	
	@Scheduled(fixedDelay = 1000)
	public void doSomething() { 
		System.out.println(new Date()+"=========================");
	}
	
	//监控预约的车位，超过15分钟自动取消
	//监控使用中的车位，如果超时，需要提示信息处理
}
