package com.example.demo_search.service;


import com.example.demo_search.bean.MentorSkills;

import java.util.ArrayList;
import java.util.List;

public interface MentorSkillsService {
	public void createMentorSkills(MentorSkills mentorSkills);
	public List<MentorSkills> getMentorSkills();
	public MentorSkills findById(long id);
	public List <MentorSkills> findByMid(long mid);
	public ArrayList <MentorSkills> findBySid(long sid);
	public MentorSkills update(MentorSkills mentorSkills, long l);
	public void deleteMentorSkillsById(long id);
	public MentorSkills updatePartially(MentorSkills mentorSkills, long id);
	
	/*public List<User> findByNameAndCountry(String name, String country);
	public List<User> findByName(String name);
	public List<User> Abc(String country);
	public List<Object[]> findXyz();*/
}
