package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.controller.MyController;
import com.example.model.Address;
import com.example.model.Employee;
import com.example.repo.EmployeeRepository;


@Service
public class MyService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(MyService.class);
	
	public Employee saveEmployee(Employee employee) throws Exception{
		return Optional.of(employee)
				.filter(emp -> validateEmployeeAge(emp))
				.map(emp -> {
				   try{
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
		//List<Employee> employees = new ArrayList();
		//Address add = new Address("Bangalore", 2141);
		//Employee emp = new Employee("Anurag", add, 28);
		//employees.add(emp);
		//System.out.println("Setting data manually");
		List<Employee> employees = employeeRepository.findAll();
		employees.stream().collect(Collectors.groupingBy(e -> e.getAddress().getPincode(), 
				Collectors.groupingBy(e-> e.getName(), Collectors.counting())));
		return employees;
	}
	
	@CacheEvict(value="employee", allEntries=true)
	public String deleteEmployeeFromCache() {
		//List<Employee> employees = new ArrayList();
		//Address add = new Address("Bangalore", 2141);
		//Employee emp = new Employee("Anurag", add, 28);
		//employees.add(emp);
		System.out.println("Deleted data.");
		//employeeRepository.findAll();
		//employees.stream().collect(Collectors.groupingBy(e -> e.getAddress().getPincode(), Collectors.groupingBy(e-> e.getName(), Collectors.counting())));
		return "Deleted Employee Details";
	}
}
