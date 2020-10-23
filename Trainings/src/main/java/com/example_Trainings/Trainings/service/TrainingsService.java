package com.example_Trainings.Trainings.service;


import com.example_Trainings.Trainings.bean.Trainings;

import java.util.List;

public interface TrainingsService {
	public void createTrainings(Trainings trainings);
	public List<Trainings> getTrainings();
	public Trainings findById(long id);
	public List<Trainings> findByStatus(String status);
	public List<Trainings> findByStatusAndMentorId(String status,long mentorId);
	public Trainings update(Trainings trainings, long l);
	public void deleteTrainingsById(long id);
	public Trainings updatePartially(Trainings trainings, long id);
	//public List<Trainings> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy);
	
	/*public List<User> findByNameAndCountry(String name, String country);
	public List<User> findByName(String name);
	public List<User> Abc(String country);
	public List<Object[]> findXyz();*/
}
