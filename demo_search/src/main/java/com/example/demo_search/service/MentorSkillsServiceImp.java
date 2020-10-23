package com.example.demo_search.service;

import com.example.demo_search.bean.MentorSkills;
import com.example.demo_search.repository.MentorSkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//@Transactional
public class MentorSkillsServiceImp implements MentorSkillsService {
	@Autowired
	MentorSkillsRepository mentorSkillsRepository;

	public void createMentorSkills(MentorSkills mentorSkills) {
		// TODO Auto-generated method stub
		mentorSkillsRepository.save(mentorSkills);
	}

	public List<MentorSkills> getMentorSkills() {
		// TODO Auto-generated method stub
		return (List<MentorSkills>) mentorSkillsRepository.findAll();
	}

	public MentorSkills findById(long id) {
		// TODO Auto-generated method stub
		return mentorSkillsRepository.findById(id).get();
		//return null;
	}
    public List<MentorSkills> findByMid(long mid)
    {
        return mentorSkillsRepository.findByMid(mid);
    }

	public ArrayList<MentorSkills> findBySid(long sid)
	{
		return mentorSkillsRepository.findBySid(sid);
	}

	public MentorSkills update(MentorSkills mentorSkills, long l) {
		// TODO Auto-generated method stub
		return mentorSkillsRepository.save(mentorSkills);
	}

	public void deleteMentorSkillsById(long id) {
		// TODO Auto-generated method stub
		mentorSkillsRepository.deleteById(id);
	}

	public MentorSkills updatePartially(MentorSkills mentorSkills, long id) {
		// TODO Auto-generated method stub
		MentorSkills usr = findById(id);
		usr.setExp(mentorSkills.getExp());
		return mentorSkillsRepository.save(usr);
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
