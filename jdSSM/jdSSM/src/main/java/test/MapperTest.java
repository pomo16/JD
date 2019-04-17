package test;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssm.common.entity.LoginInfo;
import com.ssm.maintain.dao.ILoginInfoDao;
import com.ssm.maintain.dao.IProductDao;
import com.ssm.maintain.dao.IUserInfoDao;


/**
 * 测试dao层的工作
 * @author pomo
 * 推荐Spring的项目就可以使用Spring的单元测试，可以自动注入我们需要的组件
 * 1.导入SpringTest模块
 * 2.@ContextConfiguration指定Spring配置文件的位置
 * 3.直接autowired要使用的组件即可

*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring-config.xml"})
public class MapperTest {
	@Autowired
	IUserInfoDao iuserInfoDao;
	
	@Autowired
	ILoginInfoDao iloginInfoDao;
	
	@Autowired
	IProductDao iproductDao;
	
	@Autowired
	SqlSession sqlSession;
	
	/**
	 * 测试IUserInfoDao
	 */
	@Test
	public void testCRUD() {
//		IProductDao mapper = sqlSession.getMapper(IProductDao.class);
//		for(int i = 0;i<20;i++) {
//			String uid = UUID.randomUUID().toString().substring(0,5) + i;
//			Product product = new Product();
//			product.setProductCode(uid);
//			product.setProductName(uid);
//			product.setPrice(i);
//			product.setPlace("gd");
//			product.setWeight(i+1);
//			product.setDescription(uid);
//			product.setPicture(uid);
//			mapper.insert(product);
//		}
		ILoginInfoDao mapper = sqlSession.getMapper(ILoginInfoDao.class);
//		int[] a = new int[] {9, 10};
//		System.out.println(mapper.deleteloginByIds(a));
//		
		for(int i = 0;i < 20;i++) {
			LoginInfo login = new LoginInfo();
			login.setUserId(11);
			login.setLoginTime(new Timestamp(new Date().getTime()));
			login.setLoginIp("192.168.1.1");
			mapper.insert(login);
		}
		
		//System.out.println(mapper.getAll());
		
		//批量添加用户
//		for(int i = 0;i < 1900;i++) {
//			String uid = UUID.randomUUID().toString().substring(0,5) + i;
//			
//			String password = "test123456";
//			
//			UserInfo user = new UserInfo();
//			user.setUserName(uid);
//			user.setPassword(password);
//			user.setPasswordMD5(MD5Encrypt.encryptByMD5(password));
//			user.setPhone("13143378602");
//			
//			mapper.insert(user);
//		}
//		System.out.println("批量完成！");
	}
}
