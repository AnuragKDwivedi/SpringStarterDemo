package com.starter.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starter.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee , Long>{

	public List<Employee> findAll();
}
