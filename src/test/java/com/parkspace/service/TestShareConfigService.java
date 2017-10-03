package com.parkspace.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.parkspace.db.rmdb.entity.ShareConfig;

/**
 * @Title: TestShareConfigService.java
 * @Package com.parkspace.service
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年9月22日 下午10:15:01</p>
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/dispatcher-servlet.xml"}) 
public class TestShareConfigService {
	@Resource
	private IShareConfigService shareConfigService;
	
	private String spaceno = "1";
	
	public String userId = "1";
	
	private String UUID = "1d93e91d-ae0b-4c5f-b0cc-0e6e711357ff";
	
	@Test
	public void addShareConfigCycle() {//增加周期共享时间
		ShareConfig shareConfig = new ShareConfig();
		
		shareConfig.setIsAllDay(0);
		shareConfig.setStartTime("18:10:59");
		shareConfig.setEndTime("13:03:59");
		
		shareConfig.setInternalDate("1,2,3,7");
//		shareConfig.setInternalDate("1,2,3");
		
		shareConfig.setCreateBy("孙创建");
		shareConfig.setModifyBy("孙编辑");
		//共享类型：1周期性时间段，0自定义时间段
		shareConfig.setShareType(1);
		shareConfig.setSpaceno(spaceno);
		
		this.shareConfigService.addShareConfig(shareConfig);
	}
	
	@Test
	public void addShareConfigDefinition() {//增加周期共享时间
		ShareConfig shareConfig = new ShareConfig();
		shareConfig.setIsAllDay(0);
		//共享类型：1周期性时间段，0自定义时间段
		shareConfig.setShareType(0);
		
		shareConfig.setSpaceno(spaceno);
		shareConfig.setStartDate("2017-09-10");
		shareConfig.setStartTime("18:10:59");
		shareConfig.setEndDate("2017-10-11");
		shareConfig.setEndTime("13:03:59");
		shareConfig.setCreateBy("孙创建");
		shareConfig.setModifyBy("孙编辑");
		this.shareConfigService.addShareConfig(shareConfig);
	}
	
	@Test
	public void getShareConfig() {
		ShareConfig shareConfig = this.shareConfigService.getShareConfig(UUID);
		System.out.println(shareConfig);
	}
	
	@Test
	public void updateShareConfig() {
		ShareConfig shareConfig = this.shareConfigService.getShareConfig(UUID);
		this.shareConfigService.updateShareConfig(shareConfig);
		System.out.println(shareConfig);
	}
	
	@Test
	public void deleteShareConfig() {
		ShareConfig shareConfig = this.shareConfigService.getShareConfig(UUID);
		this.shareConfigService.deleteShareConfig(shareConfig);
		System.out.println(shareConfig);
	}
	
	@Test
	public void disableShareConfig() {
		this.shareConfigService.disableShareConfig(UUID, "孙禁用");
	}
	@Test
	public void enableShareConfig() {
		this.shareConfigService.enableShareConfig(UUID, "孙启用");
	}
	@Test
	public void getShareConfigList() {
		ShareConfig shareConfig = new ShareConfig();
		List<ShareConfig> list = this.shareConfigService.getShareConfigList(shareConfig);
		for(ShareConfig s : list) {
			System.out.println(s);
		}
	}
	
	@Test
	public void getShareConfigListBySpaceno() {
		List<ShareConfig> list = this.shareConfigService.getShareConfigListBySpaceno(spaceno);
		for(ShareConfig s : list) {
			System.out.println(s);
		}
	}
}
