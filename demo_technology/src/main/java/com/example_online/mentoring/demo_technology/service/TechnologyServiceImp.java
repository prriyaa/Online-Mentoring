package com.example_online.mentoring.demo_technology.service;


import com.example_online.mentoring.demo_technology.bean.Technology;
import com.example_online.mentoring.demo_technology.repository.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
//@Transactional
public class TechnologyServiceImp implements TechnologyService {
	@Autowired
	TechnologyRepository technologyRepository;

	private final Logger Log = LoggerFactory.getLogger(getClass());

	//@Autowired
	//Technology1Repository repository;

	public List<Technology> getAllTechnology(Integer pageNo, Integer pageSize, String sortBy)
	{
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<Technology> pagedResult = technologyRepository.findAll(paging);

		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<Technology>();
		}
	}

	public void createTechnology(Technology technology) {
		// TODO Auto-generated method stub
        Log.info("creating new technology");
		technologyRepository.save(technology);
	}

	public List<Technology> getTechnology() {
		// TODO Auto-generated method stub
        Log.info("displaying list of technoogy");
		return (List<Technology>) technologyRepository.findAll();
	}

	public Technology findById(long id) {
		// TODO Auto-generated method stub
        Log.info("return technology by id");
		return technologyRepository.findById(id).get();
		//return null;
	}

	public Technology update(Technology technology, long l) {
	    Log.info("updating technology");
		// TODO Auto-generated method stub
		return technologyRepository.save(technology);
	}

	public void deleteTechnologyById(long id) {
		// TODO Auto-generated method stub
		technologyRepository.deleteById(id);
	}

	public Technology updatePartially(Technology technology, long id) {
		// TODO Auto-generated method stub
		Technology tech = findById(id);
		tech.setToc(technology.getToc());
		return technologyRepository.save(tech);
	}
    public List<Technology> findByName(String name)
    {
        return technologyRepository.findByName(name);
    }

}
