package com.example.demo_search.repository;

import com.example.demo_search.bean.Mentor;
import com.example.demo_search.bean.MentorCalender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorCalenderRepository extends JpaRepository<MentorCalender, Long>
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
