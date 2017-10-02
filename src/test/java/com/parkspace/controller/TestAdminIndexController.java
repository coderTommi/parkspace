package com.parkspace.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @Title: TestAdminIndexController.java
 * @Package com.parkspace.controller
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月1日 上午10:40:45</p>
*/

public class TestAdminIndexController extends TestBaseController{
	public String comid = "b6a0a6e7-f522-4c4f-9dc8-33897f8a6da4";
	@Test
	public void getAdminIndexSurvey() {
		try {
			mvc.perform(MockMvcRequestBuilders.get("/v1/admin/index/getadminindexsurvey"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getCommunitySurvey() {
		try {
			mvc.perform(MockMvcRequestBuilders.get("/v1/admin/index/getcommunitysurvey/"+comid))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
