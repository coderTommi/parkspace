package com.parkspace.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkspace.db.rmdb.entity.Community;
import com.parkspace.db.rmdb.entity.ParkingSpace;
import com.parkspace.db.rmdb.entity.Zone;

/**
 * @Title: TestParkingSpaceController.java
 * @Package com.parkspace.controller
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月2日 下午5:15:57</p>
*/

public class TestParkingSpaceController extends TestBaseController{

	public String zoneid = "4e73503c-7052-41bc-a716-b8b2d2e32e5e";
	
	public String comid = "d2ac2ef6-acad-411a-9b2a-9732d47028b5";
	
	public String userId = "1";
	
	public String spaceno = "1";
	
	public String orderJnlNo = "ffb6a8a1-3de1-4d95-a2aa-e8b67e8ab063";
	
	@Test
	public void getAllParkingSpace() {
		ParkingSpace parkingSpace = new ParkingSpace();
		Zone zone = new Zone();
		zone.setZoneid(zoneid);
		parkingSpace.setZone(zone);

		Community community = new Community();
		community.setComidQuery(new String[] {comid});
		parkingSpace.setCommunity(community);
		
		parkingSpace.setSpacenoLikeQuery(spaceno);
		
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			String requestJson = mapper.writeValueAsString(parkingSpace); 
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/parkingspace/getallparkingspace")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestJson)
					.param("page", "1")
					.param("pageSize", "10"))
			.andExpect(MockMvcResultMatchers.status().isOk())    //返回的状态是200  
			.andDo(print())         //打印出请求和相应的内容  
			.andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串  
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getParkingSpaceSurvey() {
		ParkingSpace parkingSpace = new ParkingSpace();
//		Zone zone = new Zone();
//		zone.setZoneid(zoneid);
//		parkingSpace.setZone(zone);
//
//		Community community = new Community();
//		community.setComidQuery(new String[] {comid});
//		parkingSpace.setCommunity(community);
//		
//		parkingSpace.setSpacenoLikeQuery(spaceno);
		
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			String requestJson = mapper.writeValueAsString(parkingSpace); 
			mvc.perform(MockMvcRequestBuilders.get("/v1/parkingspace/getparkingspacesurvey")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestJson))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getParkingSpace() {
		try {
			mvc.perform(MockMvcRequestBuilders.get("/v1/parkingspace/getparkingspace/"+spaceno))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getEnableParkingSpace() {
		try {
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/parkingspace/getenableparkingspace")
					.param("page", "1")
					.param("pageSize", "10")
					.param("comid", comid)
					.param("parkHours", "2")
					)
			.andExpect(MockMvcResultMatchers.status().isOk())    //返回的状态是200  
			.andDo(print())         //打印出请求和相应的内容  
			.andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串  
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void orderParkingSpace() {
		try {
			String json = mvc.perform(MockMvcRequestBuilders.post("/v1/parkingspace/orderparkingspace")
					.param("spaceno", spaceno)
					.param("parkHours", "3")
					.param("userId", userId)
					.param("carno", "aaa")
					.param("unitPrice", "2")
					)
			.andExpect(MockMvcResultMatchers.status().isOk())    //返回的状态是200  
			.andDo(print())         //打印出请求和相应的内容  
			.andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串  
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getParkingSpaceParkHoursBySpaceno() {
		try {
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/parkingspace/getparkingspaceparkhoursbyspaceno")
					.param("spaceno", spaceno)
					)
			.andExpect(MockMvcResultMatchers.status().isOk())    //返回的状态是200  
			.andDo(print())         //打印出请求和相应的内容  
			.andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串  
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void cancelOrderParkingSpace() {
		try {
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/parkingspace/cancelorderparkingspace")
					.param("orderJnlNo", orderJnlNo)
					)
			.andExpect(MockMvcResultMatchers.status().isOk())    //返回的状态是200  
			.andDo(print())         //打印出请求和相应的内容  
			.andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串  
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDate() throws Exception {
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date createTime = simpleFormat.parse("2017-10-03 16:00:31");
		Date currentTime = new Date();
		long longActualParkHours = currentTime.getTime() - createTime.getTime();
		double actualParkHoursTemp = (double)longActualParkHours/1000/60/60;
		BigDecimal actualParkHours = new BigDecimal(actualParkHoursTemp).setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println(actualParkHoursTemp);
		System.out.println(longActualParkHours);
		System.out.println(actualParkHours);
	}
	
	
	@Test
	public void confirmOrderParkingSpace() {
		try {
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/parkingspace/confirmorderparkingspace")
					.param("orderJnlNo", orderJnlNo)
					)
			.andExpect(MockMvcResultMatchers.status().isOk())    //返回的状态是200  
			.andDo(print())         //打印出请求和相应的内容  
			.andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串  
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getParkingSpaceBill() {
		try {
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/parkingspace/getparkingspacebill")
					.param("orderJnlNo", orderJnlNo)
					)
			.andExpect(MockMvcResultMatchers.status().isOk())    //返回的状态是200  
			.andDo(print())         //打印出请求和相应的内容  
			.andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串  
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void delayOrderParkingSpace() {
		try {
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/parkingspace/delayorderparkingspace")
					.param("orderJnlNo", orderJnlNo)
					.param("delayParkHours", "1")
					)
			.andExpect(MockMvcResultMatchers.status().isOk())    //返回的状态是200  
			.andDo(print())         //打印出请求和相应的内容  
			.andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串  
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void payOrderParkingSpace() {
		try {
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/parkingspace/payorderparkingspace")
					.param("orderJnlNo", orderJnlNo)
					)
			.andExpect(MockMvcResultMatchers.status().isOk())    //返回的状态是200  
			.andDo(print())         //打印出请求和相应的内容  
			.andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串  
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getParkingSpaceUsedHistory() {
		try {
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/parkingspace/getparkingspaceusedhistory")
					.param("page", "1")
					.param("pageSize", "10")
					.param("spaceno", spaceno)
					)
			.andExpect(MockMvcResultMatchers.status().isOk())    //返回的状态是200  
			.andDo(print())         //打印出请求和相应的内容  
			.andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串  
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getParkingSpaceUsing() {
		try {
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/parkingspace/getparkingspaceusing")
					.param("page", "1")
					.param("pageSize", "10")
					.param("spaceno", spaceno)
					)
			.andExpect(MockMvcResultMatchers.status().isOk())    //返回的状态是200  
			.andDo(print())         //打印出请求和相应的内容  
			.andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串  
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getParkingSpaceSoonExpire() {
		try {
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/parkingspace/getparkingspacesoonexpire")
					.param("page", "1")
					.param("pageSize", "10")
					.param("spaceno", spaceno)
					)
			.andExpect(MockMvcResultMatchers.status().isOk())    //返回的状态是200  
			.andDo(print())         //打印出请求和相应的内容  
			.andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串  
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
