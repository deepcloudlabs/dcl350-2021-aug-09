package com.example.hr.domain;

import java.util.List;

// DDD: Entity -> Repository, Ubiquitous Language: Sub-domain Concepts -> Solution Space
//      i) identity
//     ii) mutable -> can change state
// Sub-domain -- 1 --> Bounded-Context -- 1..* --> MicroService 
// Ubiquitous Language: Employee, TcKimlikNo, FullName, Iban, Money, Year, Department, Email, JoStyle 
// Entity Root -> Aggregate -> Validation
//                Invariant/Constraint/Consistent/Business Rule 
@Entity(identity="kimlikNo") 
@Aggregate
public class Employee {
	private TcKimlikNo kimlikNo;
	private FullName fullname;
	private Iban iban;
	private Money salary;
	private Year birthYear;
	private List<Department> departments;
	private JobStyle jobStyle;
	private Photo photo;
	// constructor!
	public Employee(TcKimlikNo kimlikNo, FullName fullname, Iban iban, Money salary, Year birthYear,
			List<Department> departments, JobStyle jobStyle, Photo photo) {
		this.kimlikNo = kimlikNo;
		this.fullname = fullname;
		this.iban = iban;
		this.salary = salary;
		this.birthYear = birthYear;
		this.departments = departments;
		this.jobStyle = jobStyle;
		this.photo = photo;
	}
	// Builder pattern! - Effective Java 3rd ed.
	
	// business method
}
