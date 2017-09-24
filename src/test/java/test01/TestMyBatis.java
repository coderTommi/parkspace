package test01;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.parkspace.db.rmdb.entity.BaseUser;
import com.parkspace.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml"})  

public class TestMyBatis {
	private static Logger logger = LoggerFactory.getLogger(TestMyBatis.class);  
	
	@Resource
	private IUserService userService = null;
	@Test
	public void test1(){
		BaseUser user = userService.getUser(1);
		System.out.println(user.getUserName());
	}

}
