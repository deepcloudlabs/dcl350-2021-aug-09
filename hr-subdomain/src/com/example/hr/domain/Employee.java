package com.example.hr.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// DDD: Entity -> Repository, Ubiquitous Language: Sub-domain Concepts -> Solution Space
//      i) identity
//     ii) mutable -> can change state
// Sub-domain -- 1 --> Bounded-Context -- 1..* --> MicroService 
// Ubiquitous Language: Employee, TcKimlikNo, FullName, Iban, Money, Year, Department, Email, JoStyle 
// Entity Root -> Aggregate -> Validation
//                Invariant/Constraint/Consistent/Business Rule 
@Entity(identity = "kimlikNo")
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

	public Employee(Builder builder) {
		this.kimlikNo = builder.kimlikNo;
		this.fullname = builder.fullname;
		this.iban = builder.iban;
		this.salary = builder.salary;
		this.birthYear = builder.birthYear;
		this.departments = builder.departments;
		this.jobStyle = builder.jobStyle;
		this.photo = builder.photo;
	}

	// Builder pattern! - Effective Java 3rd ed.
	public static class Builder {
		private TcKimlikNo kimlikNo;
		private FullName fullname;
		private Iban iban;
		private Money salary;
		private Year birthYear;
		private List<Department> departments;
		private JobStyle jobStyle;
		private Photo photo;

		public Builder(String value) {
			this.kimlikNo = TcKimlikNo.valueOf(value);
		}

		public Builder fullname(String first, String last) {
			this.fullname = FullName.of(first, last);
			return this;
		}

		public Builder iban(String value) {
			this.iban = Iban.valueOf(value);
			return this;
		}

		public Builder salary(double value) {
			this.salary = Money.of(value);
			return this;
		}

		public Builder salary(double value, FiatCurrency currency) {
			this.salary = Money.of(value, currency);
			return this;
		}

		public Builder birthYear(int value) {
			this.birthYear = new Year(value);
			return this;
		}
		public Builder jobStyle(JobStyle value) {
			this.jobStyle = value;
			return this;
		}

		public Builder departments(Department... values) {
			this.departments = new ArrayList<>(Arrays.asList(values));
			return this;
		}
		
		public Builder photo(byte[] values) {
			this.photo = Photo.of(values);
			return this;
		}
		
		public Builder photo(String base64Values) {
			this.photo = Photo.of(base64Values);
			return this;
		}
		
		public Employee build() {
			// composite validation
			// constraint/business rule/invariance
			return new Employee(this);
		}
	}
	// business method
	public void promote(Department department, double salaryIncreasePerct) {
		departments.clear();
		departments.add(department);
		salary = Money.of(salary.getValue()*(1.+salaryIncreasePerct), salary.getCurrency());
	}
	
	public void salaryYearlyIncrease(double salaryIncreasePerct) {
		salary = Money.of(salary.getValue()*(1.+salaryIncreasePerct), salary.getCurrency());
	}
	
	// utility method

	@Override
	public String toString() {
		return "Employee [kimlikNo=" + kimlikNo + ", fullname=" + fullname + ", iban=" + iban + ", salary=" + salary
				+ ", birthYear=" + birthYear + ", departments=" + departments + ", jobStyle=" + jobStyle + "]";
	}
	
}
