package com.parkspace.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkspace.db.rmdb.entity.ShareConfig;
import com.parkspace.service.IShareConfigService;

/**
 * @Title: TestShareConfigController.java
 * @Package com.parkspace.controller
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月1日 上午10:40:45</p>
*/

public class TestShareConfigController extends TestBaseController{
	@Resource
	private IShareConfigService shareConfigService;
	public String comid = "9475e801-4262-4d91-8f9b-73d8b3b729d8";
	@Test
	public void getAllShareConfig() {
		try {
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/shareconfig/getallshareconfig/1")
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
	public void disableShareConfig() {
		try {
			mvc.perform(MockMvcRequestBuilders.post("/v1/shareconfig/disableshareconfig/"+comid))
			.andDo(print())
			.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	public void enableShareConfig() {
		try {
			mvc.perform(MockMvcRequestBuilders.post("/v1/shareconfig/enableshareconfig/"+comid))
			.andDo(print())
			.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteShareConfig() {
		try {
			mvc.perform(MockMvcRequestBuilders.post("/v1/shareconfig/deleteshareconfig/"+comid))
			.andDo(print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn().getResponse().getContentAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addShareConfig() {
		ShareConfig shareConfig = new ShareConfig();
		
		shareConfig.setIsAllDay(0);
		shareConfig.setStartTime("13:03:59");
		shareConfig.setEndTime("18:10:59");
		
		shareConfig.setInternalDate("1,2,3,7");
//		shareConfig.setInternalDate("1,2,3");
		
		//共享类型：1周期性时间段，0自定义时间段
		shareConfig.setShareType(1);
		shareConfig.setSpaceno("1");
		
		ObjectMapper mapper = new ObjectMapper(); 
		
		try {
			String requestJson = mapper.writeValueAsString(shareConfig);
			System.out.println("input:"+requestJson);
			String responseString = mvc.perform(MockMvcRequestBuilders.post("/v1/shareconfig/addshareconfig")
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
	public void updateShareConfig() {
		
		ShareConfig shareConfig = this.shareConfigService.getShareConfig(comid);
		
		ObjectMapper mapper = new ObjectMapper(); 
		
		try {
			String requestJson = mapper.writeValueAsString(shareConfig); 
			String responseString = mvc.perform(MockMvcRequestBuilders.post("/v1/shareconfig/updateshareconfig")
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
	public void getShareConfig() {
		try {
			mvc.perform(MockMvcRequestBuilders.get("/v1/shareconfig/getshareconfig/"+comid))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
