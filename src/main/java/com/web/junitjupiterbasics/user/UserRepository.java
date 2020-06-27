package com.web.junitjupiterbasics.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.junitjupiterbasics.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUserNameAndPassword(String userName, String password);

}
