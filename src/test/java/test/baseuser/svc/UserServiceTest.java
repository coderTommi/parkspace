package test.baseuser.svc;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.parkspace.common.exception.PackspaceServiceException;
import com.parkspace.db.rmdb.entity.BaseUser;
import com.parkspace.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/dispatcher-servlet.xml"})  
public class UserServiceTest {
	
	private static Logger logger = LoggerFactory.getLogger(UserServiceTest.class);  
	
	@Resource
	private IUserService userService;
	@Test
	public void testRegister() throws PackspaceServiceException, Exception {
		BaseUser user = new BaseUser();
		user.setUserId(UUID.randomUUID().toString());
		user.setUserName("lidongliang");
		user.setNickName("lidongliang_nickName");
		user.setRealName("李栋梁");
		user.setUserPwd("123456a?");
		user.setTelePhone("15300201276");
		user.setWeixinAccount("123456");
		userService.registerUser(user);
	}

}
