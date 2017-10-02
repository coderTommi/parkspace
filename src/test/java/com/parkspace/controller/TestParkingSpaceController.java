package com.parkspace.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
}
