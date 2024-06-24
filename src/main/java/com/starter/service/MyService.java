package com.starter.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.starter.model.Employee;
import com.starter.repo.EmployeeRepository;


@Service
public class MyService {

	private final EmployeeRepository employeeRepository;
	
	@Autowired
	public MyService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(MyService.class);
	
	public Employee saveEmployee(Employee employee) throws Exception{
		return Optional.of(employee)
				.filter(emp -> validateEmployeeAge(emp))
				.map(emp -> {
				   try{
					   logger.info("saving now");
					   employeeRepository.save(emp);
						logger.info("Employe saved in db.");
						return emp;
				   }catch(Exception e) {
					   throw new RuntimeException("Failed to save employee.", e);
				   }
				})
				.orElseThrow(() -> new Exception("Invalid Employee"));
	}
	
	public boolean validateEmployeeAge(Employee emp) {
		return emp.getAge() > 18;
	}
	
	@Cacheable(value="employee", sync=true)
	public List<Employee> getEmpGroupByAddress() {
		List<Employee> employees = employeeRepository.findAll();
		employees.stream().collect(Collectors.groupingBy(e -> e.getAddress().getPincode(), 
				Collectors.groupingBy(e-> e.getName(), Collectors.counting())));
		logger.info("emp ->"+employees);
		return employees;
	}
	
	@CacheEvict(value="employee", allEntries=true)
	public String deleteEmployeeFromCache() {
		System.out.println("Deleted data.");
		//employeeRepository.findAll();
		//employees.stream().collect(Collectors.groupingBy(e -> e.getAddress().getPincode(), Collectors.groupingBy(e-> e.getName(), Collectors.counting())));
		return "Deleted Employee Details";
	}
}
