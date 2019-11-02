package com.bridgelabz.fundoo.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.user.model.UserData;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {
 
    public Optional<UserData> findByEmail(String email);
	
	public String findByPassword(String password);

}
