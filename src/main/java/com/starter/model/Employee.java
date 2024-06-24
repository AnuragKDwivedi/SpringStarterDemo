package com.starter.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
//@Data
public class Employee {

	@Override
	public String toString() {
		return "Employee [Id=" + Id + ", name=" + name + ", address=" + address + ", age=" + salary + "]";
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long Id;
	public String name;
	@OneToOne(cascade = CascadeType.ALL)
	public Address address;
	public int age;
	public int salary;
	
	public Employee() {}
	
	public Employee(String name, Address address, int age, int salary) {
		super();
		this.name = name;
		this.address = address;
		this.age = age;
		this.salary = salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	
	
}
