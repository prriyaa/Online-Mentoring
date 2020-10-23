package com.example_online.mentoring.demo_technology.repository;

import com.example_online.mentoring.demo_technology.bean.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public interface TechnologyRepository extends JpaRepository<Technology, Long>
{
	//List<Technology> findByNameAndToc(String name, String toc);
	List<Technology> findByName(String name);
/*
	@Query(value="select T from Technology T where T.country=?1")
    List<Technology> findAbc(String toc);*/

   /* @Query(value="select U.country as country, U.name as name from user_info U", nativeQuery=true)
    List<Map<String, Object>> findMno();

    @Query(value="select U.country as country, U.name as name from user_info U", nativeQuery=true)
    List<Object[]> findXyz();*/
}
