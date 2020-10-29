package com.eg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eg.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
