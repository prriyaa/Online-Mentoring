package com.example.demo_search.service;

import com.example.demo_search.bean.MentorCalender;
import com.example.demo_search.repository.MentorCalenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional
public class MentorCalenderServiceImp implements MentorCalenderService {
	@Autowired
	MentorCalenderRepository mentorCalenderRepository;

	public void createMentorCalender(MentorCalender mentorCalender) {
		// TODO Auto-generated method stub
		mentorCalenderRepository.save(mentorCalender);
	}

	public List<MentorCalender> getMentorCalender() {
		// TODO Auto-generated method stub
		return (List<MentorCalender>) mentorCalenderRepository.findAll();
	}

	public MentorCalender findById(long id) {
		// TODO Auto-generated method stub
		return mentorCalenderRepository.findById(id).get();
		//return null;
	}

	public MentorCalender update(MentorCalender mentorCalender, long l) {
		// TODO Auto-generated method stub
		return mentorCalenderRepository.save(mentorCalender);
	}

	public void deleteMentorCalenderById(long id) {
		// TODO Auto-generated method stub
		mentorCalenderRepository.deleteById(id);
	}

	public MentorCalender updatePartially(MentorCalender mentorCalender, long id) {
		// TODO Auto-generated method stub
		MentorCalender usr = findById(id);
		usr.setMid(mentorCalender.getMid());
		return mentorCalenderRepository.save(usr);
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
