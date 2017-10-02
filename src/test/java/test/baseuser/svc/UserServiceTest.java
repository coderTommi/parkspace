package test.baseuser.svc;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.parkspace.common.exception.ParkspaceServiceException;
import com.parkspace.controller.pojo.RegisterUserWapper;
import com.parkspace.db.rmdb.entity.BaseUser;
import com.parkspace.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/dispatcher-servlet.xml"})  
public class UserServiceTest {
	
	private static Logger logger = LoggerFactory.getLogger(UserServiceTest.class);  
	
	@Resource
	private IUserService userService;
	@Test
	public void testRegister() throws ParkspaceServiceException, Exception {
		RegisterUserWapper user = new RegisterUserWapper();
		user.setUserName("lidongliang");
		user.setRealName("李栋梁");
		user.setTelePhone("15300201276");
		user.setWeixinAccount("123456");
		userService.registerUser(user);
	}

}
