package com.starter.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starter.model.Address;
import com.starter.model.Employee;
import com.starter.service.MyService;

@RestController
@RequestMapping("/api/v1")
public class MyController {

	private static final Logger logger = LoggerFactory.getLogger(MyController.class);
	@Value("${username}")
	private String username;
	
	@Autowired
	private MyService service;
	
	@PostMapping("/employee")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) throws Exception{
		
		Employee empResponse = service.saveEmployee(employee);
		logger.info("API: /employee, Method: SaveEmployee.");
		
		return new ResponseEntity<>(empResponse, HttpStatus.OK);
		
	}
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployeesGroupByAddress(){
//        ExecutorService executor = Executors.newFixedThreadPool(2);
//        executor.submit(() -> service.getEmpGroupByAddress());
//        executor.submit(() -> service.getEmpGroupByAddress());
//        executor.submit(() -> service.getEmpGroupByAddress());
//        executor.submit(() -> service.getEmpGroupByAddress());
//        executor.shutdown();        
		return service.getEmpGroupByAddress();
	}
	
	@GetMapping("/employees/sorted")
	public List<Employee> getAllEmployeeAndSortByAge(){
		
		List<Employee> employee = new ArrayList();
		employee.add(new Employee("Anurag", new Address("Bangalore", 2142), 27, 1000));
		employee.add(new Employee("Deepak", new Address("Bangalore", 2143), 29, 2000));
		employee.add(new Employee("Vinay", new Address("Bangalore", 2141), 31, 3000));
		employee.add(new Employee("Ankit", new Address("Bangalore", 2140), 27, 4000));
		employee.add(new Employee("Rahul", new Address("Bangalore", 2141), 29, 5000));
		
		employee.sort((e1, e2) -> e2.getAge() - e1.getAge());
		employee.sort(Comparator.comparing(Employee::getAge));
		employee.sort(Comparator.comparing(Employee::getAge).thenComparing(e -> e.getAddress().getPincode()));
//				.collect(Collectors.toList());///Collectors.groupingBy( e-> e.getAge()));
		
		return employee;
	}
	
	@GetMapping("/employees/groupByCity")
	public Map<String, List<Employee>> getAllEmployeeGroupByCity(){
		
		List<Employee> employee = new ArrayList();
		employee.add(new Employee("Anurag", new Address("Bangalore", 2142), 27, 1000));
		employee.add(new Employee("Deepak", new Address("Bangalore", 2143), 29, 2000));
		employee.add(new Employee("Vinay", new Address("Bangalore", 2141), 31, 3000));
		employee.add(new Employee("Ankit", new Address("Bangalore", 2140), 27, 4000));
		employee.add(new Employee("Rahul", new Address("Bangalore", 2141), 29, 5000));
		
		//employee.sort(Comparator.comparing(e -> e.getAddress().getCity()));
		System.out.println(employee);
		Map<String, List<Employee>> res = employee.stream()
				//.sorted(Comparator.comparing(e-> e.getAddress().getPincode()))
				.collect(Collectors.groupingBy(e -> e.getAddress().getCity(), TreeMap::new, Collectors.toList()));
		        // .collect(Collectors.groupingBy(e -> e.getAddress().getCity(), Collectors.toList()));
		
		Map<Object, Set<String>> res1 = employee.stream()
				//.sorted(Comparator.comparing(e-> e.getAddress().getPincode()))
				//.collect(Collectors.groupingBy(e -> e.getAddress().getCity(), TreeMap::new, Collectors.toList()));
		        // .collect(Collectors.groupingBy(e -> e.getAddress().getCity(), Collectors.toList()));
				.collect(Collectors.groupingBy(e -> e.getAge(), Collectors.mapping(Employee::getName, Collectors.toSet())));
		System.out.println("res ->"+res);
		//for()
		res1.forEach((name, age) -> System.out.println("Name ->"+name +", Age ->"+age));
		return res;
	}
	
	@GetMapping("/employees/groupByAge")
	public Map<Object, Set<String>> getAllEmployeeGroupByAge(){
		
		List<Employee> employee = new ArrayList();
		employee.add(new Employee("Anurag", new Address("Bangalore", 2142), 27, 1000));
		employee.add(new Employee("Deepak", new Address("Bangalore", 2143), 29, 2000));
		employee.add(new Employee("Vinay", new Address("Bangalore", 2141), 31, 3000));
		employee.add(new Employee("Ankit", new Address("Bangalore", 2140), 27, 4000));
		employee.add(new Employee("Rahul", new Address("Bangalore", 2141), 29, 5000));
		Map<Object, Set<String>> res = employee.stream()
				.collect(Collectors.groupingBy(e -> e.getAge(), Collectors.mapping(Employee::getName, Collectors.toSet())));
		return res;
	}
	
	@DeleteMapping("/employees")
	public String deleteEmployee() {
		return service.deleteEmployeeFromCache();
	}
	
}
