package com.example.demo_user.repository;

import com.example.demo_user.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long>
{
	//List<Technology> findByNameAndToc(String name, String toc);
	//List<User> findByName(String name);
/*
	@Query(value="select T from Technology T where T.country=?1")
    List<Technology> findAbc(String toc);*/

   /* @Query(value="select U.country as country, U.name as name from user_info U", nativeQuery=true)
    List<Map<String, Object>> findMno();

    @Query(value="select U.country as country, U.name as name from user_info U", nativeQuery=true)
    List<Object[]> findXyz();*/
}
