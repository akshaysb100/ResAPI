package com.bridgelabz.fundoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.fundoo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
 
	
}
