package com.example.demo_search.service;

import com.example.demo_search.bean.MentorCalender;

import java.util.List;

public interface MentorCalenderService {
	public void createMentorCalender(MentorCalender mentorCalender);
	public List<MentorCalender> getMentorCalender();
	public MentorCalender findById(long id);
	public MentorCalender update(MentorCalender mentorCalender, long l);
	public void deleteMentorCalenderById(long id);
	public MentorCalender updatePartially(MentorCalender mentorCalender, long id);
	
	/*public List<User> findByNameAndCountry(String name, String country);
	public List<User> findByName(String name);
	public List<User> Abc(String country);
	public List<Object[]> findXyz();*/
}
