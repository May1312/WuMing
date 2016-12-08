package com.test.serviceImpl;

import com.test.bean.User;
import com.test.dao.MongoDao;
import com.test.service.MongoService;
import com.test.util.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoServiceImpl implements MongoService {
	
	@Autowired
	private MongoDao mongodab;

	public void add(User user) {
		try {
			//新增判断，存在该用户则判断，不存在择新增
			System.out.print(user.getUserId());
			if(StringUtils.isBlank(user.getUserId())){
					user.setUserId(MD5Utils.md5(user.getName()+user.getAge()));
					mongodab.add(user);
					System.out.print("执行新增方法 ："+user);
			}else {
				mongodab.updateUser(user);
				System.out.print("执行更新方法 ：" + user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<User> queryUser(int currentPage, int pageSize) {
		List<User> users = mongodab.queryUser(currentPage,pageSize);
		if (users!=null){
			return users;
		}
		return null;
	}

	public void remove(String userId) {
		mongodab.remove(userId);
	}

	public int checkname(String name) {
		return mongodab.checkname(name);
	}

	public int queryUserCount() {
		return mongodab.queryUserCount();
	}

}
