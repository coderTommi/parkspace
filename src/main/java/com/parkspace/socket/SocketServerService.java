package com.parkspace.socket;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.corundumstudio.socketio.AckCallback;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.service.IParkingSpaceBillService;
import com.parkspace.service.IParkingSpaceService;
import com.parkspace.util.Constants;

/**
 * @Title: SocketServerService.java
 * @Package com.parkspace.socket
 * <p>Description:道闸系统服务端socket</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月12日 上午9:48:33</p>
*/
@Service("socketServerService")
public class SocketServerService {
	@Resource
	private IParkingSpaceService parkingSpaceService;
	@Resource
	private IParkingSpaceBillService parkingSpaceBillService;
	//当前服务器的ip
//  private String serverIp = "";
  //当前服务器设备id
//  private String deviceId = "";
  //执行时间，时间单位为毫秒，不得小于等于0
	private static Long cacheTime = Long.MAX_VALUE;
  //延迟时间，时间单位为毫秒，不得小于等于0
//  private static Integer delay = 3000 * 60 * 60;
	private static Integer delay = 3000;
	/**
     * 获取日志接口.
     */
    private static final Log LOG = LogFactory.getLog(SocketServerService.class);
	private static SocketIOServer server;
	/**
	 * 记录客户端的socket的连接使用ip作为key
	 */
	private final static Map<String, SocketIOClient> clientsMap = new HashMap<String, SocketIOClient>();
	/**
	 * @Title: initServer
	 * <p>Description:启动服务</p>
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月12日 上午9:50:26</p>
	 */
	public void initServer() throws InterruptedException{
		
		Configuration config = new Configuration();
		//服务器主机ip    
        config.setHostname("localhost");
        //端口
        config.setPort(9092);
        config.setMaxFramePayloadLength(1024 * 1024);
        config.setMaxHttpContentLength(1024 * 1024);
        //实例化server
        server = new SocketIOServer(config);
        
        //监听各种事件，完成消息的推送
        /**
         * 1.进入小区事件
         * <p>Description:监听进入小区的事件
         * 需要完成-确认订单的操作即调用
         * parkingSpaceService.confirmOrderParkingSpace(orderJnlNo);
         */
        server.addEventListener("checkInCommunity", String.class, 
				new CheckInCommunityDataListener<String>(parkingSpaceService,
						parkingSpaceBillService));
        /**
         * 2.离开小区事件
         * 监听离开小区的事件
         * 调用订单支付接口
         */
        server.addEventListener("checkOutCommunity", String.class, 
        		new CheckOutCommunityDataListener<String>(parkingSpaceService,
        				parkingSpaceBillService));
        /**
         * 增加订单响应，反馈订单信息是否增加成功
         */
        server.addEventListener("addOrderParkingSpace", String.class,
        		new AddOrderParkingSpaceDataListener<String>(parkingSpaceService,
        				parkingSpaceBillService));
        /**
         * 其他事件监听
         */
        connectListener();
        /**
         * 一般消息，主要是一些提示信息
         */
        commonEventListener();
        /**
         * 启动server
         */
        server.start();
        Thread.sleep(Integer.MAX_VALUE);
        server.stop();
        
	}
	/**
	 * @Title: commonEventListener
	 * <p>Description:一般消息，主要是一些提示信息</p>
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月12日 上午10:23:10</p>
	 */
    private void commonEventListener(){
    	
    }
	/**
	 * 
	 * @Title: connectListener
	 * <p>Description:监听客户端的连接事件</p>
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月12日 上午10:05:45</p>
	 */
	private void connectListener() {
		//连接
		server.addConnectListener(new ConnectListener() {

			@Override
			public void onConnect(SocketIOClient client) {
				 String sa = client.getRemoteAddress().toString();
				 //获取设备ip
	             String clientIp = sa.substring(1,sa.indexOf(":"));
	             clientsMap.put(clientIp, client);
	             
	             LOG.info("客户端IP：【"+clientIp+"】,已上线！");
	             //可以跟客户端进行通信，比如
	             client.sendEvent("sendMessage", "客户端IP：【"+clientIp+"】您好，我们已经建立了通信");
			}
		});
		//断开
		server.addDisconnectListener(new DisconnectListener() {
			@Override
			public void onDisconnect(SocketIOClient client) {
				String sa = client.getRemoteAddress().toString();
				//获取设备ip
				String clientIp = sa.substring(1,sa.indexOf(":"));
				clientsMap.remove(clientIp);
             
				LOG.info("客户端IP：【"+clientIp+"】,已下线！");
			}
		});
	}
	/**
	 * 
	 * @Title: stopServer
	 * <p>Description:关闭服务
	 * 服务启动之后会保持连接，一般不会关闭
	 * </p>
	 * @param     参数
	 * @return void    返回类型
	 * @throws
	 * <p>CreateDate:2017年10月12日 上午10:54:04</p>
	 */
	public void stopServer(){
        if(server != null){
            server.stop();
            server = null;
        }
    }
	
	/**
     *  给所有连接客户端推送消息
     * @param eventType 推送的事件类型
     * @param message  推送的内容
     */
    public void sendMessageToAllClient(String eventType,String message) 
    		throws ParkspaceServiceException{
    	try {
    		if(server == null) {
    			startServer();
    		}
    		Collection<SocketIOClient> clients = server.getAllClients();
            for(SocketIOClient client: clients){
//                client.sendEvent(eventType,message);
               /* client.sendEvent(eventType, new MultiTypeAckCallback() {
					@Override
					public void onSuccess(MultiTypeArgs result) {
						LOG.info("========客户端响应：========"+result);
						if(result != null) {
							System.out.println("=====1111====="+result.get(0));
						}
					}
				} , message);*/
            	client.sendEvent(eventType, new AckCallback<String>(String.class) {
					@Override
					public void onSuccess(String result) {
						LOG.info("ack from client: " + client.getSessionId() + " data: " + result);
					}
            		
            	}, message);
            }
    	}catch(Exception e) {
    		e.printStackTrace();
    		throw new ParkspaceServiceException(
					Constants.ERRORCODE.SEND_MESSAGE_IS_FAILURE.toString(), 
					"发送消息失败");
    	}
    }
    /**
     * 给具体的客户端推送消息
     * @param deviceId 设备类型
     * @param eventType推送事件类型
     * @param message 推送的消息内容
     */
    public void sendMessageToOneClient(String uuid,String eventType,String message) 
    		throws ParkspaceServiceException{
        try {
            if(uuid != null && !"".equals(uuid)){
                SocketIOClient client = (SocketIOClient)clientsMap.get(uuid);
                if(client != null){
                    client.sendEvent(eventType,message);
                }
            }
        } catch (Exception e) {
        	LOG.error("发送消息失败："+e.getMessage());
            e.printStackTrace();
            throw new ParkspaceServiceException(
					Constants.ERRORCODE.SEND_MESSAGE_IS_FAILURE.toString(), 
					"发送消息失败");
        }
    }
    /**
     * @Title: getServer
     * <p>Description:返回server</p>
     * @param     参数
     * @return SocketIOServer    返回类型
     * @throws
     * <p>CreateDate:2017年10月12日 上午10:56:19</p>
     */
    public static SocketIOServer getServer() {
        return server;
    }
    /**
     * @Title: startServer
     * <p>Description:启动服务</p>
     * @param     参数
     * @return void    返回类型
     * @throws
     * <p>CreateDate:2017年10月12日 下午5:38:58</p>
     */
    public void startServer() {
		Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
          public void run() {
            //启动socket监听
              try{
                  if(SocketServerService.getServer() == null){
                      new Thread(new Runnable() {
                          @Override
                          public void run() {
                              try {
                            	  initServer();
                              } catch (InterruptedException e) {
                                  e.printStackTrace();
                              }
                          }
                      }).start();
                  }
              }catch(Exception e){
            	  e.printStackTrace();
              }
          }
        }, delay,cacheTime);// 这里设定将延时每天固定执行
	}
	
}
