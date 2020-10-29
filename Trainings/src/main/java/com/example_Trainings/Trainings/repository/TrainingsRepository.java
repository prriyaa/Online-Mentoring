package com.example_Trainings.Trainings.repository;

import com.example_Trainings.Trainings.bean.Trainings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainingsRepository extends JpaRepository<Trainings, Long>
{
	List<Trainings> findByStatus(int  status);
	List<Trainings> findByStatusAndMentorId(String status,long mentorId);


/*
	@Query(value="select T from Technology T where T.country=?1")
    List<Technology> findAbc(String toc);*/

   /* @Query(value="select U.country as country, U.name as name from user_info U", nativeQuery=true)
    List<Map<String, Object>> findMno();

    @Query(value="select U.country as country, U.name as name from user_info U", nativeQuery=true)
    List<Object[]> findXyz();*/
}
