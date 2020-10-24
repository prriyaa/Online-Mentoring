package com.example.demo_search.repository;

import com.example.demo_search.bean.Mentor;
import com.example.demo_search.bean.MentorSkills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface MentorSkillsRepository extends JpaRepository<MentorSkills, Long>
{
    List<MentorSkills> findByMid(long mid);
    ArrayList<MentorSkills> findBySid(long sid);
	/*List<User> findByNameAndCountry(String name, String country);
	List<User> findByName(String name);

	@Query(value="select U from User U where U.country=?1")
    List<User> findAbc(String country);

    @Query(value="select U.country as country, U.name as name from user_info U", nativeQuery=true)
    List<Map<String, Object>> findMno();

    @Query(value="select U.country as country, U.name as name from user_info U", nativeQuery=true)
    List<Object[]> findXyz();*/
}
