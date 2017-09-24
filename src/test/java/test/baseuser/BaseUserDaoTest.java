package test.baseuser;

import java.sql.Timestamp;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.parkspace.db.rmdb.dao.BaseUserDao;
import com.parkspace.db.rmdb.entity.BaseUser;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml"})  

public class BaseUserDaoTest {
	private static Logger logger = LoggerFactory.getLogger(BaseUserDaoTest.class);  
	
	@Resource
	private BaseUserDao baseUserDao = null;
	@Test
	public void testsave(){
		BaseUser user = new BaseUser();
		user.setUserId(UUID.randomUUID().toString());
		user.setUserName("test01");
		user.setNickName("test01_nickName");
		user.setUserPwd("123456a?");
		user.setTelePhone("15300201276");
		user.setIsAdmin(0);
		user.setIdType(0);
		user.setIdNo("370714198211240087");
		user.setState(2);
		user.setWeixinAccount("123456");
		user.setAvator("admin");
		user.setMemo("test user");
		user.setCreateBy("admin");
		user.setCreateTime(new Timestamp(System.currentTimeMillis()));
		user.setModifyBy("admin");
		user.setModifyTime(new Timestamp(System.currentTimeMillis()));
		baseUserDao.save(user);
		System.out.println(user.getUserName());
	}
	
	@Test
	public void TestUpdate(){
		BaseUser newUser = new BaseUser();
		newUser.setUserId("321586e9-6f49-4900-8b51-a085526bfc23");
		newUser.setState(0);
		newUser.setMemo("test Modify");
		newUser.setModifyBy("admin2");
		newUser.setModifyTime(new Timestamp(System.currentTimeMillis()) );
		baseUserDao.update(newUser);
	}
	
	@Test
	public void getById(){
		String userId = "321586e9-6f49-4900-8b51-a085526bfc23";
		BaseUser user = baseUserDao.getById(userId);
		System.out.println(user.getUserName());
	}
	
	@Test
	public void delete(){
		String userId = "321586e9-6f49-4900-8b51-a085526bfc23";
		baseUserDao.delete(userId);
	}

}
