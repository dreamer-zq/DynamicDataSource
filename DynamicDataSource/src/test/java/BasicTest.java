import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qiang.dao.UserInfoDao;
import com.qiang.model.UserInfo;

/**
 * Created by zhiqiang on 2015/3/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class BasicTest {

	@Autowired
	private UserInfoDao userInfoDao;

	@Test
	public void testInsertUser() {
		UserInfo entity = new UserInfo();
		entity.setUsername("hhaha");
		userInfoDao.insert(entity);
	}

	@Test
	public void testFindAllUser() {
		List<UserInfo> list = userInfoDao.findAll();
		for (UserInfo user : list) {
			System.out.println(user.getUsername());
		}
	}
}
