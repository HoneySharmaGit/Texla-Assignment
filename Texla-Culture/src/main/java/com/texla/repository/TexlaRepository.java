package com.texla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.texla.entity.EmployeeExpenses;

public interface TexlaRepository extends JpaRepository<EmployeeExpenses, Integer> {

	@Query(value = "SELECT ee.uniqueId FROM EmployeeExpenses ee ORDER BY ee.sNo DESC LIMIT 1")
	String getLastGeneratedUniqueId();

}
