package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.boundary.HireEmployeeRequest;
import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;

@Configuration
public class ModelMapperConfig {

	
	private static final Converter<HireEmployeeRequest,Employee> HireEmployeeRequest_TO_Employee_CONVERTER = context -> {
		var request = context.getSource();
		return new Employee.Builder(request.getKimlikNo())
	               .fullname(request.getFirstName(), request.getLastName())
	               .jobStyle(request.getJobStyle())
	               .iban(request.getIban())
	               .birthYear(request.getBirthYear())
	               .salary(request.getSalary(), request.getCurrency())
	               .departments(request.getDepartments().toArray(new Department[0]))
	               .photo(request.getPhoto())
	               .build(); // valid!
	};

	@Bean
	public ModelMapper modelMapper() {
		var mapper = new ModelMapper();
		mapper.addConverter(HireEmployeeRequest_TO_Employee_CONVERTER, HireEmployeeRequest.class, Employee.class);
		return mapper;
	}
}
