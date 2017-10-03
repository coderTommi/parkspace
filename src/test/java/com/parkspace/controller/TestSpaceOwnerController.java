package com.parkspace.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkspace.db.rmdb.entity.BlackList;
import com.parkspace.db.rmdb.entity.Community;
import com.parkspace.db.rmdb.entity.SpaceOwner;
import com.parkspace.db.rmdb.entity.Zone;

/**
 * @Title: TestSpaceOwnerController.java
 * @Package com.parkspace.controller
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月2日 上午11:23:05</p>
*/

public class TestSpaceOwnerController  extends TestBaseController{
	public String zoneid = "4e73503c-7052-41bc-a716-b8b2d2e32e5e";
	
	public String comid = "d2ac2ef6-acad-411a-9b2a-9732d47028b5";
	
	public String userId = "1";
	
	@Test
	public void getAllSpaceOwner() {
		SpaceOwner spaceOwner = new SpaceOwner();
		Zone zone = new Zone();
		zone.setZoneid(zoneid);
		spaceOwner.setZone(zone);
		
		Community community = new Community();
		community.setComidQuery(new String[] {comid});
		spaceOwner.setCommunity(community);
		
		spaceOwner.setUserName("孙");
		spaceOwner.setTelePhone("15300201276");
		spaceOwner.setSpaceno("1");
		spaceOwner.setCarno("a");
		
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			String requestJson = mapper.writeValueAsString(spaceOwner); 
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/spaceowner/getallspaceowner")
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
	public void addBlackList() {
		BlackList blackList = new BlackList();
		blackList.setUserId(userId);
		blackList.setMemo("测试把用户添加到黑名单中--业主");
		
		ObjectMapper mapper = new ObjectMapper(); 
		
		try {
			String requestJson = mapper.writeValueAsString(blackList); 
			String responseString = mvc.perform(MockMvcRequestBuilders.post("/v1/spaceowner/addblacklist")
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
	public void getBlackList() {
		BlackList blackList = new BlackList();
		
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			String requestJson = mapper.writeValueAsString(blackList); 
			String json = mvc.perform(MockMvcRequestBuilders.get("/v1/spaceowner/getblacklist")
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
