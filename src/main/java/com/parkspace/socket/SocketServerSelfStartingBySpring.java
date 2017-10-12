package com.parkspace.socket;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/**
 * @Title: SocketServerSelfStartingBySpring.java
 * @Package com.parkspace.socket
 * <p>Description:通过spring加载完成自启动</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月12日 上午11:00:26</p>
*/
@Component("socketServerSelfStartingBySpring")
public class SocketServerSelfStartingBySpring implements ApplicationListener<ContextRefreshedEvent>{
	
   /**
    * 获取日志接口.
    */
   private static final Log LOG = LogFactory.getLog(SocketServerSelfStartingBySpring.class);
	
	@Resource
	private SocketServerService socketServerService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent context) {
		LOG.info("===start====spring 加载完成之后启动Socket服务========");
		socketServerService.startServer();
		LOG.info("===end=====spring 加载完成之后启动Socket服务========");
	}
}
