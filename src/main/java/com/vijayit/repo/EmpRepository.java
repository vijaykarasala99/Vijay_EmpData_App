package com.vijayit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vijayit.entity.EmpEntity;

public interface EmpRepository extends JpaRepository<EmpEntity, Integer> {

	    @Query("select distinct(salary) from EmpEntity")
		public List<String> getSalaries();
	    @Query("select distinct(mail) from EmpEntity")
		public List<String> getMails();
	    @Query("select distinct(name) from EmpEntity")
		public List<String> getNames();

	
	
}
