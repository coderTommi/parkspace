package test.baseuser.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.parkspace.db.rmdb.dao.WalletDao;
import com.parkspace.db.rmdb.entity.Wallet;
import com.parkspace.db.rmdb.entity.WalletOperation;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml"})  

public class WalletDaoTest {
	private static Logger logger = LoggerFactory.getLogger(WalletDaoTest.class);  
	
	@Resource
	private WalletDao walletDao = null;
	@Test
	public void testsave(){
		Wallet wallet = new Wallet();
		wallet.setUserId("123");
		wallet.setBalance(new BigDecimal(100));
		wallet.setBonus(new BigDecimal(10));
		wallet.setOpenTime(new Timestamp(System.currentTimeMillis()));
		wallet.setPledge(new BigDecimal("300.00"));
		wallet.setUnclosedAmt(new BigDecimal("10.00"));
		wallet.setLastTrsTime(new Timestamp(System.currentTimeMillis()));
		walletDao.save(wallet);
	}
	
	
	@Test
	public void getByUserId(){
		Wallet wallet = walletDao.getByUserId("123");
		System.out.println(wallet.getBalance());
	}
	@Test
	public void update(){
		Wallet wallet = new Wallet();
		wallet.setUserId("123");
		wallet.setBalance(new BigDecimal(200));
		wallet.setBonus(new BigDecimal(20));
		wallet.setOpenTime(new Timestamp(System.currentTimeMillis()));
		wallet.setPledge(new BigDecimal("600.00"));
		wallet.setUnclosedAmt(new BigDecimal("20.00"));
		wallet.setLastTrsTime(new Timestamp(System.currentTimeMillis()));
		walletDao.update(wallet);
	}
	
	@Test
	public void updateByBill() {
		WalletOperation obj = new WalletOperation();
		obj.setUserId("f55dc148-5abf-4af3-a0f6-535c6cdebf4d");
		obj.setBalance(new BigDecimal("1").negate());
		int count = walletDao.updateByBill(obj);
		System.out.println(count);
	}
	
	public static void main(String[] args) {
		System.out.println(new BigDecimal("10").negate());
	}
	

}
 