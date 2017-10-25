package com.parkspace.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkspace.db.rmdb.entity.Community;
import com.parkspace.db.rmdb.entity.Zone;
import com.parkspace.service.ICommunityService;

/**
 * @Title: TestCommunityController.java
 * @Package com.parkspace.controller
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月1日 上午10:40:45</p>
*/

public class TestCommunityController extends TestBaseController{
	@Resource
	private ICommunityService communityService;
	public String zoneid = "4e73503c-7052-41bc-a716-b8b2d2e32e5e";
	public String comid = "647f452a-9569-44ea-a416-af6a557e43f8";
	@Test
	public void getAllCommunity() {
		Community community = new Community();
		Zone zone = new Zone();
		zone.setCity("济南");
		
		zone.setZoneid(zoneid);
		community.setComname("王府");
//		community.setZone(zone);
		
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			String requestJson = mapper.writeValueAsString(community); 
			System.out.println("入参："+requestJson);
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/community/getallcommunity")
					.param("page", "1")
					.param("pageSize", "1")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestJson)
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
	public void deleteCommunity() {
		try {
			mvc.perform(MockMvcRequestBuilders.post("/v1/community/deletecommunity/"+comid))
			.andDo(print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn().getResponse().getContentAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addCommunity() {
		
		Community community = new Community();
		community.setAddress("师范路金牛小区");
		community.setComname("金牛小区");
		community.setCreateBy("孙辽东创建1");
		//默认开通
		community.setMemo("测试添加小区1");
		community.setModifyBy("孙辽东编辑1");
		community.setPrice(new BigDecimal(10));
		community.setZoneid(zoneid);
		community.setPrice(new BigDecimal(2));//单价
		community.setFreeParkingMinutes(60);//免费一小时
		community.setMaxPriceAllDay(new BigDecimal(16));//一条最多16元
		community.setOvertimePrice(new BigDecimal(5));
		
		ObjectMapper mapper = new ObjectMapper(); 
		
		try {
			String requestJson = mapper.writeValueAsString(community); 
			System.out.println("input:"+requestJson);
			String responseString = mvc.perform(MockMvcRequestBuilders.post("/v1/community/addcommunity")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestJson))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(print())
			.andReturn().getResponse().getContentAsString();
			System.out.println(responseString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateCommunity() {
		
		Community community = this.communityService.getCommunity(comid);
		community.setMemo("测试更新小区信息");
		community.setFreeParkingMinutes(30);
		community.setMaxPriceAllDay(new BigDecimal(18));
		community.setOvertimePrice(new BigDecimal(5));
		
		ObjectMapper mapper = new ObjectMapper(); 
		
		try {
			String requestJson = mapper.writeValueAsString(community); 
			System.out.println("input:"+requestJson);
			String responseString = mvc.perform(MockMvcRequestBuilders.post("/v1/community/updatecommunity")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestJson))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(print())
			.andReturn().getResponse().getContentAsString();
			System.out.println(responseString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getCommunity() {
		try {
			mvc.perform(MockMvcRequestBuilders.get("/v1/community/getcommunity/"+comid))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(print())
			.andReturn().getResponse().getContentAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void getPropertyMgmtUser() {
		try {
			mvc.perform(MockMvcRequestBuilders.get("/v1/community/getpropertymgmtuser/"+comid))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(print())
			.andReturn().getResponse().getContentAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
