package com.example.demo_search.service;

import com.example.demo_search.bean.Mentor;
import com.example.demo_search.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional
public class MentorServiceImp implements MentorService {
	@Autowired
	MentorRepository mentorRepository;

	public void createMentor(Mentor mentor) {
		// TODO Auto-generated method stub
		mentorRepository.save(mentor);
	}

	public List<Mentor> getMentor() {
		// TODO Auto-generated method stub
		return (List<Mentor>) mentorRepository.findAll();
	}

	public Mentor findById(long id) {
		// TODO Auto-generated method stub
		return mentorRepository.findById(id).get();
		//return null;
	}

	public Mentor update(Mentor mentor, long l) {
		// TODO Auto-generated method stub
		return mentorRepository.save(mentor);
	}

	public void deleteMentorById(long id) {
		// TODO Auto-generated method stub
		mentorRepository.deleteById(id);
	}

	public Mentor updatePartially(Mentor mentor, long id) {
		// TODO Auto-generated method stub
		Mentor usr = findById(id);
		usr.setUsername(mentor.getUsername());
		return mentorRepository.save(usr);
	}


	/*public List<Mentor> findByNameAndCountry(String name, String country) {
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
        /*for(Map<String,Object> mso: lmso)
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
