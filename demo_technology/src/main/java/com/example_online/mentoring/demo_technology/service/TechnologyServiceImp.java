package com.example_online.mentoring.demo_technology.service;


import com.example_online.mentoring.demo_technology.bean.Technology;
import com.example_online.mentoring.demo_technology.repository.TechnologyRepository;
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
public class TechnologyServiceImp implements TechnologyService {
	@Autowired
	TechnologyRepository technologyRepository;

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
		technologyRepository.save(technology);
	}

	public List<Technology> getTechnology() {
		// TODO Auto-generated method stub
		return (List<Technology>) technologyRepository.findAll();
	}

	public Technology findById(long id) {
		// TODO Auto-generated method stub
		return technologyRepository.findById(id).get();
		//return null;
	}

	public Technology update(Technology technology, long l) {
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
