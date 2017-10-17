package com.parkspace.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkspace.db.rmdb.entity.Zone;
import com.parkspace.service.IZoneService;

/**
 * @Title: TestZoneController.java
 * @Package com.parkspace.controller
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月1日 上午10:40:45</p>
*/

public class TestZoneController extends TestBaseController{
	@Resource
	private IZoneService zoneService;
	public String zoneid = "59f0d481-e78e-4e50-a918-78345194f0df";
	@Test
	public void getAllZone() {
		Zone zone = new Zone();
		zone.setCity("济南1");
		zone.setMemo("测试区域添加1");
		zone.setProvince("山东省1");
//		zone.setZone("天桥区1");
//		zone.setZonename("天桥行政区1");
		
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			String requestJson = mapper.writeValueAsString(zone); 
			System.out.println("入参："+requestJson);
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/zone/getallzone")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestJson)
					.param("page", "1")
					.param("pageSize", "1"))
			.andExpect(MockMvcResultMatchers.status().isOk())    //返回的状态是200  
			.andDo(print())         //打印出请求和相应的内容  
			.andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串  
			
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteZone() {
		try {
			mvc.perform(MockMvcRequestBuilders.post("/v1/zone/deletezone/"+zoneid))
			.andDo(print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn().getResponse().getContentAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addZone() {
		Zone zone = new Zone();
		zone.setCity("济南1");
		zone.setMemo("测试区域添加1");
		zone.setProvince("山东省1");
		zone.setZone("天桥区1");
		zone.setZonename("天桥行政区1");
		
		ObjectMapper mapper = new ObjectMapper(); 
		
		try {
			String requestJson = mapper.writeValueAsString(zone); 
			System.out.println("入参:"+requestJson);
			String responseString = mvc.perform(MockMvcRequestBuilders.post("/v1/zone/addzone")
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
	public void updateZone() {
		
		Zone zone = zoneService.getZone(zoneid);
		zone.setMemo("测试更改端到端");
		
		ObjectMapper mapper = new ObjectMapper(); 
		
		try {
			String requestJson = mapper.writeValueAsString(zone); 
			System.out.println("入参"+requestJson);
			String responseString = mvc.perform(MockMvcRequestBuilders.post("/v1/zone/updatezone")
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
	public void getZone() {
		try {
			mvc.perform(MockMvcRequestBuilders.get("/v1/zone/getzone/"+zoneid))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(print())
			.andReturn().getResponse().getContentAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
