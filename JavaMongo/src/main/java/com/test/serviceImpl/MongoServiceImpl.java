package com.test.serviceImpl;

import com.test.bean.User;
import com.test.dao.MongoDao;
import com.test.service.MongoService;
import com.test.service.UserService;
import com.test.util.MD5Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoServiceImpl implements MongoService {
	
	@Autowired
	private MongoDao mongodb;
	@Autowired
	private UserService userService;

	public void add(User user) {
		try {
			//新增判断，存在该用户则判断，不存在择新增
			System.out.print(user.getUserId());
			if(StringUtils.isBlank(user.getUserId())){
					user.setUserId(MD5Utils.md5(user.getName()+user.getAge()));
				//复用login接口 加密问题
				User user2 = new User();
				BeanUtils.copyProperties(user2,user);
				user2.setPassword(MD5Utils.md5(user2.getPassword()));
					mongodb.add(user2);
					userService.login(user);
					System.out.print("执行新增方法 ："+user);
			}else {
				mongodb.updateUser(user);
				System.out.print("执行更新方法 ：" + user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<User> queryUser(int currentPage, int pageSize) {
		List<User> users = mongodb.queryUser(currentPage,pageSize);
		if (users!=null){
			return users;
		}
		return null;
	}

	public void remove(String userId) {
		mongodb.remove(userId);
	}

	public User checkname(String name) {
		List<User> list = mongodb.checkname(name);
		if(list!=null){
			return list.get(0);
		}
		return null;
	}

	public int queryUserCount() {
		return mongodb.queryUserCount();
	}

	@Override
	public int checkname2(String name) {
		return mongodb.checkname2(name);
	}
	@Value("${HTTP_PHOTO_URL}")
	private String HTTP_PHOTO_URL;

}
