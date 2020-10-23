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

	/*public List<Trainings> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy)
	{
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<Trainings> pagedResult = trainingsRepository.findAll(paging);

		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<Trainings>();
		}
	}*/

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

	public List<Trainings> findByStatus(String status)
	{
		return trainingsRepository.findByStatus(status);
	}

	public List<Trainings> findByStatusAndMentorId(String status,long mentorId)
	{
		return trainingsRepository.findByStatusAndMentorId(status,mentorId);
	}

	/*public List<User> findByNameAndCountry(String name, String country) {
		return userRepository.findByNameAndCountry(name, country);
	}
	public List<User> findByName(String name)
    {
        return userRepository.findByName(name);
    }
    public List<User> Abc(String country)
    {
        List<Map<String,Object>> lmso=userRepository.findMno();
       /* for(Map<String,Object> mso: lmso)
        {
            for(String key:mso.keySet())
            {
                System.out.println(key+"\t"+mso.get(key));
            }
        }*/
       // Approach 2
       /* for(Map<String,Object> mso: lmso)
        {
            mso.forEach((k,v)->System.out.println(k+"\t"+v));
        }
        return userRepository.findAbc("india");
    }
    public List<Object[]> findXyz()
    {
        List<Object[]> lmso = userRepository.findXyz();
        lmso.forEach(mso -> {for(Object ob : mso) System.out.println("------"+ ob + "--------");});
        return userRepository.findXyz();
    }*/
}
