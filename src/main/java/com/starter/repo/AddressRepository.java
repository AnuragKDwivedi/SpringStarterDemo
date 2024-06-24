package com.starter.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starter.model.Address;
import com.starter.model.Employee;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

	public List<Address> findAll();
}
