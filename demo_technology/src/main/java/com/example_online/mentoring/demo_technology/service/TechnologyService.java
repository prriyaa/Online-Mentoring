package com.example_online.mentoring.demo_technology.service;


import com.example_online.mentoring.demo_technology.bean.Technology;

import java.util.List;

public interface TechnologyService {
	public void createTechnology(Technology technology);
	public List<Technology> getTechnology();
	public Technology findById(long id);
	public Technology update(Technology technology, long l);
	public void deleteTechnologyById(long id);
	public Technology updatePartially(Technology technology, long id);
	public List<Technology> getAllTechnology(Integer pageNo, Integer pageSize, String sortBy);
    List<Technology> findByName(String name);
	//public Technology getSkill(long id);
	
	/*public List<User> findByNameAndCountry(String name, String country);
	public List<User> findByName(String name);
	public List<User> Abc(String country);
	public List<Object[]> findXyz();*/
}
