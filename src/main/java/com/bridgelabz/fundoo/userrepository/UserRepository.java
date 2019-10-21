package com.bridgelabz.fundoo.userrepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.usermodel.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 
    public Optional<User> findByEmail(String email);
	
	public String findByPassword(String password);
}
