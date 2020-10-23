package com.example.demo_search.service;

import com.example.demo_search.bean.Mentor;

import java.util.List;

public interface MentorService {
	public void createMentor(Mentor mentor);
	public List<Mentor> getMentor();
	public Mentor findById(long id);
	public Mentor update(Mentor mentor, long l);
	public void deleteMentorById(long id);
	public Mentor updatePartially(Mentor mentor, long id);
	
	/*public List<User> findByNameAndCountry(String name, String country);
	public List<User> findByName(String name);
	public List<User> Abc(String country);
	public List<Object[]> findXyz();*/
}
