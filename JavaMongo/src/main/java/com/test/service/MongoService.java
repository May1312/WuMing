package com.test.service;

import com.test.bean.User;

import java.util.List;

public interface MongoService {

	public void add(User user);

	public List<User> queryUser(int currentPage, int pageSize);

	public void remove(String userId);

	public User checkname(String name);

	public int queryUserCount();

	int checkname2(String name);
}
