package com.example.hr.adapter;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.repository.EmployeeRepository;

@Service
public class EmployeeRepositoryMongoAdapter implements EmployeeRepository {

	
	@Override
	public boolean exists(String kimlik) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Employee save(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Employee> findById(TcKimlikNo kimlik) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Employee employee) {
		// TODO Auto-generated method stub
		
	}

}
