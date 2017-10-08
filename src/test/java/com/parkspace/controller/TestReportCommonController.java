package com.parkspace.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkspace.model.ReportCommonModel;

/**
 * @Title: TestReportCommonController.java
 * @Package com.parkspace.controller
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月8日 下午9:28:39</p>
*/

public class TestReportCommonController  extends TestBaseController{
	@Test
	public void getCommunityReport() {
		
		ReportCommonModel reportCommonModel = new ReportCommonModel();
		
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			String requestJson = mapper.writeValueAsString(reportCommonModel); 
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/report/getcommunityreport")
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
	public void getParkingSpaceBillHisReport() {
		
		ReportCommonModel reportCommonModel = new ReportCommonModel();
		
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			String requestJson = mapper.writeValueAsString(reportCommonModel); 
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/report/getparkingspacebillhisreport")
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
	public void getCertifiedParkingSpaceReport() {
		
		ReportCommonModel reportCommonModel = new ReportCommonModel();
		
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			String requestJson = mapper.writeValueAsString(reportCommonModel); 
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/report/getcertifiedparkingspacereport")
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
	public void getActivingParkingSpaceReport() {
		
		ReportCommonModel reportCommonModel = new ReportCommonModel();
		
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			String requestJson = mapper.writeValueAsString(reportCommonModel); 
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/report/getactivingparkingspacereport")
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
	public void getCertifiedUserReport() {
		
		ReportCommonModel reportCommonModel = new ReportCommonModel();
		
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			String requestJson = mapper.writeValueAsString(reportCommonModel); 
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/report/getcertifieduserreport")
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
	public void getActivingUserReport() {
		
		ReportCommonModel reportCommonModel = new ReportCommonModel();
		
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			String requestJson = mapper.writeValueAsString(reportCommonModel); 
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/report/getactivinguserreport")
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
}
