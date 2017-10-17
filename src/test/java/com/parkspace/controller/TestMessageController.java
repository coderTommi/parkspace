package com.parkspace.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkspace.db.rmdb.entity.Message;

/**
 * @Title: TestMessageController.java
 * @Package com.parkspace.controller
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月7日 下午8:32:07</p>
*/

public class TestMessageController extends TestBaseController{
	public Calendar cal = Calendar.getInstance();
	public String UUID = "7cd4952a-65b8-4000-a4c6-3bf965b32b35";
	@Test
	public void addMessage() {
		Message message = new Message();
		message.setComid("");
		message.setContext("测试消息内容体"+new Date());
		cal.add(Calendar.DATE, 2);
		message.setEnableEndTime(cal.getTime());
		message.setEnableStartTime(new Date());
		message.setMemo("消息备注"+new Date());
		message.setMessageObject(2);
		message.setStatus(0);//草稿
		
		ObjectMapper mapper = new ObjectMapper(); 
		
		try {
			String requestJson = mapper.writeValueAsString(message); 
			System.out.println("input:"+requestJson);
			String responseString = mvc.perform(MockMvcRequestBuilders.post("/v1/message/addmessage")
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
	public void deleteMessage() {
		try {
			String responseString = mvc.perform(MockMvcRequestBuilders.post("/v1/message/deletemessage/"+UUID)
					)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(print())
			.andReturn().getResponse().getContentAsString();
			System.out.println(responseString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getMessage() {
		try {
			String responseString = mvc.perform(MockMvcRequestBuilders.post("/v1/message/getmessage/"+UUID)
					)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(print())
			.andReturn().getResponse().getContentAsString();
			System.out.println(responseString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getAllMessage() {
		Message message = new Message();
		message.setComid("1");
		message.setStatusQuery(new Integer[] {1,2});
		message.setMessageObjectQuery(new Integer[] {0});
		
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			String requestJson = mapper.writeValueAsString(message); 
			System.out.println("input:"+requestJson);
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/message/getallmessage")
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

}
