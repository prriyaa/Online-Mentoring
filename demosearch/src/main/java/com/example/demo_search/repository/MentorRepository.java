package com.example.demo_search.repository;

import com.example.demo_search.bean.Mentor;
import com.example.demo_search.bean.MentorCalender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public interface MentorRepository extends JpaRepository<Mentor, Long>
{
	/*List<User> findByNameAndCountry(String name, String country);
	List<User> findByName(String name);

	@Query(value="select U from User U where U.country=?1")
    List<User> findAbc(String country);

    @Query(value="select U.country as country, U.name as name from user_info U", nativeQuery=true)
    List<Map<String, Object>> findMno();

    @Query(value="select U.country as country, U.name as name from user_info U", nativeQuery=true)
    List<Object[]> findXyz();*/
}
