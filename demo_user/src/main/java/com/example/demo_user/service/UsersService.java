package com.example.demo_user.service;


import com.example.demo_user.bean.User;

import java.util.List;

public interface UsersService {
	public void createUser(User user);
	public List<User> getUser();
	public User findById(long id);
	public User update(User user, long l);
	public void deleteUserById(long id);
	public User updatePartially(User user, long id);
	public List<User> getAllUser(Integer pageNo, Integer pageSize, String sortBy);
    //List<User> findByName(String name);
	//public Technology getSkill(long id);
	
	/*public List<User> findByNameAndCountry(String name, String country);
	public List<User> findByName(String name);
	public List<User> Abc(String country);
	public List<Object[]> findXyz();*/
}
