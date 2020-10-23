package com.example.demo_user.service;


import com.example.demo_user.bean.User;
import com.example.demo_user.repository.UsersRepository;
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
public class UsersServiceImp implements UsersService {
	@Autowired
	UsersRepository usersRepository;

	//@Autowired
	//Technology1Repository repository;

	public List<User> getAllUser(Integer pageNo, Integer pageSize, String sortBy)
	{
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<User> pagedResult = usersRepository.findAll(paging);

		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<User>();
		}
	}

	public void createUser(User users) {
		// TODO Auto-generated method stub
		usersRepository.save(users);
	}

	public List<User> getUser() {
		// TODO Auto-generated method stub
		return (List<User>) usersRepository.findAll();
	}

	public User findById(long id) {
		// TODO Auto-generated method stub
		return usersRepository.findById(id).get();
		//return null;
	}

	public User update(User users, long l) {
		// TODO Auto-generated method stub
		return usersRepository.save(users);
	}

	public void deleteUserById(long id) {
		// TODO Auto-generated method stub
		usersRepository.deleteById(id);
	}

	public User updatePartially(User users, long id) {
		// TODO Auto-generated method stub
		User user = findById(id);
		user.setLastname(user.getLastname());
		return usersRepository.save(user);
	}
    /*public List<Technology> findByName(String name)
    {
        return technologyRepository.findByName(name);
    }*/


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
