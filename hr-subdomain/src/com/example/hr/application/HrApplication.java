package com.example.hr.application;

import java.util.Optional;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

public interface HrApplication {
	void hireEmployee(Employee employee);
	Optional<Employee> fireEmployee(TcKimlikNo kimlikNo);
}
