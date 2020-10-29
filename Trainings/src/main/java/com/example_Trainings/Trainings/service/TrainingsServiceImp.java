package com.example_Trainings.Trainings.service;



import com.example_Trainings.Trainings.bean.Trainings;
import com.example_Trainings.Trainings.repository.TrainingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//@Transactional
public class TrainingsServiceImp implements TrainingsService {
	@Autowired
	TrainingsRepository trainingsRepository;

	//@Autowired
	//Technology1Repository repository;

	public List<Trainings> getAllTrainings(Integer pageNo, Integer pageSize, String sortBy)
	{
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<Trainings> pagedResult = trainingsRepository.findAll(paging);

		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<Trainings>();
		}
	}

	public void createTrainings(Trainings trainings) {
		// TODO Auto-generated method stub
		trainingsRepository.save(trainings);
	}

	public List<Trainings> getTrainings() {
		// TODO Auto-generated method stub
		return (List<Trainings>) trainingsRepository.findAll();
	}

	public Trainings findById(long id) {
		// TODO Auto-generated method stub
		return trainingsRepository.findById(id).get();
		//return null;
	}

	public Trainings update(Trainings trainings, long l) {
		// TODO Auto-generated method stub
		return trainingsRepository.save(trainings);
	}

	public void deleteTrainingsById(long id) {
		// TODO Auto-generated method stub
		trainingsRepository.deleteById(id);
	}

	public Trainings updatePartially(Trainings trainings, long id) {
		// TODO Auto-generated method stub
		Trainings tech = findById(id);
		tech.setProgress(trainings.getProgress());
		return trainingsRepository.save(tech);
	}
	public void updateAmount(long id,double amount){
		Trainings training = trainingsRepository.findById(id).get();
		training.setAmount_recieved(amount);
	}


	public List<Trainings> findByStatus(int status)
	{
		return trainingsRepository.findByStatus(status);
	}

	public List<Trainings> findByStatusAndMentorId(String status,long mentorId)
	{
		return trainingsRepository.findByStatusAndMentorId(status,mentorId);
	}


}
