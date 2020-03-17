package com.gotodigital.covid19.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gotodigital.covid19.api.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	 

}
